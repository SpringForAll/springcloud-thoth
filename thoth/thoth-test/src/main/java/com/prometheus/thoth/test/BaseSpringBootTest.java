package com.prometheus.thoth.test;


import com.prometheus.thoth.ApplicationTest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ResourceBundle;

/**
 * Base SpringBootTest.
 * <p>
 * Created by liangliang on 2017/02/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class BaseSpringBootTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static ObjectMapper objectMapper = new ObjectMapper();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    static {
        System.setProperty("LOG_PATH", "./logs");
        ResourceBundle allProperites= ResourceBundle.getBundle("application");
        String logFile = StringUtils.defaultIfBlank(allProperites.getString("application.name"), "application");
        System.setProperty("LOG_FILE", logFile);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        //未知属性忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
