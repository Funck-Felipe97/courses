package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.CourseService;
import academy.devdojo.youtube.course.endpoint.service.interfaces.SectionService;
import academy.devdojo.youtube.course.model.entity.Course;
import academy.devdojo.youtube.course.model.entity.Section;
import academy.devdojo.youtube.course.repository.SectionRepository;
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
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final CourseService courseService;

    @Override
    public Optional<Section> findByIdAndCourse(final Long id, final Long courseId) {
        Course course = courseService.findOne(courseId);
        return sectionRepository.findByIdAndCourse(id, course);
    }

    @Override
    public List<Section> findAllByCourse(final Long courseId) {
        Course course = courseService.findOne(courseId);
        return sectionRepository.findAllByCourse(course);
    }

    @Override
    public Section save(final Long courseId, final Section section) {
        Course course = courseService.findOne(courseId);
        section.setCourse(course);
        return save(section);
    }

    @Override
    public Section update(final Long id, final Long courseId, final Section section) {
        Section savedSection = findByIdAndCourse(id, courseId).orElseThrow(() -> new NoSuchElementException("Section not found with id: " + id));
        String[] ignoreProperties = new String[]{"id", "lessons", "course"};
        BeanUtils.copyProperties(section, savedSection, ignoreProperties);
        return save(savedSection);
    }

    @Override
    public void deleteByIdAndCouse(Long id, Long courseId) {
        Course course = courseService.findOne(courseId);
        sectionRepository.deleteByIdAndCourse(id, course);
    }

    @Override
    public JpaRepository getRepository() {
        return sectionRepository;
    }

    @Override
    public Class<Section> getClazz() {
        return Section.class;
    }

}
