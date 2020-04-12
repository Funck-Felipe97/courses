package academy.devdojo.youtube.course.model.entity;

import academy.devdojo.youtube.core.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true, value = "section")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lesson")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lesson implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String videoUrl;

    @JoinColumn(name = "section_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

}
