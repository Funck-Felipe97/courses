package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByAccount_id(Long account);

}
