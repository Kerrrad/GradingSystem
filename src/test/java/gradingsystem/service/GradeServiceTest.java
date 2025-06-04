package gradingsystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import gradingsystem.model.Grade;
import gradingsystem.repository.GradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void testGetAllGrades() {
        // Przygotowanie danych testowych
        List<Grade> grades = new ArrayList<>();
        // Dodaj testowe oceny do listy
        when(gradeRepository.findAll()).thenReturn(grades);

        // Wywołanie metody w serwisie
        List<Grade> result = gradeService.getAllGrades();

        // Sprawdzenie wyniku
        assertThat(result).isEqualTo(grades);
    }

    @Test
    public void testGetGradeById() {
        // Przygotowanie danych testowych
        Long gradeId = 1L;
        Grade grade = new Grade();
        grade.setId(gradeId);
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(grade));

        // Wywołanie metody w serwisie
        Grade result = gradeService.getGradeById(gradeId);

        // Sprawdzenie wyniku
        assertThat(result).isEqualTo(grade);
    }

    @Test
    public void testCreateGrade() {
        // Przygotowanie danych testowych
        Grade gradeToCreate = new Grade();
        when(gradeRepository.save(gradeToCreate)).thenReturn(gradeToCreate);

        // Wywołanie metody w serwisie
        Grade result = gradeService.createGrade(gradeToCreate);

        // Sprawdzenie wyniku
        assertThat(result).isEqualTo(gradeToCreate);
    }

    @Test
    public void testUpdateGrade() {

        Long gradeId = 1L;
        Grade existingGrade = new Grade();
        existingGrade.setId(gradeId);

        Grade updatedGrade = new Grade();
        updatedGrade.setId(gradeId);
        updatedGrade.setGrade(4);

        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(existingGrade));
        when(gradeRepository.save(existingGrade)).thenReturn(updatedGrade);


        Grade result = gradeService.updateGrade(gradeId, updatedGrade);


        assertThat(result).isEqualTo(updatedGrade);
        assertThat(existingGrade.getGrade()).isEqualTo(updatedGrade.getGrade());
    }

    @Test
    public void testDeleteGrade() {

        Long gradeId = 1L;


        gradeService.deleteGrade(gradeId);


        verify(gradeRepository, times(1)).deleteById(gradeId);
    }

    @Test
    public void testCreateGrades() {

        List<Grade> gradesToCreate = new ArrayList<>();
        when(gradeRepository.saveAll(gradesToCreate)).thenReturn(gradesToCreate);


        List<Grade> result = gradeService.createGrades(gradesToCreate);


        assertThat(result).isEqualTo(gradesToCreate);
    }

    @Test
    public void testGetGradesByStudentId() {

        Long studentId = 1L;
        List<Grade> grades = new ArrayList<>();

        when(gradeRepository.findByStudent_Id(studentId)).thenReturn(grades);


    }
}