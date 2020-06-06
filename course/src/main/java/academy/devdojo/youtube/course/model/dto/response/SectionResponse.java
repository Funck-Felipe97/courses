package academy.devdojo.youtube.course.model.dto.response;

import academy.devdojo.youtube.course.endpoint.resource.SectionResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionResponse extends ResourceSupport {

    @EqualsAndHashCode.Include
    private Long sectionId;
    private String name;
    private List<LessonResponse> lessons;

    public void addSelfLink(final Long courseId) {
        add(linkTo(methodOn(SectionResource.class).findAllByCourse(courseId))
                .slash(sectionId)
                .withSelfRel());
    }

}
