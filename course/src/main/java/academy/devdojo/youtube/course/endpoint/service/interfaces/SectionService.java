package academy.devdojo.youtube.course.endpoint.service.interfaces;

import academy.devdojo.youtube.core.service.GenericService;
import academy.devdojo.youtube.course.model.entity.Section;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SectionService extends GenericService<Section, Long> {

    @Transactional(readOnly = true)
    Optional<Section> findByIdAndCourse(Long id, Long courseId);

    @Transactional(readOnly = true)
    Section findOneByIdAndCourse(Long id, Long courseId);

    @Transactional(readOnly = true)
    List<Section> findAllByCourse(Long courseId);

    @Transactional(propagation = Propagation.REQUIRED)
    Section save(Long courseId, Section section);

    @Transactional(propagation = Propagation.REQUIRED)
    Section update(Long id, Long courseId, Section section);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByIdAndCouse(Long id, Long course);

}
