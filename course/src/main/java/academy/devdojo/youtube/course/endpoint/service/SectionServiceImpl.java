package academy.devdojo.youtube.course.endpoint.service;

import academy.devdojo.youtube.course.endpoint.service.interfaces.SectionService;
import academy.devdojo.youtube.course.model.entity.Section;
import academy.devdojo.youtube.course.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public JpaRepository getRepository() {
        return sectionRepository;
    }

}
