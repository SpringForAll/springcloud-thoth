package com.prometheus.thoth.redis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 *
 * Created by ${USER} on ${DATE}.
 */
public class RedisApplicationTest {

    protected static final Logger logger = LoggerFactory.getLogger(RedisApplicationTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        logger.info("===setUpBeforeClass=====");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        logger.info("===tearDownAfterClass===");
    }

    @Before
    public void setUp() throws Exception {
        logger.info("=========setUp==========");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("=========tearDown=======");
    }

    @Test
    public void testAssertEquals() {
        logger.debug("testAssertEquals");
        assertEquals("a", "a");
    }

    @Test
    public void testAssertTrue() {
        logger.debug("testAssertTrue");
        assertTrue(true);
    }

    @Test
    public void testAssertFalse() {
        logger.debug("testAssertFalse");
        assertFalse(false);
    }

    @Test(expected = RuntimeException.class)
    public void testException() {
        logger.debug("testException");
        throw new RuntimeException();
    }
}