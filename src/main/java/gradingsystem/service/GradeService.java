package gradingsystem.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gradingsystem.model.Grade;
import gradingsystem.repository.GradeRepository;

import java.util.List;
    @Service
    public class GradeService {

        private final GradeRepository gradeRepository;

        @Autowired
        public GradeService(GradeRepository gradeRepository) {
            this.gradeRepository = gradeRepository;
        }

        public List<Grade> getAllGrades() {
            return gradeRepository.findAll();
        }

        public Grade getGradeById(Long id) {
            return gradeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ocena o takim id nie została znaleziona"));
        }

        public Grade createGrade(Grade grade) {
            return gradeRepository.save(grade);
        }

        public Grade updateGrade(Long id, Grade grade) {
            Grade existingGrade = gradeRepository.findById(grade.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Ocena o takim id nie została znaleziona"));
            existingGrade.setGrade(grade.getGrade());
            existingGrade.setDate(grade.getDate());
            return gradeRepository.save(existingGrade);
        }

        public void deleteGrade(Long id) {
            gradeRepository.deleteById(id);
        }


        public List<Grade> createGrades(List<Grade> grades) {
            return gradeRepository.saveAll(grades);
        }

        //do wszystkich ocen
        public List<Grade> getGradesByStudentId(Long Id){
            return gradeRepository.findByStudent_Id(Id);
        }



    }
