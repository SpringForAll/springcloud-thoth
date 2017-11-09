package com.prometheus.thoth.common.model;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * Created by liangliang on 2017/02/25.
 *
 * @author liangliang
 * @since 2017/02/25
 */
public class PageRestResult extends RestResult {

    @JSONField(ordinal = 4)
    private int pageNum;

    @JSONField(ordinal = 5)
    private int pageSize;

    @JSONField(ordinal = 6)
    private long pages;

    @JSONField(ordinal = 7)
    private long total;

    public PageRestResult() {
        super();
    }

    public PageRestResult(int code, String message) {
        super(code, message);
    }

    public PageRestResult(int code, String message, Object data) {
        super(code, message, data);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
