package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.model.entity.Student;
import academy.devdojo.youtube.course.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should find optional student by account")
    void shouldfindByAccount() {
        // given
        var student = Student.builder().id(1L).build();
        when(studentRepository.findByAccount_id(2L)).thenReturn(Optional.of(student));

        // when
        var student2 = studentService.findByAccount(2L);

        // then
        verify(studentRepository).findByAccount_id(2L);
        assertThat(student2.get(), equalTo(student));
    }

    @Test
    @DisplayName("Should find student by account")
    void shouldfindOneByAccount() {
        // given
        var student = Student.builder().id(1L).build();
        when(studentRepository.findByAccount_id(2L)).thenReturn(Optional.of(student));

        // when
        var student2 = studentService.findOneByAccount(2L);

        // then
        verify(studentRepository).findByAccount_id(2L);
        assertThat(student2, equalTo(student));
    }

    @Test
    @DisplayName("Throws NoSuchElementException when student not found")
    void shouldThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> studentService.findOneByAccount(1L));
    }

    @Test
    @DisplayName("Return student class for generic service")
    void shouldReturnEntityClass() {
        assertTrue(studentService.getClazz().equals(Student.class));
    }

    @Test
    @DisplayName("Return student repository for generic service")
    void shouldReturnCourseRepository() {
        assertTrue(studentService.getRepository() instanceof StudentRepository);
    }

}
