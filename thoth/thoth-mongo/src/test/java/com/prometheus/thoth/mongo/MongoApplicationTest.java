package com.prometheus.thoth.mongo;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test for mongo
 */
@ActiveProfiles("test")
@PropertySources(
        @PropertySource("application-test.properties")
)
@SpringBootTest(classes = MongoApplicationTest.class)
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MongoApplicationTest  {

    @Autowired
    @Qualifier("imMongoTemplate")
    private MongoTemplate imMongoTemplate;

    @Test
    public void save() throws Exception {
   
    }
}