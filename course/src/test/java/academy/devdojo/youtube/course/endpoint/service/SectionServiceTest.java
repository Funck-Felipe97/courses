package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.entity.Section;
import academy.devdojo.youtube.course.repository.SectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SectionServiceTest {

    @InjectMocks
    private SectionServiceImpl sectionService;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private CourseService courseService;

    @Captor
    private ArgumentCaptor<Section> sectionCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should find optional section by sectionId and Couse")
    void shouldFindByIdAndCourse() {
        // given
        var course = Course.builder().id(1L).build();
        var section = Section.builder().id(1L).build();

        when(courseService.findOne(1L)).thenReturn(course);
        when(sectionRepository.findByIdAndCourse(1L, course)).thenReturn(Optional.of(section));

        // when
        var sectionOptional = sectionService.findByIdAndCourse(1L, 1L);

        // then
        verify(courseService).findOne(1L);
        verify(sectionRepository).findByIdAndCourse(1L, course);
        assertThat(sectionOptional.get(), equalTo(section));
    }

    @Test
    @DisplayName("Should throws NoSuchElementException when section not found")
    void shouldThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> sectionService.findOneByIdAndCourse(1L, 1L));
    }

    @Test
    @DisplayName("Should find section by sectionId and Couse")
    void shouldFindOneByIdAndCourse() {
        // given
        var course = Course.builder().id(1L).build();
        var section = Section.builder().id(1L).build();

        when(courseService.findOne(1L)).thenReturn(course);
        when(sectionRepository.findByIdAndCourse(1L, course)).thenReturn(Optional.of(section));

        // when
        var section2 = sectionService.findOneByIdAndCourse(1L, 1L);

        // then
        verify(courseService).findOne(1L);
        verify(sectionRepository).findByIdAndCourse(1L, course);
        assertThat(section2, equalTo(section));
    }

    @Test
    @DisplayName("Should find all sections by Couse")
    void shouldFindAllByCourse() {
        // given
        var course = Course.builder().id(1L).build();
        var section = Section.builder().id(1L).build();

        when(courseService.findOne(1L)).thenReturn(course);
        when(sectionRepository.findAllByCourse(course)).thenReturn(List.of(section));

        // when
        var sections = sectionService.findAllByCourse(1L);

        // then
        verify(courseService).findOne(1L);
        verify(sectionRepository).findAllByCourse(course);
        assertThat(sections.size(), equalTo(1));
        assertThat(sections.get(0), equalTo(section));
    }

    @Test
    @DisplayName("Should save a new Section")
    void shouldSaveSection() {
        // given
        var course = Course.builder().id(1L).build();
        var section = new Section();

        when(courseService.findOne(1L)).thenReturn(course);
        doAnswer(invocation -> {
            Section s = invocation.getArgument(0);
            s.setId(1l);
            return s;
        }).when(sectionRepository).saveAndFlush(section);

        // when
        var savedSection = sectionService.save(1L, section);

        // then
        verify(courseService).findOne(1L);
        verify(sectionRepository).saveAndFlush(section);
        assertThat(savedSection, equalTo(section));
        assertThat(savedSection.getId(), equalTo(1L));
        assertThat(savedSection.getCourse(), equalTo(course));
    }

    @Test
    @DisplayName("Should update a existing section")
    void shouldUpdateSection() {
        // given
        var course = Course.builder().id(1L).build();
        var section = Section.builder().id(1L).name("Section a").build();
        var updatedSection = Section.builder().name("Section B").build();

        when(courseService.findOne(1L)).thenReturn(course);
        when(sectionRepository.findByIdAndCourse(1L, course)).thenReturn(Optional.of(section));

        // when
        sectionService.update(1L, 1L, updatedSection);

        // then
        verify(courseService).findOne(1L);
        verify(sectionRepository).saveAndFlush(sectionCaptor.capture());
        var savedSection = sectionCaptor.getValue();

        assertAll("Section", () -> {
            assertThat(savedSection.getId(), equalTo(1L));
            assertThat(savedSection.getName(), equalTo("Section B"));
        });
    }

    @Test
    @DisplayName("Should throws NoSuchElementException when section not found")
    void shouldThrowsNoSuchElementExceptionInUpdate() {
        assertThrows(NoSuchElementException.class, () -> sectionService.update(1L, 1L, new Section()));
    }

    @Test
    @DisplayName("Return section class for generic service")
    void shouldReturnEntityClass() {
        assertTrue(sectionService.getClazz().equals(Section.class));
    }

    @Test
    @DisplayName("Return section repository for generic service")
    void shouldReturnCourseRepository() {
        assertTrue(sectionService.getRepository() instanceof SectionRepository);
    }

    @Test
    @DisplayName("Delete course by id and Couse")
    void shouldDeleteByIdAndCouse() {
        // given
        var course = Course.builder().id(1L).build();
        when(courseService.findOne(1L)).thenReturn(course);

        // when
        sectionService.deleteByIdAndCouse(1L, 1L);

        // then
        verify(sectionRepository).deleteByIdAndCourse(1L, course);
    }

}
