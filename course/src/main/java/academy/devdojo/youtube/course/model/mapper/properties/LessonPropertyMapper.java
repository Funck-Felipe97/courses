package academy.devdojo.youtube.course.model.mapper.properties;

import academy.devdojo.youtube.course.model.dto.response.LessonResponse;
import academy.devdojo.youtube.course.model.entity.Lesson;
import org.modelmapper.PropertyMap;

public class LessonPropertyMapper extends PropertyMap<Lesson, LessonResponse> {

    @Override
    protected void configure() {
        map().setLessonId(source.getId());
    }

}
