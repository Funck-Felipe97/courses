package academy.devdojo.youtube.course.model.mapper;

import academy.devdojo.youtube.core.service.RequestMapper;
import academy.devdojo.youtube.core.service.ResponseMapper;
import academy.devdojo.youtube.course.model.dto.request.SectionRequest;
import academy.devdojo.youtube.course.model.dto.response.SectionResponse;
import academy.devdojo.youtube.course.model.entity.Section;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SectionMapper implements ResponseMapper<Section, SectionResponse>, RequestMapper<Section, SectionRequest> {

    private final ModelMapper mapper;

    @Override
    public Section toEntity(final SectionRequest sectionRequest) {
        return mapper.map(sectionRequest, Section.class);
    }

    @Override
    public List<Section> toEntityList(final List<SectionRequest> sectionRequests) {
        return sectionRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public SectionResponse toResponse(final Section section) {
        final SectionResponse sectionResponse = mapper.map(section, SectionResponse.class);
        sectionResponse.addLinks(section.getCourse().getId());
        return sectionResponse;
    }

    @Override
    public List<SectionResponse> toResponseList(final List<Section> sections) {
        return sections.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
