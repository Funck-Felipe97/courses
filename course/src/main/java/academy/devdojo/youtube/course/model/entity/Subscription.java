package academy.devdojo.youtube.course.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course_subscription")
@ToString
@EqualsAndHashCode
public class Subscription {

    @EmbeddedId
    private SubscriptionID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
