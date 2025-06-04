package gradingsystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import gradingsystem.model.Student;
import gradingsystem.model.StudentClass;
import gradingsystem.repository.StudentRepository;
import gradingsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testSaveStudent() {

        Student studentToSave = new Student();
        studentToSave.setPassword("password"); // Raw password
        studentToSave.setPesel("12345678901");


        when(studentRepository.existsByPesel("12345678901")).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(studentToSave);


        Student result = studentService.save(studentToSave);


        assertThat(result.getPassword()).isNotEqualTo("password");
        assertThat(result.getRoles()).isEqualTo(1);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testSaveStudent_NonUniquePesel() {

        Student studentToSave = new Student();
        studentToSave.setPassword("password"); // Raw password
        studentToSave.setPesel("12345678901");


        when(studentRepository.existsByPesel("12345678901")).thenReturn(true);


        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studentService.save(studentToSave);
        });


        assertThat(exception.getMessage()).isEqualTo("Numer PESEL już istnieje w bazie danych.");
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    public void testGetStudentsInSameClass() {

        String userEmail = "user@example.com";
        Student student = new Student();
        student.setStudentClass(new StudentClass());
        student.getStudentClass().setName("ClassA");


        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(userEmail);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        when(studentRepository.findByEmail(userEmail)).thenReturn(Optional.of(student));
        when(studentRepository.findByStudentClass_Name("ClassA")).thenReturn(Collections.singletonList(student));


        List<Student> result = studentService.getStudentsInSameClass(userEmail);


        assertThat(result).containsExactly(student);
    }

    @Test
    public void testGetStudentsByClassName() {

        String className = "ClassA";
        List<Student> students = new ArrayList<>();
        students.add(new Student());


        when(studentRepository.findByStudentClass_Name(className)).thenReturn(students);


        List<Student> result = studentService.getStudentsByClassName(className);


        assertThat(result).isEqualTo(students);
    }

    @Test
    public void testGetStudentByEmail() {

        String email = "user@example.com";
        Student student = new Student();

        when(studentRepository.findByEmail(email)).thenReturn(Optional.of(student));


        Optional<Student> result = studentService.getStudentByEmail(email);


        assertThat(result).isEqualTo(Optional.of(student));
    }
}
