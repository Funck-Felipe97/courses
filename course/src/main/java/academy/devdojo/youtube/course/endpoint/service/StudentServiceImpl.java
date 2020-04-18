package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.StudentService;
import academy.devdojo.youtube.course.model.entity.Student;
import academy.devdojo.youtube.course.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findByAccount(Long account) {
        return studentRepository.findByAccount_id(account);
    }

    @Override
    public Student findOneByAccount(Long account) {
        return findByAccount(account)
                .orElseThrow(() -> new NoSuchElementException("Student not found for account:" + account));
    }

    @Override
    public JpaRepository getRepository() {
        return studentRepository;
    }

    @Override
    public Class<Student> getClazz() {
        return Student.class;
    }

}
