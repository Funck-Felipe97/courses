package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.core.event.ResourceCreatedEvent;
import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SubscriptionService;
import academy.devdojo.youtube.course.model.dto.request.CourseRequest;
import academy.devdojo.youtube.course.model.dto.response.CourseResponse;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.mapper.CourseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@ExposesResourceFor(CourseResponse.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/v1/courses")
@Api("Endpoints to manage courses")
public class CourseResource {

    private final CourseMapper courseMapper;
    private final CourseService courseService;
    private final SubscriptionService subscriptionService;
    private final ApplicationEventPublisher publisher;

    @GetMapping
    @ApiOperation("List all available courses")
    public ResponseEntity<List<CourseResponse>> findAll() {
        return new ResponseEntity(toResponseList(courseService.findAllWithGraph()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ApiOperation("Save new course")
    public ResponseEntity<CourseResponse> save(@RequestBody @Valid final CourseRequest course, final HttpServletResponse response) {
        Course savedCourse = courseService.save(toEntity(course));
        publisher.publishEvent(new ResourceCreatedEvent(savedCourse.getId(), response));
        return new ResponseEntity(toResponse(savedCourse), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find course by id")
    public ResponseEntity<?> findById(@PathVariable final Long id) {
        Optional<Course> course = courseService.findById(id);
        return course.isPresent() ? ResponseEntity.ok(toResponse(course.get())) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update course by id")
    public ResponseEntity<CourseResponse> update(@PathVariable final Long id, @RequestBody @Valid final CourseRequest course) {
        return ResponseEntity.ok(toResponse(courseService.update(id, toEntity(course))));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete course by id")
    public void deleteById(@PathVariable final Long id) {
        courseService.deleteById(id);
    }

    @PostMapping("/{id}/subscribe")
    @ApiOperation("Subscribe logged stundent in course")
    public void subscribe(@PathVariable("id") final Long courseId) {
        subscriptionService.register(courseId);
    }

    private Course toEntity(final CourseRequest request) {
        return courseMapper.toEntity(request);
    }

    private List<CourseResponse> toResponseList(final List<Course> courses) {
        return courseMapper.toResponseList(courses);
    }

    private CourseResponse toResponse(final Course course) {
        return courseMapper.toResponse(course);
    }

}
