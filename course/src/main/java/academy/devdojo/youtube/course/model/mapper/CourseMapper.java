package academy.devdojo.youtube.course.model.mapper;

import academy.devdojo.youtube.core.service.RequestMapper;
import academy.devdojo.youtube.core.service.ResponseMapper;
import academy.devdojo.youtube.course.model.dto.request.CourseRequest;
import academy.devdojo.youtube.course.model.dto.response.CourseResponse;
import academy.devdojo.youtube.course.model.entity.Course;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class CourseMapper implements RequestMapper<Course, CourseRequest>, ResponseMapper<Course, CourseResponse> {

    private final ModelMapper mapper;

    @Override
    public Course toEntity(CourseRequest courseRequest) {
        return mapper.map(courseRequest, Course.class);
    }

    @Override
    public List<Course> toEntityList(List<CourseRequest> courseRequests) {
        return courseRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public CourseResponse toResponse(Course course) {
        final CourseResponse courseResponse = mapper.map(course, CourseResponse.class);
        courseResponse.addLinks(course.getId());
        return courseResponse;
    }

    @Override
    public List<CourseResponse> toResponseList(List<Course> courses) {
        return courses.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
