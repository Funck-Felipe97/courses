package academy.devdojo.youtube.course.repository;

import academy.devdojo.youtube.course.model.entity.Subscription;
import academy.devdojo.youtube.course.model.entity.SubscriptionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionID> {
}
