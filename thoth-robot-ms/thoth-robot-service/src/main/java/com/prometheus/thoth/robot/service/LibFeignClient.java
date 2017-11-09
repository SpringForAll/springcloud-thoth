package com.prometheus.thoth.robot.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * created by sunliangliang
 * 获取知识库相关信息
 */
@FeignClient("clazz-ms")
public interface LibFeignClient {

}
