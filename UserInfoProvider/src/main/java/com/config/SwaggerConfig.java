package com.config;

import com.google.common.base.Predicates;
import javafx.scene.shape.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

public class SwaggerConfig {

    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket Lamp() {
        Parameter token = new ParameterBuilder().name("token")
                .description("用户登录令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(true)
                .build();
        ArrayList<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo())
                .groupName("灯")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(Predicates.or(
                        PathSelectors.regex("/lamp.*"),
                        PathSelectors.regex("/lampState.*")
                )).build();
    }

    @Bean
    public Docket LampState() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("获取 token")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(PathSelectors.ant("/GetToken"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("登录权限控制")
                .description("").build();
    }
}
