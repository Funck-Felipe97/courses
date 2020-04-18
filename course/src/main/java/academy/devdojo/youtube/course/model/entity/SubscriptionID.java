package academy.devdojo.youtube.course.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SubscriptionID implements Serializable {

    @NotNull
    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    @NotNull
    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

}
