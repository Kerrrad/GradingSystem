package gradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gradingsystem.model.StudentClass;
import gradingsystem.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByTeacherId(Long teacherId);

    List<Subject> findByStudentClassIdAndTeacherId(Long studentClassId, Long teacherId);

    List<Subject> findByStudentClass_Id(Long classId);


    List<Subject> findByStudentClass(Optional<StudentClass> studentClass);

}

