package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CourseServiceTest {

    @InjectMocks
    CourseServiceImpl courseService;

    @Mock
    CourseRepository courseRepository;

    @Captor
    ArgumentCaptor<Course> courseCaptor;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should throws NoSuchElementException when course not found")
    void shouldThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> courseService.update(1l, new Course()));
    }

    @Test
    @DisplayName("Should update course by id and new course state")
    void shouldUpdateCourse() {
        // given
        var course = Course.builder().id(1L).createdAt(now()).lastUpdate(now()).description("Java advantage").name("Java").build();
        var updatedCourse = Course.builder().id(2L).description("Java advantage 2").name("Java 2").build();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // when
        courseService.update(1L, updatedCourse);

        // then
        verify(courseRepository).saveAndFlush(courseCaptor.capture());
        assertAll("course", () -> {
            Course updated = courseCaptor.getValue();
            assertEquals("Java advantage 2", updated.getDescription());
            assertEquals("Java 2", updated.getName());
            assertEquals("1", updated.getId().toString());
            assertNotNull(updated.getLastUpdate());
            assertNotNull(updated.getCreatedAt());
        });
    }

    @Test
    @DisplayName("Return course class for generic service")
    void shouldReturnEntityClass() {
        assertTrue(courseService.getClazz().equals(Course.class));
    }

    @Test
    @DisplayName("Return course repository for generic service")
    void shouldReturnCourseRepository() {
        assertTrue(courseService.getRepository() instanceof CourseRepository);
    }

    @Test
    @DisplayName("Return all courses using jpa entity graph impl")
    void shouldFindAllCoursesUsingEntityGraph() {
        courseService.findAllWithGraph();
        verify(courseRepository).findAllWithGraph();
    }

}
