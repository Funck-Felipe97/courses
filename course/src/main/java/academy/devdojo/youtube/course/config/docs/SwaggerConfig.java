package academy.devdojo.youtube.course.config.docs;

import academy.devdojo.youtube.core.config.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    public SwaggerConfig() {
        super("academy.devdojo.youtube.course.endpoint.resource");
    }

}
