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
    public Section toEntity(SectionRequest sectionRequest) {
        return mapper.map(sectionRequest, Section.class);
    }

    @Override
    public List<Section> toEntityList(List<SectionRequest> sectionRequests) {
        return sectionRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public SectionResponse toResponse(Section section) {
        return mapper.map(section, SectionResponse.class);
    }

    @Override
    public List<SectionResponse> toResponseList(List<Section> sections) {
        return sections.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
