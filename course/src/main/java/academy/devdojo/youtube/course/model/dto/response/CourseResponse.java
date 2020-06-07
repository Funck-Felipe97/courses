package academy.devdojo.youtube.course.model.dto.response;

import academy.devdojo.youtube.course.endpoint.resource.CourseResource;
import academy.devdojo.youtube.course.endpoint.resource.SectionResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse extends ResourceSupport {

    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private Long courseId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;
    private Duration duration;
    private List<SectionResponse> sections;

    public void addLinks(final Long courseId) {
        add(linkTo(methodOn(CourseResource.class).findById(courseId)).withSelfRel());
        add(linkTo(CourseResource.class).slash(courseId).withRel("delete"));
        add(linkTo(CourseResource.class).slash(courseId).withRel("update"));
        add(linkTo(CourseResource.class).slash(courseId).slash("subscribe").withRel("subscribe"));
        add(linkTo(methodOn(SectionResource.class).findAllByCourse(courseId)).withRel("sections"));
    }

}
