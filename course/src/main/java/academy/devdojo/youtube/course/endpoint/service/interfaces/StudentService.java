package academy.devdojo.youtube.course.endpoint.service.interfaces;

import academy.devdojo.youtube.core.service.GenericService;
import academy.devdojo.youtube.course.model.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StudentService extends GenericService<Student, Long> {

    @Transactional(readOnly = true)
    Optional<Student> findByAccount(Long account);

    @Transactional(readOnly = true)
    Student findOneByAccount(Long account);

}
