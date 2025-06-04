package gradingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gradingsystem.model.StudentClass;
import gradingsystem.model.Subject;
import gradingsystem.repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {



    @Autowired
    private SubjectRepository subjectRepository;
    

    
    public Subject save(Subject subject) {

        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectsByClassId(Long classId) {
        return subjectRepository.findByStudentClass_Id(classId);
    }

    public List<Subject> getSubjectsByClassIdAndTeacherId(Long classId, Long teacherId) {
        return subjectRepository.findByStudentClassIdAndTeacherId(classId, teacherId);
    }
//    public List<StudentClass> findClassesByTeacherId(Long teacherId) {
//        List<Subject> subjects = subjectRepository.findByTeacherId(teacherId);
//
//        // Przemapuj listę obiektów Subject na listę klas
//        return subjects.stream()
//                .map(Subject::getStudentClass)
//                .collect(Collectors.toList());
//    }
public List<StudentClass> findClassesByTeacherId(Long teacherId) {
    List<Subject> subjects = subjectRepository.findByTeacherId(teacherId);

    // Przemapuj listę obiektów Subject na listę klas, używając distinct
    return subjects.stream()
            .map(Subject::getStudentClass)
            .distinct()
            .collect(Collectors.toList());
}

//    public List<Subject> getSubjectsByStudentClass(StudentClass studentClass) {
//        List<Subject> subjects = subjectRepository.findByStudentClass(studentClass);
//        return subjects;
//    }
}
