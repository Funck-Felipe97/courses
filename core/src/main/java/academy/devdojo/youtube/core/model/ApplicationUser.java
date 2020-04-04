package academy.devdojo.youtube.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationUser implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false)
    private String username;

    @ToString.Exclude
    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "The field 'role' is mandatory")
    @Column(nullable = false)
    private String role = "USER";

    public ApplicationUser(ApplicationUser applicationUser) {
        this.id = applicationUser.id;
        this.username = applicationUser.username;
        this.password = applicationUser.password;
        this.role = applicationUser.role;
    }
}
