package com.salary.config;

import com.fasterxml.classmate.TypeResolver;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig implements ApiListingScannerPlugin {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.salary.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("某大学工资管理系统api文档")
                                .description("swagger生成接api文档")
                                .version("v1.0")
                                .contact(new Contact("软件工程第十小组", "https://gitee.com/salary_tenth_team", "salary@gmail.com"))
                                .termsOfServiceUrl("https://gitee.com/salary_tenth_team")
                                .build()
                );
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("登录")
                .notes("用户名密码登录")
                .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) // 接收参数格式
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
                .tags(Sets.newHashSet("登录"))
                .parameters(Arrays.asList(
                        new ParameterBuilder()
                                .description("工号")
                                .type(new TypeResolver().resolve(String.class))
                                .name("id")
                                .defaultValue("4444444444")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .description("密码")
                                .type(new TypeResolver().resolve(String.class))
                                .name("psd")
                                .defaultValue("aaa")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build()
                ))
                .responseMessages(Collections.singleton(
                        new ResponseMessageBuilder().code(200).message("登录成功")
                                .responseModel(null).build()))
                .build();

        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口",
                Collections.singletonList(usernamePasswordOperation), false);

        return Collections.singletonList(loginApiDescription);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
