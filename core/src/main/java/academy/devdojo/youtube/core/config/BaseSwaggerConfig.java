package academy.devdojo.youtube.core.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class BaseSwaggerConfig {

    private final String basePackge;

    public BaseSwaggerConfig(String basePackge) {
        this.basePackge = basePackge;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackge))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Another Awesome course from DevDojo <3")
                .description("Everybody is a Jedi now")
                .version("1.0")
                .contact(new Contact("Felipe Carvalho Funck", "", "funck@alunos.utfpr.edu.br"))
                .license("Private stuff bro, belongs to Devdojo")
                .licenseUrl("")
                .build();
    }

}
