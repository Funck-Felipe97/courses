package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Subscription;
import academy.devdojo.youtube.course.model.entity.SubscriptionID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, SubscriptionID> {
}
