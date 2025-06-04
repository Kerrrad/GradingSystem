package gradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gradingsystem.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByPesel(String pesel);

//zmiany
Optional<Student> findByEmail(String email);

    List<Student> findByStudentClass_Name(String className);
}
