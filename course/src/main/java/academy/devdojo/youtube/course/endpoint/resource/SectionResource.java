package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.core.event.ResourceCreatedEvent;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SectionService;
import academy.devdojo.youtube.course.model.dto.request.SectionRequest;
import academy.devdojo.youtube.course.model.dto.response.SectionResponse;
import academy.devdojo.youtube.course.model.entity.Section;
import academy.devdojo.youtube.course.model.mapper.SectionMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@ExposesResourceFor(SectionResponse.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/v1/courses/{courseId}/sections")
@Api("Endpoints to manage sections of courses")
public class SectionResource {

    private final SectionService sectionService;
    private final ApplicationEventPublisher publisher;
    private final SectionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SectionResponse>> findAllByCourse(@PathVariable final Long courseId) {
        return ResponseEntity.ok(toResponseList(sectionService.findAllByCourse(courseId)));
    }

    @PostMapping
    public ResponseEntity<SectionResponse> save(@PathVariable final Long courseId, @RequestBody @Valid SectionRequest section,
                                        final HttpServletResponse response) {
        Section savedSection = sectionService.save(courseId, toEntity(section));
        publisher.publishEvent(new ResourceCreatedEvent(savedSection.getId(), response));
        return new ResponseEntity(toResponse(savedSection), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long courseId, @PathVariable final Long id) {
        Optional<Section> section = sectionService.findByIdAndCourse(id, courseId);
        return section.isPresent() ? ResponseEntity.ok(toResponse(section.get())) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionResponse> update(@PathVariable final Long courseId, @PathVariable final Long id, @RequestBody @Valid SectionRequest section) {
        Section updatedSection = sectionService.update(id, courseId, toEntity(section));
        return ResponseEntity.ok(toResponse(updatedSection));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long courseId, @PathVariable final Long id) {
        sectionService.deleteByIdAndCouse(id, courseId);
    }

    private List<SectionResponse> toResponseList(final List<Section> sections) {
        return mapper.toResponseList(sections);
    }

    private SectionResponse toResponse(final Section section) {
        return mapper.toResponse(section);
    }

    private Section toEntity(final SectionRequest sectionRequest) {
        return mapper.toEntity(sectionRequest);
    }

}
