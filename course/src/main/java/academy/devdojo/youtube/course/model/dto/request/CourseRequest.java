package academy.devdojo.youtube.course.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    @NotBlank(message = "Course name can not be null")
    private String name;

    @NotBlank(message = "Course description can not be null")
    private String description;

    private String imageUrl;
    private Duration duration;

}
