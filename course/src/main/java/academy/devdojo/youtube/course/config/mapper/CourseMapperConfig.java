package academy.devdojo.youtube.course.config.mapper;

import academy.devdojo.youtube.course.model.mapper.properties.CoursePropertyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CourseMapperConfig {

    @Primary
    @Bean
    public ModelMapper configure() {
        ModelMapper mapper = new ModelMapper();
        configureCourseMapper(mapper);
        return mapper;
    }

    private void configureCourseMapper(ModelMapper mapper) {
        mapper.addMappings(new CoursePropertyMapper());
    }

}
