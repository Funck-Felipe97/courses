package academy.devdojo.youtube.course.model.mapper.properties;

import academy.devdojo.youtube.course.model.dto.response.StudentResponse;
import academy.devdojo.youtube.course.model.entity.Student;
import org.modelmapper.PropertyMap;

public class StudentPropertyMapper extends PropertyMap<Student, StudentResponse> {

    @Override
    protected void configure() {
        map().setStudentId(source.getId());
    }

}
