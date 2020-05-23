package academy.devdojo.youtube.course.model.mapper;

import academy.devdojo.youtube.core.service.RequestMapper;
import academy.devdojo.youtube.core.service.ResponseMapper;
import academy.devdojo.youtube.course.model.dto.request.StudentRequest;
import academy.devdojo.youtube.course.model.dto.response.StudentResponse;
import academy.devdojo.youtube.course.model.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class StudentMapper implements ResponseMapper<Student, StudentResponse>, RequestMapper<Student, StudentRequest> {

    private final ModelMapper mapper;

    @Override
    public Student toEntity(final StudentRequest studentRequest) {
        return mapper.map(studentRequest, Student.class);
    }

    @Override
    public List<Student> toEntityList(final List<StudentRequest> studentRequests) {
        return studentRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public StudentResponse toResponse(final Student student) {
        return mapper.map(student, StudentResponse.class);
    }

    @Override
    public List<StudentResponse> toResponseList(final List<Student> students) {
        return students.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
