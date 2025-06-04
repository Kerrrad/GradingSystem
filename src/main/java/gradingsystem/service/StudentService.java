package gradingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import gradingsystem.model.Student;
import gradingsystem.repository.StudentRepository;
import gradingsystem.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Student save(Student student) {
        String hashedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(hashedPassword);
        student.setRoles(1);
        validatePeselUniqueness(student.getPesel());
        return studentRepository.save(student);
    }

    private void validatePeselUniqueness(String pesel) {
        if (studentRepository.existsByPesel(pesel)) {
            throw new IllegalArgumentException("Numer PESEL już istnieje w bazie danych.");
        }
    }

//zmiany

    public List<Student> getStudentsInSameClass(String email) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (student.getStudentClass() != null) {
                String className = student.getStudentClass().getName();
                return studentRepository.findByStudentClass_Name(className);
            }
        }

        return Collections.emptyList();
    }


    public List<Student> getStudentsByClassName(String className) {
        return studentRepository.findByStudentClass_Name(className);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

}
