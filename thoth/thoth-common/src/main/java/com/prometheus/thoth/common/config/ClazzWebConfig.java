package com.prometheus.thoth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liangliang on 2017/7/26.
 */
@Configuration
@EnableSwagger2
public class ClazzWebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.prometheus.thoth"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("加恩工作室", "june.prometheus.com", "june@cyou-inc.com(加恩工作室邮箱 JUNE暂定为加恩的英文名)");
        return new ApiInfoBuilder()
                .title("Thoth项目API文档")
                .description("BUG? 不存在的!")
                .contact(contact)
                .version("0.0.1SNAPSHOT")
                .build();
    }
}
