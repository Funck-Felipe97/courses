package academy.devdojo.youtube.core.service;

import java.util.List;

public interface ResponseMapper<Entity, Response> {

    Response toResponse(Entity entity);

    List<Response> toResponseList(List<Entity> entities);

}
