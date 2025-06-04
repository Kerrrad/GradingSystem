package gradingsystem.model;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {

    @Test
    public void testGettersAndSetters() {

        Student student = new Student();
        Teacher teacher = new Teacher();
        Subject subject = new Subject();
        int gradeValue = 90;
        Date date = new Date();


        Grade grade = new Grade(student, teacher, subject, gradeValue, date);


        assertEquals(student, grade.getStudent());
        assertEquals(teacher, grade.getTeacher());
        assertEquals(subject, grade.getSubject());
        assertEquals(gradeValue, grade.getGrade());
        assertEquals(date, grade.getDate());


        Student newStudent = new Student();
        Teacher newTeacher = new Teacher();
        Subject newSubject = new Subject();
        int newGradeValue = 80;
        Date newDate = new Date();

        grade.setStudent(newStudent);
        grade.setTeacher(newTeacher);
        grade.setSubject(newSubject);
        grade.setGrade(newGradeValue);
        grade.setDate(newDate);

        assertEquals(newStudent, grade.getStudent());
        assertEquals(newTeacher, grade.getTeacher());
        assertEquals(newSubject, grade.getSubject());
        assertEquals(newGradeValue, grade.getGrade());
        assertEquals(newDate, grade.getDate());
    }

    @Test
    public void testGetStudentId() {

        Student student = new Student();
        Grade grade = new Grade(student, new Teacher(), new Subject(), 80, new Date());


        assertEquals(student.getId(), grade.getStudentId());
    }

    @Test
    public void testGetSubjectId() {

        Subject subject = new Subject();
        Grade grade = new Grade(new Student(), new Teacher(), subject, 80, new Date());

        assertEquals(subject.getId(), grade.getSubjectId());
    }
}
