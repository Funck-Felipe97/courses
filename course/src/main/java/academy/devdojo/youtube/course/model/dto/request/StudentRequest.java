package academy.devdojo.youtube.course.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    @NotBlank(message = "The student name can not be empty")
    private String name;

    @NotBlank(message = "The student last name can not be empty")
    private String lastName;

    @NotNull(message = "The student account can not be null")
    private AccountRequest account;

}
