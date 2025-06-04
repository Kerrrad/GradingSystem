package gradingsystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import gradingsystem.model.StudentClass;
import gradingsystem.model.Subject;
import gradingsystem.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    public void testSaveSubject() {

        Subject subjectToSave = new Subject();


        when(subjectRepository.save(subjectToSave)).thenReturn(subjectToSave);


        Subject result = subjectService.save(subjectToSave);


        assertThat(result).isEqualTo(subjectToSave);
        verify(subjectRepository, times(1)).save(subjectToSave);
    }

    @Test
    public void testGetSubjectsByClassId() {

        Long classId = 1L;
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());


        when(subjectRepository.findByStudentClass_Id(classId)).thenReturn(subjects);


        List<Subject> result = subjectService.getSubjectsByClassId(classId);


        assertThat(result).isEqualTo(subjects);
    }

    @Test
    public void testGetSubjectsByClassIdAndTeacherId() {

        Long classId = 1L;
        Long teacherId = 2L;
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());


        when(subjectRepository.findByStudentClassIdAndTeacherId(classId, teacherId)).thenReturn(subjects);


        List<Subject> result = subjectService.getSubjectsByClassIdAndTeacherId(classId, teacherId);


        assertThat(result).isEqualTo(subjects);
    }

    @Test
    public void testFindClassesByTeacherId() {

        Long teacherId = 2L;
        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject();
        subject1.setStudentClass(new StudentClass());
        subject1.getStudentClass().setName("ClassA");

        Subject subject2 = new Subject();
        subject2.setStudentClass(new StudentClass());
        subject2.getStudentClass().setName("ClassB");

        subjects.add(subject1);
        subjects.add(subject2);


        when(subjectRepository.findByTeacherId(teacherId)).thenReturn(subjects);


        List<StudentClass> result = subjectService.findClassesByTeacherId(teacherId);


        assertThat(result).containsExactlyInAnyOrder(subject1.getStudentClass(), subject2.getStudentClass());
    }
}
