package academy.devdojo.youtube.course.model.mapper.properties;

import academy.devdojo.youtube.course.model.dto.response.CourseResponse;
import academy.devdojo.youtube.course.model.entity.Course;
import org.modelmapper.PropertyMap;

public class CoursePropertyMapper extends PropertyMap<Course, CourseResponse> {

    @Override
    protected void configure() {
        map().setCourseId(source.getId());
    }

}
