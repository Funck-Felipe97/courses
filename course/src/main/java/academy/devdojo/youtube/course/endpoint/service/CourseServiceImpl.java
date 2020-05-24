package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course update(Long id, Course course) {
        Course savedCourse = findOne(id);
        String[] ignoreProperties = new String[]{"id", "createdAt", "lastUpdate", "sections"};
        BeanUtils.copyProperties(course, savedCourse, ignoreProperties);
        return save(savedCourse);
    }

    @Override
    public List<Course> findAllWithGraph() {
        return courseRepository.findAllWithGraph();
    }

    @Override
    public JpaRepository getRepository() {
        return this.courseRepository;
    }

    @Override
    public Class<Course> getClazz() {
        return Course.class;
    }

}
