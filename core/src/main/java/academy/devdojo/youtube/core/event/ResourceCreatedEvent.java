package academy.devdojo.youtube.core.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@Getter
@RequiredArgsConstructor
public class ResourceCreatedEvent<ID> {

    private final ID id;
    private final HttpServletResponse response;


}
