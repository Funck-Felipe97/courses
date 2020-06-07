package academy.devdojo.youtube.course.model.dto.response;

import academy.devdojo.youtube.course.endpoint.resource.LessonResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonResponse extends ResourceSupport {

    @EqualsAndHashCode.Include
    private Long lessonId;
    private String name;
    private String videoUrl;

    public void addLinks(Long courseId, Long sectionId) {
        addSelfLink(courseId, sectionId, this.lessonId);
        addUpdateLink(courseId, sectionId, this.lessonId);
        addDeleteLink(courseId, sectionId, this.lessonId);
    }

    public void addSelfLink(Long courseId, Long sectionId, Long lessonId) {
        add(linkTo(methodOn(LessonResource.class).findAll(courseId, sectionId))
                .slash(lessonId)
                .withSelfRel());
    }

    public void addUpdateLink(Long courseId, Long sectionId, Long lessonId) {
        add(linkTo(methodOn(LessonResource.class).findAll(courseId, sectionId))
                .slash(lessonId)
                .withRel("update"));
    }

    public void addDeleteLink(Long courseId, Long sectionId, Long lessonId) {
        add(linkTo(methodOn(LessonResource.class).findAll(courseId, sectionId))
                .slash(lessonId)
                .withRel("delete"));
    }

}
