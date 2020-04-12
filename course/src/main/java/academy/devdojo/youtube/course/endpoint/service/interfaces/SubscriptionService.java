package academy.devdojo.youtube.course.endpoint.service.interfaces;

import academy.devdojo.youtube.core.service.GenericService;
import academy.devdojo.youtube.course.model.entity.Subscription;
import academy.devdojo.youtube.course.model.entity.SubscriptionID;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SubscriptionService extends GenericService<Subscription, SubscriptionID> {

    @Transactional(propagation = Propagation.REQUIRED)
    void register(Long courseId);

}
