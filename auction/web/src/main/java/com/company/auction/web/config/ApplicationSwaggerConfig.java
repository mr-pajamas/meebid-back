package com.company.auction.web.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Michael on 2016/6/26.
 */
@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .build()
            .apiInfo(new ApiInfo(
                "拍卖项目接口",
                "这是项目接口说明",
                "1.0",
                "",
//                new Contact("汤力丞", "", "lctang@foxmail.com"),
                new Contact("", "", ""),
                "",
                ""
            )).protocols(Collections.singleton("http"));

    }
}
