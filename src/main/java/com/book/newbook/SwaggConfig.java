package com.book.newbook;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;



@EnableElasticsearchRepositories(basePackages = "com.example.demo.repository")
@Configuration
@EnableSwagger2


public class SwaggConfig extends AbstractElasticsearchConfiguration{

	@Value("${elasticsearch.url}")
	public String elasticsearchUrl;
	
	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration config = ClientConfiguration.builder()
				.connectedTo(elasticsearchUrl)
				.build();
		return RestClients.create(config).rest();
	}
	
/*	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/posts.*"), regex("/api/new.*"));
	}*/
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(regex("/api.*")).build();
	}
	
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Book API")
				.description("Books for Booklovers")
				.termsOfServiceUrl("http://javainuse.com")
				.contact("santhiyamalar@gmail.com").license("San License")
				.licenseUrl("book_san@gmail.com").version("1.0").build();
	}
	
}
