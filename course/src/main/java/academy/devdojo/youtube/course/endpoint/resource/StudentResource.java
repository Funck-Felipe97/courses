package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.core.event.ResourceCreatedEvent;
import academy.devdojo.youtube.course.endpoint.service.interfaces.StudentService;
import academy.devdojo.youtube.course.model.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/v1/students")
public class StudentResource {

    private final StudentService studentService;
    private final ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody @Valid final Student student, final HttpServletResponse response) {
        Student savedStudent = studentService.save(student);
        publisher.publishEvent(new ResourceCreatedEvent(savedStudent.getId(), response));
        return new ResponseEntity(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) {
        Optional<Student> student = studentService.findById(id);
        return student.isPresent() ? ResponseEntity.ok(student.get()) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable final Long id, @RequestBody final Student student) {
        return ResponseEntity.ok(studentService.update(id, student));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
        studentService.deleteById(id);
    }

}
