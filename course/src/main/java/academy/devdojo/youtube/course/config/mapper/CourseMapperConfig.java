package academy.devdojo.youtube.course.config.mapper;

import academy.devdojo.youtube.course.model.mapper.properties.CoursePropertyMapper;
import academy.devdojo.youtube.course.model.mapper.properties.LessonPropertyMapper;
import academy.devdojo.youtube.course.model.mapper.properties.SectionPropertyMapper;
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
        configureSectionMapper(mapper);
        configureLessonMapper(mapper);
        return mapper;
    }

    private void configureCourseMapper(ModelMapper mapper) {
        mapper.addMappings(new CoursePropertyMapper());
    }

    private void configureSectionMapper(ModelMapper mapper) {
        mapper.addMappings(new SectionPropertyMapper());
    }

    private void configureLessonMapper(ModelMapper mapper) {
        mapper.addMappings(new LessonPropertyMapper());
    }

}
