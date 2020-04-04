package academy.devdojo.youtube.course.endpoint.controller;

import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.endpoint.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/v1/admin/courses")
@Api("Endpoints to manage courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @ApiOperation("List all available courses")
    public ResponseEntity<Iterable<Course>> findAll(Pageable pageable) {
        return new ResponseEntity(courseService.findAll(pageable), HttpStatus.OK);
    }

}