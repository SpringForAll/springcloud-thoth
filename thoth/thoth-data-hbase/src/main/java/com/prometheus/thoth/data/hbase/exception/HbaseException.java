package com.prometheus.thoth.data.hbase.exception;

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * Created by liangliang on 2017/04/10.
 *
 * @author liangliang
 * @since 2017/04/10
 */
public class HbaseException extends UncategorizedDataAccessException {

    public HbaseException(Exception cause) {
        super(cause.getMessage(), cause);
    }

    public HbaseException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
