package academy.devdojo.youtube.course.model.mapper.properties;

import academy.devdojo.youtube.course.model.dto.response.SectionResponse;
import academy.devdojo.youtube.course.model.entity.Section;
import org.modelmapper.PropertyMap;

public class SectionPropertyMapper extends PropertyMap<Section, SectionResponse> {

    @Override
    protected void configure() {
        map().setSectionId(source.getId());
    }

}
