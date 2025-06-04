package gradingsystem.controller;

import gradingsystem.model.Grade;
import gradingsystem.model.Student;
import gradingsystem.model.StudentClass;
import gradingsystem.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import gradingsystem.model.*;
import gradingsystem.service.GradeService;
import gradingsystem.service.StudentService;
import gradingsystem.service.SubjectService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    @Autowired
    GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;
    @GetMapping("/student")
    String Home(ModelMap model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<Student> studentOptional = studentService.getStudentByEmail(userEmail);
        if (studentOptional.isPresent()) {
            StudentClass studentClass = studentOptional.get().getClassName();
            Long studentClassId = studentClass.getId();
            List<Grade> grades = gradeService.getGradesByStudentId(studentOptional.get().getId());
            List<Grade> latestGrades = grades.stream()
                    .sorted(Comparator.comparing(Grade::getDate).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            List<Subject> subjects = subjectService.getSubjectsByClassId(studentClassId);
            Map<String, Double> averageGrades = calculateAverageGrades(grades);
            Optional<Map.Entry<String, Double>> bestSubjectEntry = averageGrades.entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue));
            Optional<Map.Entry<String, Double>> worstSubjectEntry = averageGrades.entrySet()
                    .stream()
                    .min(Comparator.comparing(Map.Entry::getValue));
            if (bestSubjectEntry.isPresent()) {
                String bestSubjectName = bestSubjectEntry.get().getKey();
                Double bestSubjectAverage = bestSubjectEntry.get().getValue();
                Map<String, String> bestSubjectMap = Map.of(bestSubjectName, bestSubjectAverage.toString());
                model.addAttribute("bestSubject", bestSubjectMap);
            } else {
                model.addAttribute("bestSubject", Map.of("-", "-")); // Domyślne wartości, gdy brak ocen
            }
            if (worstSubjectEntry.isPresent()) {
                String worstSubjectName = worstSubjectEntry.get().getKey();
                Double worstSubjectAverage = worstSubjectEntry.get().getValue();
                Map<String, String> worstSubjectMap = Map.of(worstSubjectName, worstSubjectAverage.toString());
                model.addAttribute("worstSubject", worstSubjectMap);
            } else {
                model.addAttribute("worstSubject", Map.of("-", "-")); // Domyślne wartości, gdy brak ocen
            }



            model.addAttribute("latestGrades",latestGrades);


        }

        return "student/student";
    }

    @GetMapping("/student/list")
    String List(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        List<Student> className = studentService.getStudentsInSameClass(userEmail);
        className.sort(Comparator.comparing(Student::getLastName));
        model.addAttribute("students", className);
        return "student/student-list";
    }

    @GetMapping("/student/oceny")
    String Grade(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<Student> studentOptional = studentService.getStudentByEmail(userEmail);
        if (studentOptional.isPresent()) {
            StudentClass studentClass = studentOptional.get().getClassName();
            Long studentClassId = studentClass.getId();
            List<Subject> subjects = subjectService.getSubjectsByClassId(studentClassId);
            List<Grade> grades = gradeService.getGradesByStudentId(studentOptional.get().getId());
            Map<String, Double> averageGrades = calculateAverageGrades(grades);
            model.addAttribute("averages",averageGrades);
            //List<Subject> subjects = subjectService.getSubjectsByStudentClass(studentClass);
            model.addAttribute("subjects",subjects);
            model.addAttribute("grades",grades);

        }



        return "student/student-grade";
    }

    public static Map<String, Double> calculateAverageGrades(List<Grade> grades) {
        return grades.stream()
                .collect(Collectors.groupingBy(
                    grade -> grade.getSubject().getSubjectName(),
                    Collectors.collectingAndThen(
                        Collectors.averagingInt(Grade::getGrade),
                        avg -> Math.round(avg * 100.0) / 100.0
                    )
                ));
    }



}
