package academy.devdojo.youtube.course.model.mapper;

import academy.devdojo.youtube.core.service.RequestMapper;
import academy.devdojo.youtube.core.service.ResponseMapper;
import academy.devdojo.youtube.course.model.dto.request.LessonRequest;
import academy.devdojo.youtube.course.model.dto.response.LessonResponse;
import academy.devdojo.youtube.course.model.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
public class LessonMapper implements ResponseMapper<Lesson, LessonResponse>, RequestMapper<Lesson, LessonRequest> {

    private final ModelMapper mapper;

    @Override
    public Lesson toEntity(final LessonRequest lessonRequest) {
        return mapper.map(lessonRequest, Lesson.class);
    }

    @Override
    public List<Lesson> toEntityList(final List<LessonRequest> lessonRequests) {
        return lessonRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public LessonResponse toResponse(final Lesson lesson) {
        return mapper.map(lesson, LessonResponse.class);
    }

    @Override
    public List<LessonResponse> toResponseList(final List<Lesson> lessons) {
        return lessons.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
