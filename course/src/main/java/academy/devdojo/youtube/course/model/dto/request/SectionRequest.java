package academy.devdojo.youtube.course.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequest {

    @NotBlank(message = "Section name can not ben empty")
    private String name;

}
