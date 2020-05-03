package academy.devdojo.youtube.core.service;

import java.util.List;

public interface RequestMapper<Entity, Request> {

    Entity toEntity(Request request);

    List<Entity> toEntityList(List<Request> requests);

}
