package academy.devdojo.youtube.course.endpoint.resource;

import academy.devdojo.youtube.core.event.ResourceCreatedEvent;
import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SubscriptionService;
import academy.devdojo.youtube.course.model.entity.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/v1/courses")
@Api("Endpoints to manage courses")
public class CourseResource {

    private final CourseService courseService;
    private final SubscriptionService subscriptionService;
    private final ApplicationEventPublisher publisher;

    @GetMapping
    @ApiOperation("List all available courses")
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity(courseService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Save new course")
    public ResponseEntity<Course> save(@RequestBody @Valid final Course course, final HttpServletResponse response) {
        Course savedCourse = courseService.save(course);
        publisher.publishEvent(new ResourceCreatedEvent(course.getId(), response));
        return new ResponseEntity(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find course by id")
    public ResponseEntity<?> findById(@PathVariable final Long id) {
        Optional<Course> course = courseService.findById(id);
        return course.isPresent() ? ResponseEntity.ok(course.get()) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update course by id")
    public ResponseEntity<Course> update(@PathVariable final Long id, @RequestBody @Valid final Course course) {
        return ResponseEntity.ok(courseService.update(id, course));
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

}
