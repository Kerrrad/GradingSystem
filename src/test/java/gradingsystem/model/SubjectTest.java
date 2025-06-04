package gradingsystem.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    @Test
    public void testGettersAndSetters() {

        String subjectName = "Math";
        Teacher teacher = new Teacher();
        StudentClass studentClass = new StudentClass();


        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subject.setTeacher(teacher);
        subject.setStudentClass(studentClass);


        assertEquals(subjectName, subject.getSubjectName());
        assertEquals(teacher, subject.getTeacher());
        assertEquals(studentClass, subject.getStudentClass());

        String newSubjectName = "English";
        Teacher newTeacher = new Teacher();
        StudentClass newStudentClass = new StudentClass();

        subject.setSubjectName(newSubjectName);
        subject.setTeacher(newTeacher);
        subject.setStudentClass(newStudentClass);

        assertEquals(newSubjectName, subject.getSubjectName());
        assertEquals(newTeacher, subject.getTeacher());
        assertEquals(newStudentClass, subject.getStudentClass());
    }
}
