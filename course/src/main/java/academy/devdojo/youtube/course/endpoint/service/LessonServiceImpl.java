package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.LessonService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SectionService;
import academy.devdojo.youtube.course.model.entity.Lesson;
import academy.devdojo.youtube.course.model.entity.Section;
import academy.devdojo.youtube.course.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class LessonServiceImpl implements LessonService {

    private final SectionService sectionService;
    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> findByCourseAndSection(final Long courseId, final Long sectionId) {
        Section section = getSectionByIdAndCourseId(courseId, sectionId);
        return lessonRepository.findAllBySection(section);
    }

    @Override
    public Lesson save(Lesson lesson, Long courseId, Long sectionId) {
        Section section = getSectionByIdAndCourseId(courseId, sectionId);
        lesson.setSection(section);
        return save(lesson);
    }

    @Override
    public Optional<Lesson> findByIdAndSectionAndCourse(Long id, Long sectionId, Long courseId) {
        Section section = sectionService.findOneByIdAndCourse(sectionId, courseId);
        return lessonRepository.findByIdAndSection(id, section);
    }

    @Override
    public Lesson update(Long id, Lesson lesson, Long sectionId, Long courseId) {
        Lesson savedLesson = findByIdAndSectionAndCourse(id, sectionId, courseId).orElseThrow(() ->
                new NoSuchElementException("Lesson not found with id: " + sectionId));
        String[] ignoreProperties = new String[]{"id", "section"};
        BeanUtils.copyProperties(lesson, savedLesson, ignoreProperties);
        return save(savedLesson);
    }

    @Override
    public void deleteByIdAndSectionAndCourse(Long id, Long sectionId, Long courseId) {
        Section section = sectionService.findOneByIdAndCourse(sectionId, courseId);
        lessonRepository.deleteByIdAndSection(id, section);
    }

    private Section getSectionByIdAndCourseId(Long courseId, Long sectionId) {
        return sectionService.findByIdAndCourse(sectionId, courseId).orElseThrow(() ->
                new NoSuchElementException("Section not found with id: " + sectionId));
    }

    @Override
    public JpaRepository getRepository() {
        return lessonRepository;
    }

    @Override
    public Class<Lesson> getClazz() {
        return Lesson.class;
    }

}
