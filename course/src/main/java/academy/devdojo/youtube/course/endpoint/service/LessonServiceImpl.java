package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.LessonService;
import academy.devdojo.youtube.course.model.entity.Lesson;
import academy.devdojo.youtube.course.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public JpaRepository getRepository() {
        return lessonRepository;
    }

}
