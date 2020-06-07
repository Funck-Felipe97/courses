package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.core.event.ResourceCreatedEvent;
import academy.devdojo.youtube.course.endpoint.service.interfaces.LessonService;
import academy.devdojo.youtube.course.model.dto.request.LessonRequest;
import academy.devdojo.youtube.course.model.dto.response.LessonResponse;
import academy.devdojo.youtube.course.model.entity.Lesson;
import academy.devdojo.youtube.course.model.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.hateoas.ExposesResourceFor;
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

@ExposesResourceFor(LessonResponse.class)
@RestController
@RequestMapping("/v1/courses/{courseId}/sections/{sectionId}/lessons")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LessonResource {

    private final LessonService lessonService;
    private final ApplicationEventPublisher publisher;
    private final LessonMapper lessonMapper;

    @GetMapping
    public ResponseEntity<List<LessonResponse>> findAll(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId) {
        return ResponseEntity.ok(toResponseList(lessonService.findByCourseAndSection(courseId, sectionId)));
    }

    @PostMapping
    public ResponseEntity<LessonResponse> save(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @RequestBody @Valid final LessonRequest lesson,
            final HttpServletResponse response) {
        Lesson savedLesson = lessonService.save(toEntity(lesson), courseId, sectionId);
        publisher.publishEvent(new ResourceCreatedEvent(savedLesson.getId(), response));
        return new ResponseEntity(toResponse(savedLesson), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @PathVariable final Long id) {
        Optional<Lesson> lesson = lessonService.findByIdAndSectionAndCourse(id, sectionId, courseId);
        return lesson.isPresent() ? ResponseEntity.ok(toResponse(lesson.get())) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @PathVariable final Long id,
            @RequestBody @Valid final LessonRequest lesson) {
        Lesson updateLesson = lessonService.update(id, toEntity(lesson), sectionId, courseId);
        return ResponseEntity.ok(toResponse(updateLesson));
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable final Long courseId,
            @PathVariable final Long sectionId,
            @PathVariable final Long id) {
        lessonService.deleteByIdAndSectionAndCourse(id, sectionId, courseId);
    }

    private List<LessonResponse> toResponseList(final List<Lesson> lessons) {
        return lessonMapper.toResponseList(lessons);
    }

    private LessonResponse toResponse(final Lesson lesson) {
        return lessonMapper.toResponse(lesson);
    }

    private Lesson toEntity(final LessonRequest lessonRequest) {
        return lessonMapper.toEntity(lessonRequest);
    }

}
