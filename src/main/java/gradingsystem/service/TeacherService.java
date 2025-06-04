package gradingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import gradingsystem.model.Teacher;
import gradingsystem.repository.TeacherRepository;
import gradingsystem.repository.UserRepository;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Teacher save(Teacher teacher) {
        String hashedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(hashedPassword);
        teacher.setRoles(2);
        validatePeselUniqueness(teacher.getPesel());
        return teacherRepository.save(teacher);
    }

    private void validatePeselUniqueness(String pesel) {
        if (teacherRepository.existsByPesel(pesel)) {
            throw new IllegalArgumentException("Numer PESEL już istnieje w bazie danych.");
        }
    }

    public List<Teacher> getAllTeacherss() {
        return teacherRepository.findAll();
    }

    public Teacher getTeachersById(Long id) {
        return teacherRepository.findTeacherById(id).orElse(null);
    }
    public Teacher getTeachersByEmail(String email) {
        return teacherRepository.findTeacherByEmail(email).orElse(null);
    }




}
