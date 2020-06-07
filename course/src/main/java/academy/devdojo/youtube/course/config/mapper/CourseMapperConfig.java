package academy.devdojo.youtube.course.config.mapper;

import academy.devdojo.youtube.course.model.mapper.properties.CoursePropertyMapper;
import academy.devdojo.youtube.course.model.mapper.properties.LessonPropertyMapper;
import academy.devdojo.youtube.course.model.mapper.properties.SectionPropertyMapper;
import academy.devdojo.youtube.course.model.mapper.properties.StudentPropertyMapper;
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
        mapper.addMappings(new CoursePropertyMapper());
        mapper.addMappings(new SectionPropertyMapper());
        mapper.addMappings(new LessonPropertyMapper());
        mapper.addMappings(new StudentPropertyMapper());
        return mapper;
    }

}
