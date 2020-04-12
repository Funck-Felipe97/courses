package academy.devdojo.youtube.core.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface GenericService<Entity, ID> {

    @Transactional(readOnly = true)
    default List<Entity> findAll() {
        return getRepository().findAll();
    }

    @Transactional(readOnly = true)
    default Entity findOne(ID id) {
        return findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("%s with id %s not found", getClazz(), id)));
    }

    @Transactional(readOnly = true)
    default Optional<Entity> findById(ID id) {
        return getRepository().findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default Entity save(Entity entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default void delete(Entity entity) {
        getRepository().delete(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default void deleteById(ID id) {
        Entity entity = findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("%s with id %s not found", getClazz(), id)));

        getRepository().delete(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default Entity update(ID id, Entity entity) {
        Entity savedEntity = getRepository().findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("%s with id %s not found", getClazz().getSimpleName(), id)));

        BeanUtils.copyProperties(entity, savedEntity, "id");

        return save(savedEntity);
    }

    JpaRepository<Entity, ID> getRepository();

    Class<Entity> getClazz();

}
