package br.com.ProjetoFinal.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket forumApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("br.com.ProjetoFinal"))
	        .paths(PathSelectors.ant("/**"))
	        .build();
	}
	
	  private ApiInfo metaData() {
		    return new ApiInfoBuilder()
		        .title("API Produtos ")
		        .description("Projeto Final Compass")
		        .version("1.0.0")
		        .license("Apache License Version 2.0")
		        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
		        .build();
		  }
}