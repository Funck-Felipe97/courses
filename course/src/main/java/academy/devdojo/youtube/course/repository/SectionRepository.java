package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Section;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends PagingAndSortingRepository<Section, Long> {

}
