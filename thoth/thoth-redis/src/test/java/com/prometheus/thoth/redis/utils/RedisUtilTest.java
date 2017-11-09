package com.prometheus.thoth.redis.utils;

import com.prometheus.thoth.redis.RedisApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;


/**
 * Created by sunliangliang on 2017/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplicationTest.class)
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void getKeys(){
        System.out.print("---------------"+redisUtil);
        Set<String> set =redisUtil.getKeys("thoth:spirit:*");
        System.out.print("---------------");
        for (String str:set){
           System.out.print("--------str:"+str);
        }
    }
}