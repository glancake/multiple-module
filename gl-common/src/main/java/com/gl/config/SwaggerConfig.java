package com.gl.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addParameters("token",new HeaderParameter().description("请填写Token").schema(new StringSchema()))
                        .addParameters("adminID",new HeaderParameter().description("请填写用户ID").schema(new StringSchema())))
                .info(new Info().title(applicationName)
                        .description("接口文档")
                        .version("v2.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("留言板")
                        .url("https://gitee.com/StandFast"));
    }

}
