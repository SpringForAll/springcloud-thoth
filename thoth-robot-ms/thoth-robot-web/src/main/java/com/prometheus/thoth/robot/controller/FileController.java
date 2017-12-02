package com.prometheus.thoth.robot.controller;

import com.prometheus.thoth.common.exception.BusinessException;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.model.RestResultBuilder;
import com.prometheus.thoth.common.util.VerificationUtils;
import com.prometheus.thoth.common.web.controller.BaseController;
import com.prometheus.thoth.fastdfs.client.FastDFSClient;
import com.prometheus.thoth.robot.exception.RobotErrorCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * created by sunliangliang
 * 该类是利用fastdfs文件做管理
 */
@RestController
public class FileController extends BaseController{
    @Autowired
    private FastDFSClient fastDFSClient;
    @Value("${fastdfs.nginx-web:}")
    String fastdfsUrl;
    @ApiOperation(value="文件上传", notes="文件上传")
    @PostMapping("/file")
    public RestResult handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename();
        if (!VerificationUtils.isImage(name)){
            throw new BusinessException(RobotErrorCode.INVALID_IMAGE_FORMAT);
        }
        if (!multipartFile.isEmpty()) {
            try {
                String uploadFile = fastDFSClient.uploadFile(multipartFile);
                logger.debug("uploadFile:{}", uploadFile);
                return RestResultBuilder.builder().success(uploadFile).build();
            } catch (IOException e) {
                return RestResultBuilder.builder().failure().build();
            }
        } else {
            throw new BusinessException(RobotErrorCode.FILE_EMPTY);
        }
    }
    @ApiOperation(value="删除文件", notes="删除文件")
    @DeleteMapping("/file")
    public RestResult handleFileDel(@PathParam("path") String path) {
        logger.debug("handleFileDel :{}", path);
        StringBuilder apperder = new StringBuilder();
        String [] dfsArray = path.split(fastdfsUrl);
        apperder.append(fastdfsUrl).append("/purge").append(dfsArray[1]);
        logger.info("--------url:"+apperder.toString());
        fastDFSClient.deleteFile(apperder.toString());
        return RestResultBuilder.builder().success().build();
    }
}
