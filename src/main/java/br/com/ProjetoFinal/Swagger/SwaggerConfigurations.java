package br.com.ProjetoFinal.Swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

	@Bean
	public Docket forumApi() {
		 
	       Contact contact = new Contact("Thyago Gomes", "", "");
	       List<VendorExtension> vendorExtensions = new ArrayList<>();
	       
	       ApiInfo apiInfo = new ApiInfo(
	       "Restful API documentação", 
	       "CRUD de produtos usando Java Spring", "1.0", "", contact, 
	       "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",vendorExtensions);
	       
	       Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.ProjetoFinal")).paths(PathSelectors.ant("/**"))
				.build();
	       return docket;
	}
}