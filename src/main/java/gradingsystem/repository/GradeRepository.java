package gradingsystem.repository;

import gradingsystem.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findBySubject_IdAndTeacher_Id(Long subjectId, Long teacherId);

    List<Grade> findByStudent_Id(Long studentId);


}





