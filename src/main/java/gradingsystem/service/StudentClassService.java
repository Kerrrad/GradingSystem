package gradingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gradingsystem.model.StudentClass;
import gradingsystem.repository.StudentClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;


    public List<StudentClass> getAllClasses() {
        return studentClassRepository.findAll();
    }


    public StudentClass getClassById(Long id) {
        return studentClassRepository.findById(id).orElse(null);
    }

public Long getClassByName(String name) {
    Optional<StudentClass> studentClassOptional = studentClassRepository.findByName(name);

    if (studentClassOptional.isPresent()) {
        return studentClassOptional.get().getId();
    } else {
        return null;
    }
}
    public void saveClass(StudentClass studentClass) {
        studentClassRepository.save(studentClass);
    }




}

