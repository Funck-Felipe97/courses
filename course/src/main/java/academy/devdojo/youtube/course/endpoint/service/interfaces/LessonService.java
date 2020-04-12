package academy.devdojo.youtube.course.endpoint.service.interfaces;

import academy.devdojo.youtube.core.service.GenericService;
import academy.devdojo.youtube.course.model.entity.Lesson;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LessonService extends GenericService<Lesson, Long> {

    @Transactional(readOnly = true)
    List<Lesson> findByCourseAndSection(Long courseId, Long sectionId);

    @Transactional(propagation = Propagation.REQUIRED)
    Lesson save(Lesson lesson, Long courseId, Long sectionId);

    @Transactional(readOnly = true)
    Optional<Lesson> findByIdAndSectionAndCourse(Long id, Long sectionId, Long courseId);

    @Transactional(propagation = Propagation.REQUIRED)
    Lesson update(Long id, Lesson lesson, Long sectionId, Long courseId);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByIdAndSectionAndCourse(Long id, Long sectionId, Long courseId);

}
