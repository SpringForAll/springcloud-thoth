package com.prometheus.thoth.data;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liangliang on 2017/04/22.
 *
 * @author liangliang
 * @since 2017/04/22
 */
public class TestId {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testId() {

        long workerIdBits = 10L;/* 机器标识位数 */
        long datacenterIdBits = 10L;
        long maxWorkerId = -1L ^ (-1L << workerIdBits);/* 机器ID最大值 1023 */
        long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        //long workerIdBits = 4L;
        //long maxWorkerId = -1L ^ -1L << workerIdBits;

        logger.debug("testId maxWorkerId:{}, maxDatacenterId:{}", maxWorkerId, maxDatacenterId);
    }
}
