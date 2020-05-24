package academy.devdojo.youtube.course.endpoint.service.interfaces;

import academy.devdojo.youtube.core.service.GenericService;
import academy.devdojo.youtube.course.model.entity.Course;

import java.util.List;

public interface CourseService extends GenericService<Course, Long> {

    List<Course> findAllWithGraph();

}
