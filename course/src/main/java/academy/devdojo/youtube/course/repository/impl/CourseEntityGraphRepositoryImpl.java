package academy.devdojo.youtube.course.repository.impl;

import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.repository.CourseEntityGraphRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CourseEntityGraphRepositoryImpl implements CourseEntityGraphRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> findAllWithGraph() {
        EntityGraph entityGraph = entityManager.getEntityGraph("courses-with-sections-graph");
        return entityManager
                .createQuery("select c from Course c", Course.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

}
