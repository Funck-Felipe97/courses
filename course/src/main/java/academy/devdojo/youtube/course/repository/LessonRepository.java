package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Lesson;
import academy.devdojo.youtube.course.model.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllBySection(Section section);

    Optional<Lesson> findByIdAndSection(Long id, Section section);

    void deleteByIdAndSection(Long id, Section section);

}
