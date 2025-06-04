package gradingsystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import gradingsystem.model.Teacher;
import gradingsystem.repository.TeacherRepository;
import gradingsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeacherServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testSaveTeacher() {

        Teacher teacherToSave = new Teacher();
        teacherToSave.setPassword("password"); // Raw password
        teacherToSave.setPesel("12345678901");


        when(teacherRepository.existsByPesel("12345678901")).thenReturn(false);
        when(teacherRepository.save(teacherToSave)).thenReturn(teacherToSave);


        Teacher result = teacherService.save(teacherToSave);


        assertThat(result.getPassword()).isNotEqualTo("password");
        assertThat(result.getRoles()).isEqualTo(2);
        verify(teacherRepository, times(1)).save(teacherToSave);
    }

    @Test
    public void testSaveTeacher_NonUniquePesel() {

        Teacher teacherToSave = new Teacher();
        teacherToSave.setPassword("password"); // Raw password
        teacherToSave.setPesel("12345678901");


        when(teacherRepository.existsByPesel("12345678901")).thenReturn(true);


        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            teacherService.save(teacherToSave);
        });


        assertThat(exception.getMessage()).isEqualTo("Numer PESEL już istnieje w bazie danych.");
        verify(teacherRepository, never()).save(teacherToSave);
    }

    @Test
    public void testGetAllTeachers() {

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher());


        when(teacherRepository.findAll()).thenReturn(teachers);


        List<Teacher> result = teacherService.getAllTeacherss();


        assertThat(result).isEqualTo(teachers);
    }

    @Test
    public void testGetTeacherById() {

        Long teacherId = 1L;
        Teacher teacher = new Teacher();


        when(teacherRepository.findTeacherById(teacherId)).thenReturn(Optional.of(teacher));


        Teacher result = teacherService.getTeachersById(teacherId);


        assertThat(result).isEqualTo(teacher);
    }

    @Test
    public void testGetTeacherByEmail() {

        String email = "teacher@example.com";
        Teacher teacher = new Teacher();


        when(teacherRepository.findTeacherByEmail(email)).thenReturn(Optional.of(teacher));

        Teacher result = teacherService.getTeachersByEmail(email);


        assertThat(result).isEqualTo(teacher);
    }
}
