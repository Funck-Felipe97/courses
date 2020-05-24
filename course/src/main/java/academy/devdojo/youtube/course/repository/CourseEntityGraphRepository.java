package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Course;

import java.util.List;

public interface CourseEntityGraphRepository {

    List<Course> findAllWithGraph();

}
