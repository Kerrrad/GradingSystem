package gradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gradingsystem.model.Teacher;

import java.util.Optional;

public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
    boolean existsByPesel(String pesel);

    Optional<Teacher> findTeacherById(Long id);
    Optional<Teacher> findTeacherByEmail(String email);

}
