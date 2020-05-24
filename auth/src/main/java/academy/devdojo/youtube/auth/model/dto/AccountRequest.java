package academy.devdojo.youtube.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    @NotNull(message = "The field 'username' is mandatory")
    private String username;
    @NotNull(message = "The field 'password' is mandatory")
    private String password;

}
