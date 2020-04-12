package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.course.endpoint.service.interfaces.LessonService;
import academy.devdojo.youtube.course.model.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/courses/{courseId}/sections/{sectionId}/lessons")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LessonResource {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> findAll(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId) {
        return ResponseEntity.ok(lessonService.findByCourseAndSection(courseId, sectionId));
    }

    @PostMapping
    public ResponseEntity<Lesson> save(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @RequestBody @Valid final Lesson lesson) {
        return new ResponseEntity(lessonService.save(lesson, courseId, sectionId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @PathVariable final Long id) {
        Optional<Lesson> lesson = lessonService.findByIdAndSectionAndCourse(id, sectionId, courseId);
        return lesson.isPresent() ? ResponseEntity.ok(lesson.get()) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @PathVariable final Long id,
            @RequestBody @Valid final Lesson lesson) {
        return ResponseEntity.ok(lessonService.update(id, lesson, sectionId, courseId));
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @PathVariable final Long id) {
        lessonService.deleteByIdAndSectionAndCourse(id, sectionId, courseId);
    }

}
