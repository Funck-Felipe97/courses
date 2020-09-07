package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.StudentService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SubscriptionService;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.entity.Student;
import academy.devdojo.youtube.course.model.entity.Subscription;
import academy.devdojo.youtube.course.model.entity.SubscriptionID;
import academy.devdojo.youtube.course.repository.SubscriptionRepository;
import academy.devdojo.youtube.security.service.SecurityContextService;
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
    private final StudentService studentService;
    private final CourseService courseService;
    private final SecurityContextService securityContext;

    @Override
    public void register(Long courseId) {
        Course course = courseService.findOne(courseId);
        Student student = studentService.findOneByAccount(securityContext.getAuthenticationAccountId());
        SubscriptionID subscriptionID = new SubscriptionID(course, student);
        findById(subscriptionID).ifPresentOrElse(subscription -> {
            throw new RuntimeException("Student already subscribed in course: " + courseId);
        }, () -> {
            Subscription subscription = new Subscription();
            subscription.setId(subscriptionID);
            save(subscription);
        });
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
