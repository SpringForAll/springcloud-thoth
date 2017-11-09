package com.prometheus.thoth.fastdfs.client;


import com.prometheus.thoth.common.exception.GlobalErrorCode;
import com.prometheus.thoth.common.util.ExceptionUtils;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by liangliang on 2017/03/27.
 *
 * @author liangliang
 * @since 2017/03/27
 */
public class FastDFSClient {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final Logger FDFS_UPLOAD = LoggerFactory.getLogger("FDFS_UPLOAD");

    /** 支持的图片类型 */
    private static final String[] SUPPORT_IMAGE_TYPE = {"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"};
    private static final List<String> SUPPORT_IMAGE_LIST = Arrays.asList(SUPPORT_IMAGE_TYPE);

    @Autowired
    private FastFileStorageClient storageClient;

    /** fastDfs Web地址 */
    private boolean hasFastDfsNginx;

    /** fastdfs nginx config */
    private String fastDfsNginx;

    /**
     * 上传文件
     *
     * @param is 文件对象
     * @param fileSize 文件大小
     * @return 文件访问地址
     * @throws IOException
     */
    public String upload(InputStream is, long fileSize, String fileExtName) throws IOException {
        StorePath storePath = storageClient.uploadFile(is, fileSize, fileExtName, null);
        logger.debug("uploadFile fullPath:{}", storePath.getFullPath());
        //记录上传文件地址
        FDFS_UPLOAD.info("{}", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        logger.debug("uploadFile fullPath:{}", storePath.getFullPath());
        //记录上传文件地址
        FDFS_UPLOAD.info("{}", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }


    /**
     * 上传图片，支持的类型为："JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     *
     * @param inputStream 图片对象
     * @param fileSize    图片大小
     * @param fileExtName 图片扩展名
     * @return 文件访问地址
     * @throws IOException
     */
    public StorePath uploadImage(MultipartFile inputStream, long fileSize, String fileExtName) {
        return uploadImage(inputStream, fileSize, fileExtName);
    }

    /**
     * 上传图片，支持的类型为："JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     *
     * @param inputStream 图片对象
     * @param fileSize    图片大小
     * @param fileExtName 图片扩展名
     * @return 文件访问地址
     * @throws IOException
     */
    public StorePath uploadImage(InputStream inputStream, long fileSize, String fileExtName) {
        Validate.notNull(inputStream, "上传文件流不能为空");
        Validate.notBlank(fileExtName, "文件扩展名不能为空");
        // 检查是否能处理此类图片
        if (!isSupportImage(fileExtName)) {
            ExceptionUtils.business(GlobalErrorCode.UNSUPPORT_IMAGE_TYPE);
        }
        return uploadImage(inputStream, fileSize, fileExtName);
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content       文件内容
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
        //记录上传文件地址
        FDFS_UPLOAD.info("{}", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }

    /**
     * 封装图片完整URL地址
     *
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileRoot = "";
        if (isHasFastDfsNginx()) {
            fileRoot = getFastDfsNginx();
        }
        String filePath = String.format("%s/%s", fileRoot, storePath.getFullPath());
        return filePath;
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        logger.debug("FastDFSClient.deleteFile fileUrl:{}", fileUrl);
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            logger.info("FastDFSClient.deleteFile storePath group:{}, path:{}",
                    storePath.getGroup(), storePath.getPath());
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsServerException e) {
            logger.warn("FdfsServerException--->{}", e.getMessage());
            ExceptionUtils.business(GlobalErrorCode.UNSUPPORT_STORE_PATH);
        }
    }

    /**
     * 是否是支持的图片文件
     *
     * @param fileExtName
     * @return
     */
    private boolean isSupportImage(String fileExtName) {
        return SUPPORT_IMAGE_LIST.contains(fileExtName.toUpperCase());
    }

    public boolean isHasFastDfsNginx() {
        return hasFastDfsNginx;
    }

    public void setHasFastDfsNginx(boolean hasFastDfsNginx) {
        this.hasFastDfsNginx = hasFastDfsNginx;
    }

    public String getFastDfsNginx() {
        return fastDfsNginx;
    }

    public void setFastDfsNginx(String fastDfsNginx) {
        this.fastDfsNginx = fastDfsNginx;
    }
}
