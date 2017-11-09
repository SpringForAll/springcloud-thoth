package com.prometheus.thoth.common.model;


import com.prometheus.thoth.common.web.dto.PageInfoDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liangliang on 2017/02/25.
 *
 * @author liangliang
 * @since 2017/02/25
 */
public class PageInfo<T> extends com.github.pagehelper.PageInfo {

    protected static int PAGE_NUM_DEFAULT = 1;

    protected static int PAGE_SIZE_DEFAULT = 10;

    protected String orderColumn;

    protected String dir; //asc ,desc

    public static <T> PageInfo<T> build(PageInfoDto pageInfoDto) {
        if (pageInfoDto == null) {
            pageInfoDto = new PageInfoDto();
            pageInfoDto.setPageNum(PAGE_NUM_DEFAULT);
            pageInfoDto.setPageSize(PAGE_SIZE_DEFAULT);
        }
        return new PageInfo<>(pageInfoDto.getPageNum(), pageInfoDto.getPageSize(), pageInfoDto.getOrderColumn(), pageInfoDto.getDir());
    }

    public static <T> PageInfo<T> build(Integer pageNum, Integer pageSize) {
        return new PageInfo<>(pageNum, pageSize);
    }

    public PageInfo() {
        this(PAGE_NUM_DEFAULT, PAGE_SIZE_DEFAULT);
    }

    public PageInfo(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? PAGE_NUM_DEFAULT : pageNum;
        pageSize = pageSize == null ? PAGE_SIZE_DEFAULT : pageSize;
        this.setPageNum(pageNum);
        this.setPageSize(pageSize);
    }

    public PageInfo(Integer pageNum, Integer pageSize, String orderColumn, String dir) {
        pageNum = pageNum == null ? PAGE_NUM_DEFAULT : pageNum;
        pageSize = pageSize == null ? PAGE_SIZE_DEFAULT : pageSize;
        this.setPageNum(pageNum);
        this.setPageSize(pageSize);
        this.setOrderColumn(orderColumn);
        this.setDir(dir);
    }


    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        super(list, PAGE_SIZE_DEFAULT);
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageInfo(List<T> list, int navigatePages) {
        super(list, navigatePages);
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public String getDir() {
        return dir;
    }

    /**
     * 排序
     *
     * @return
     */

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
