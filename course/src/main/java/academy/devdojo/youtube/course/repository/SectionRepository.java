package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findByIdAndCourse(Long id, Course course);

    List<Section> findAllByCourse(Course course);

    void deleteByIdAndCourse(Long id, Course course);

}
