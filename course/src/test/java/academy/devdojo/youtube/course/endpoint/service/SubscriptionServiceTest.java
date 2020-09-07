package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.StudentService;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.entity.Student;
import academy.devdojo.youtube.course.model.entity.Subscription;
import academy.devdojo.youtube.course.repository.SubscriptionRepository;
import academy.devdojo.youtube.security.service.SecurityContextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SubscriptionServiceTest {

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private CourseService courseService;

    @Mock
    private SecurityContextService securityContextService;

    @Captor
    private ArgumentCaptor<Subscription> subscriptionCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Throws RuntimeException when student already subscribed on course")
    void shouldThrowsRuntimeException() {
        when(subscriptionRepository.findById(any())).thenReturn(Optional.of(new Subscription()));
        assertThrows(RuntimeException.class, () -> subscriptionService.register(1L));
    }

    @Test
    @DisplayName("Return subscription class for generic service")
    void shouldReturnEntityClass() {
        assertTrue(subscriptionService.getClazz().equals(Subscription.class));
    }

    @Test
    @DisplayName("Return subscription repository for generic service")
    void shouldReturnCourseRepository() {
        assertTrue(subscriptionService.getRepository() instanceof SubscriptionRepository);
    }

    @Test
    @DisplayName("Register a student in a existent course")
    void shouldRegisterSubscriber() {
        // a
        var course = Course.builder().id(1L).build();
        var student = Student.builder().id(1L).build();

        when(securityContextService.getAuthenticationAccountId()).thenReturn(1L);
        when(studentService.findOneByAccount(1L)).thenReturn(student);
        when(courseService.findOne(1L)).thenReturn(course);

        // a
        subscriptionService.register(1L);

        // a
        verify(subscriptionRepository).saveAndFlush(subscriptionCaptor.capture());
        assertAll("subscription", () -> {
            assertEquals(course, subscriptionCaptor.getValue().getId().getCourse());
            assertEquals(student, subscriptionCaptor.getValue().getId().getStudent());
        });
    }
}
