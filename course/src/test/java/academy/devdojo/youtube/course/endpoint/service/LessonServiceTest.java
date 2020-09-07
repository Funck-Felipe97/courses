package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.SectionService;
import academy.devdojo.youtube.course.model.entity.Lesson;
import academy.devdojo.youtube.course.model.entity.Section;
import academy.devdojo.youtube.course.repository.LessonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LessonServiceTest {

    @InjectMocks
    private LessonServiceImpl lessonService;

    @Mock
    private SectionService sectionService;

    @Mock
    private LessonRepository lessonRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Throws NoSuchElementException when section not found with course id and section id")
    void shouldThrowsNoSuchElementExceptionWhenSectionNotFound() {
        assertThrows(NoSuchElementException.class, () -> lessonService.findByCourseAndSection(1L, 1l));
        assertThrows(NoSuchElementException.class, () -> lessonService.save(new Lesson(), 1L, 1l));
    }

    @Test
    void shouldFindByCourseAndSection() {
        // given
        var section = Section.builder().id(1L).build();
        var lesson = Lesson.builder().id(1L).section(section).build();

        when(sectionService.findByIdAndCourse(1L, 1L)).thenReturn(Optional.of(section));
        when(lessonRepository.findAllBySection(section)).thenReturn(List.of(lesson));

        // when
        var lessons = lessonService.findByCourseAndSection(1L, 1L);

        // then
        verify(lessonRepository).findAllBySection(section);
        assertAll("lessons", () -> {
            assertEquals(lessons.size(), 1);
            assertEquals(lessons.get(0), lesson);
        });
    }

    @Test
    @DisplayName("Should save a new lesson")
    void shouldSave() {
        // given
        var section = Section.builder().id(1L).build();
        var lesson = Lesson.builder().name("Lesson one").build();

        when(sectionService.findByIdAndCourse(1L, 1L)).thenReturn(Optional.of(section));
        doAnswer((answer) -> {
            Lesson l = answer.getArgument(0);
            l.setId(1L);
            return l;
        }).when(lessonRepository).saveAndFlush(lesson);

        // when
        var savedLesson = lessonService.save(lesson, 1L, 1L);

        // then
        verify(lessonRepository).saveAndFlush(lesson);
        assertAll("lesson", () -> {
            assertEquals("1", savedLesson.getId().toString());
            assertEquals(section, savedLesson.getSection());
        });
    }

    @Test
    @DisplayName("Should find lesson by id, section and course")
    void shouldFindByIdAndSectionAndCourse() {
        // given
        var section = Section.builder().id(1L).build();
        when(sectionService.findOneByIdAndCourse(1L, 1L)).thenReturn(section);

        // when
        lessonService.findByIdAndSectionAndCourse(1L, 1L, 1L);

        // then
        verify(sectionService).findOneByIdAndCourse(1L, 1L);
        verify(lessonRepository).findByIdAndSection(1L, section);
    }

    @Test
    @DisplayName("Return lesson class for generic service")
    void shouldReturnEntityClass() {
        assertTrue(lessonService.getClazz().equals(Lesson.class));
    }

    @Test
    @DisplayName("Return lesson repository for generic service")
    void shouldReturnCourseRepository() {
        assertTrue(lessonService.getRepository() instanceof LessonRepository);
    }

}
