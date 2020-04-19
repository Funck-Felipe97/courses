package academy.devdojo.youtube.core.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class ResourceCreatedListener<ID> {

    @EventListener
    public void addIdOnHeaderLocation(ResourceCreatedEvent<ID> event) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(event.getId())
                .toUri();

        event.getResponse().setHeader("Location", uri.toASCIIString());
    }

}
