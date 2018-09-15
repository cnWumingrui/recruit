package com.yc.web.swagger;

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
 * 创建Swagger配置类时，
 * 通过@Configuration注解，让Spring来加载该类配置
 *  @EnableSwagger2:注解来启用Swagger2
 *  再通过被@Bean注解的函数创建Docket的Bean之后,
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//创建多个函数并用@Bean注解，这样就可以针对不同的Controller创建出各自的API文档。需要注意的是：在创建多个API时，需要指定API文档的名称。
	//这里如果函数上面不配置@Bean，那么Swagger只是启动了，没有创建API信息。 
	@Bean
	
	/**
	 * 创建API文档,apiInfo()用来创建该Api的基本信息
	 * 
	 * @return
	 */
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                 //.groupName("cus")
                .apiInfo(apiInfo())  //apiInfo()用来创建该Api的基本信息
                .select()    //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                .apis(RequestHandlerSelectors.basePackage("com.yc.web.controllers"))
                .paths(PathSelectors.any())
                .build();
    }
    
	/**
	 * 创建api信息
	 * @return
	 */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目信息相关内容")
                .description("以下接口是操作用户的相关信息")
                .version("1.0")
                .build();
    }
}