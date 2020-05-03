package academy.devdojo.youtube.course.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {

    @NotBlank(message = "Lesson name can not be null")
    private String name;
    private String videoUrl;

}
