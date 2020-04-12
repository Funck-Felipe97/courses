package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.SubscriptionService;
import academy.devdojo.youtube.course.model.entity.Subscription;
import academy.devdojo.youtube.course.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void register(Long courseId) {

    }

    @Override
    public JpaRepository getRepository() {
        return subscriptionRepository;
    }

    @Override
    public Class<Subscription> getClazz() {
        return Subscription.class;
    }

}
