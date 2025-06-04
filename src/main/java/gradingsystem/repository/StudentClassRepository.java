package gradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gradingsystem.model.StudentClass;

import java.util.Optional;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {




    Optional<StudentClass> findByName(String name);
}

