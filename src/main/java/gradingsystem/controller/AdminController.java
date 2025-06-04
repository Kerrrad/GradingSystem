package gradingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gradingsystem.model.Student;
import gradingsystem.model.StudentClass;
import gradingsystem.model.Subject;
import gradingsystem.model.Teacher;
import gradingsystem.service.StudentClassService;
import gradingsystem.service.StudentService;
import gradingsystem.service.SubjectService;
import gradingsystem.service.TeacherService;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/admin")
    String Home() {
        return "/admin/admin";
    }


    @GetMapping("/admin/add-student")
    String AddStudent(Model model) {

        Student student = new Student();
        List<StudentClass> availableClasses = studentClassService.getAllClasses();

        model.addAttribute("student", student);
        model.addAttribute("availableClasses", availableClasses);
        return "admin/add-student";
    }

    @GetMapping("/admin/add-teacher")
    String AddTeacher() {
        return "admin/add-teacher";
    }

    @PostMapping("admin/add-student")
    public String addStudent(@ModelAttribute Student student, RedirectAttributes model, @RequestParam("studentClass.id") Long studentClassId) {
        try {
            StudentClass studentClass = studentClassService.getClassById(studentClassId);
            student.setStudentClass(studentClass);
            studentService.save(student);
            model.addFlashAttribute("message", "Student został pomyślnie dodany");
        } catch (IllegalArgumentException e) {
            model.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addFlashAttribute("error", "Wystąpił nieoczekiwany błąd.");
        }



        return "redirect:/admin/add-student";

    }

    @PostMapping("admin/add-teacher")
    public String addTeacher(@ModelAttribute Teacher teacher, RedirectAttributes model) {
        try {
            teacherService.save(teacher);
            model.addFlashAttribute("message", "Nauczyciel został dodany pomyślnie!");
        } catch (IllegalArgumentException e) {
            model.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addFlashAttribute("error", "Wystąpił nieoczekiwany błąd.");
        }

        return "redirect:/admin/add-teacher";
    }




    //Przedmioty
    @GetMapping("/admin/add-subject")
    String AddSubject(Model model) {
        List<StudentClass> availableClasses = studentClassService.getAllClasses();
        List<Teacher> availableTeachers = teacherService.getAllTeacherss();


        model.addAttribute("availableClasses", availableClasses);
        model.addAttribute("availableTeachers", availableTeachers);

        return "admin/add-subject";
    }

    @PostMapping("admin/add-subject")
    public String addStudent(@ModelAttribute Subject subject, RedirectAttributes model, @RequestParam("studentClass.id") Long studentClassId,@RequestParam("teacher") Long teacherId) {
        StudentClass studentClass;
        try {
            studentClass = studentClassService.getClassById(studentClassId);
            Teacher teacher = teacherService.getTeachersById(teacherId);
            subject.setStudentClass(studentClass);
            subject.setTeacher(teacher);

            subjectService.save(subject);
            model.addFlashAttribute("message", "Przedmiot został pomyślnie dodany");
        } catch (IllegalArgumentException e) {
            model.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addFlashAttribute("error", "Wystąpił nieoczekiwany błąd.");
        }

        return "redirect:/admin/add-subject";
    }

    //Klasy
    @GetMapping("/admin/add-class")
    String addClass(Model model) {
        StudentClass studentClass = new StudentClass();
        model.addAttribute("studentClass", studentClass);
        return "admin/add-class";
    }

    @PostMapping("/admin/add-class")
    public String addClass(@ModelAttribute StudentClass studentClass, RedirectAttributes model) {

        Long studentClassId = studentClassService.getClassByName(studentClass.getName());
        if(studentClassId == null) {
            studentClassService.saveClass(studentClass);
            model.addFlashAttribute("message", "Klasa została dodana pomyślnie!");
        }
        else {
            model.addFlashAttribute("error", "Klasa o podaniej nazwie już istnieje!");
        }
        return "redirect:/admin/add-class";
    }


}
