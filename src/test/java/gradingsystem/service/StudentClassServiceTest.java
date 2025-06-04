package gradingsystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import gradingsystem.model.StudentClass;
import gradingsystem.repository.StudentClassRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentClassServiceTest {

    @Mock
    private StudentClassRepository studentClassRepository;

    @InjectMocks
    private StudentClassService studentClassService;

    @Test
    public void testGetAllClasses() {

        List<StudentClass> classes = new ArrayList<>();

        when(studentClassRepository.findAll()).thenReturn(classes);


        List<StudentClass> result = studentClassService.getAllClasses();


        assertThat(result).isEqualTo(classes);
    }

    @Test
    public void testGetClassById() {

        Long classId = 1L;
        StudentClass studentClass = new StudentClass();
        studentClass.setId(classId);
        when(studentClassRepository.findById(classId)).thenReturn(Optional.of(studentClass));


        StudentClass result = studentClassService.getClassById(classId);


        assertThat(result).isEqualTo(studentClass);
    }

    @Test
    public void testGetClassByName() {

        String className = "ClassA";
        StudentClass studentClass = new StudentClass();
        studentClass.setId(1L);
        when(studentClassRepository.findByName(className)).thenReturn(Optional.of(studentClass));


        Long result = studentClassService.getClassByName(className);


        assertThat(result).isEqualTo(studentClass.getId());
    }

    @Test
    public void testGetClassByName_NotFound() {

        String className = "NonexistentClass";
        when(studentClassRepository.findByName(className)).thenReturn(Optional.empty());


        Long result = studentClassService.getClassByName(className);


        assertThat(result).isNull();
    }

    @Test
    public void testSaveClass() {

        StudentClass studentClassToSave = new StudentClass();
        when(studentClassRepository.save(studentClassToSave)).thenReturn(studentClassToSave);


        studentClassService.saveClass(studentClassToSave);


        verify(studentClassRepository, times(1)).save(studentClassToSave);
    }
}
