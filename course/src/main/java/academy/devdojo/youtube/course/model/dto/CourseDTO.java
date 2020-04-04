package academy.devdojo.youtube.course.model.dto;

import academy.devdojo.youtube.course.model.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private List<Section> sections;

}
