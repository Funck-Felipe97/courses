package academy.devdojo.youtube.course.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class StudentResponse {

    private Long id;
    private String name;
    private String lastName;
    private AccountResponse account;

}
