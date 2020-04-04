package academy.devdojo.youtube.course.endpoint.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenericService<Entity, ID> {

    default List<Entity> findAll() {
        return getRepository().findAll();
    }

    default Optional<Entity> findById(ID id) {
        return getRepository().findById(id);
    }

    default Entity save(Entity entity) {
        return getRepository().save(entity);
    }

    default void delete(Entity entity) {
        getRepository().delete(entity);
    }

    default void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    JpaRepository<Entity, ID> getRepository();
}
