package com.lys.demo.Swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2通过注解来生成API接口文档，文档信息包括接口名、请求方法、参数、返回信息等。通常情况下用于生成在线API文档，下面是常见的注解：
 *
 * @Api：修饰整个类，用于描述Controller。
 * @ApiOperation：描述类的方法，或者说一个接口。
 * @ApiParam：单个参数描述。
 * @ApiModel：用对象来接收参数。
 * @ApiProperty：用对象接收参数时，描述一个对象的字段。
 * @ApiResponse：HTTP响应的一个描述。
 * @ApiResponses：HTTP响应的整体描述。
 * @ApiIgnore：Swagger2忽略该api。
 * @ApiError：发生错误返回的信息。
 * @ApiParamImplicit：一个请求参数。
 * @ApiParamsImplicit：多个请求参数。
 */
@Configuration  //@Configuration注解表示这是一个配置类
@EnableSwagger2 //@EnableSwagger2开启Swagger2的功能
public class Swagger2Config {
    //在配置类中需要注入一个Docket的Bean，该Bean包含了apiInfo，即基本的API文件的描述信息，以及包扫描的基本包名等信息。
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("默认分组")
                //select() 函数会返回一个ApiSelectorBuilder实例，用来控制哪些接口暴露到swagger页面中。
                // 本例采用指定包路径来定义， Swagger会扫描该包下的所有Controller定义的api, 并生成文档展现在swagger页面中（除了接口被@ApiIgnore指定的会被忽略）
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lys.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    // 预览地址:swagger-ui.html
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot利用swagger构建api文档")
                .description("接口访问地址：http://localhost:8080/swagger-ui.html")
                .termsOfServiceUrl("http://localhost:8080/swagger-ui.html#/")
                .version("1.0")
                .build();
    }

}
