package com.hubu.aspirin.core.needconfig;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 替换 Swagger的 UI
 * @author alex
 */
@SpringBootConfiguration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {
    @Bean
    public Docket userApi() {
        // 默认分组
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(defaultApiInfo())
                .groupName("默认接口分组")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hubu.aspirin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo defaultApiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档 1.0 版本")
                .description("")
                .version("1.0")
                .build();
    }

}
