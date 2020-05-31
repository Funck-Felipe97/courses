package academy.devdojo.youtube.course.model.mapper;

import academy.devdojo.youtube.core.service.RequestMapper;
import academy.devdojo.youtube.core.service.ResponseMapper;
import academy.devdojo.youtube.course.model.dto.request.CourseRequest;
import academy.devdojo.youtube.course.model.dto.response.CourseResponse;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.mapper.properties.CoursePropertyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper implements RequestMapper<Course, CourseRequest>, ResponseMapper<Course, CourseResponse> {

    private final ModelMapper mapper;
    private final EntityLinks entityLinks;

    public CourseMapper(ModelMapper mapper, EntityLinks entityLinks) {
        this.mapper = mapper;
        this.entityLinks = entityLinks;
        this.mapper.addMappings(new CoursePropertyMapper());
    }

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
        final Link selfLink = entityLinks.linkToSingleResource(CourseResponse.class, course.getId());
        courseResponse.add(selfLink);
        return courseResponse;
    }

    @Override
    public List<CourseResponse> toResponseList(List<Course> courses) {
        return courses.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
