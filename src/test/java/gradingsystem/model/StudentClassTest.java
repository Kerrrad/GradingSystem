package gradingsystem.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentClassTest {

    @Test
    public void testGettersAndSetters() {

        String className = "Class A";

        StudentClass studentClass = new StudentClass();
        studentClass.setName(className);

        assertEquals(className, studentClass.getName());

        String newClassName = "Class B";
        studentClass.setName(newClassName);

        assertEquals(newClassName, studentClass.getName());
    }
}
