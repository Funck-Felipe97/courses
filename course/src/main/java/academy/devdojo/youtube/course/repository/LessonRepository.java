package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Lesson;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {
}
