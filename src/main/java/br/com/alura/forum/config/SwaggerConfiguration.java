
package br.com.alura.forum.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Optional;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import br.com.alura.forum.domain.User;
import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo())
				.globalResponseMessage(RequestMethod.GET, 
						Arrays.asList(
								new ResponseMessageBuilder()
								.code(500)
								.message("Deu erro interno no servidor")
								.build(),
								new ResponseMessageBuilder()
								.code(403)
								.message("Forbidden! Voce não pode acessar este recurso")
								.build(),
								new ResponseMessageBuilder()
								.code(404)
								.message("Recurso não encontrado")
								.build()
								)
				).ignoredParameterTypes(User.class)
				.globalOperationParameters(Arrays.asList(new ParameterBuilder().name("Authorization")
						.description("Header para facilitar o envio do Authorization Bearer Token")
						.modelRef(new ModelRef("string")).parameterType("header").required(false).build()

				));
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact();
		contact.setEmail("aluno@alura.com.br");
		contact.setName("Alura");
		contact.setUrl("alura.com.br");
		
		return new ApiInfoBuilder()
				.title("Alura Forum API Documentation")
				.description("Documentação interativa do forum da Alura")
				.version("1.0")
				.contact(contact.toString())
				.build();
	}
	
	

}
