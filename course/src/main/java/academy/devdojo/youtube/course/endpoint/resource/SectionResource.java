package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.core.event.ResourceCreatedEvent;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SectionService;
import academy.devdojo.youtube.course.model.entity.Section;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/v1/courses/{courseId}/sections")
@Api("Endpoints to manage sections of courses")
public class SectionResource {

    private final SectionService sectionService;
    private final ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<Section>> findAllByCourse(@PathVariable final Long courseId) {
        return ResponseEntity.ok(sectionService.findAllByCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<Section> save(@PathVariable final Long courseId, @RequestBody @Valid Section section,
                                        final HttpServletResponse response) {
        Section savedSection = sectionService.save(courseId, section);
        publisher.publishEvent(new ResourceCreatedEvent(section.getId(), response));
        return new ResponseEntity(savedSection, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long courseId, @PathVariable final Long id) {
        Optional<Section> section = sectionService.findByIdAndCourse(id, courseId);
        return section.isPresent() ? ResponseEntity.ok(section.get()) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> update(@PathVariable final Long courseId, @PathVariable final Long id, @RequestBody @Valid Section section) {
        return ResponseEntity.ok(sectionService.update(id, courseId, section));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long courseId, @PathVariable final Long id) {
        sectionService.deleteByIdAndCouse(id, courseId);
    }

}
