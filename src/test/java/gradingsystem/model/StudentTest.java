package gradingsystem.model;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testGettersAndSetters() {

        StudentClass studentClass = new StudentClass();
        String firstName = "John";
        String lastName = "Doe";
        String pesel = "12345678901";
        String dateOfBirth = "2000-01-01";
        String city = "City";
        String postalCode = "12345";
        String streetAndNumber = "Street 123";
        String phoneNumber = "123-456-789";

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentClass(studentClass);
        student.setPesel(pesel);
        student.setDateOfBirth(dateOfBirth);
        student.setCity(city);
        student.setPostalCode(postalCode);
        student.setStreetAndNumber(streetAndNumber);
        student.setPhoneNumber(phoneNumber);


        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertEquals(studentClass, student.getStudentClass());
        assertEquals(pesel, student.getPesel());
        assertEquals(dateOfBirth, student.getDateOfBirth());
        assertEquals(city, student.getCity());
        assertEquals(postalCode, student.getPostalCode());
        assertEquals(streetAndNumber, student.getStreetAndNumber());
        assertEquals(phoneNumber, student.getPhoneNumber());

        StudentClass newStudentClass = new StudentClass();
        String newFirstName = "Jane";
        String newLastName = "Smith";
        String newPesel = "98765432109";
        String newDateOfBirth = "1995-05-05";
        String newCity = "New City";
        String newPostalCode = "54321";
        String newStreetAndNumber = "New Street 456";
        String newPhoneNumber = "987-654-321";

        student.setFirstName(newFirstName);
        student.setLastName(newLastName);
        student.setStudentClass(newStudentClass);
        student.setPesel(newPesel);
        student.setDateOfBirth(newDateOfBirth);
        student.setCity(newCity);
        student.setPostalCode(newPostalCode);
        student.setStreetAndNumber(newStreetAndNumber);
        student.setPhoneNumber(newPhoneNumber);

        assertEquals(newFirstName, student.getFirstName());
        assertEquals(newLastName, student.getLastName());
        assertEquals(newStudentClass, student.getStudentClass());
        assertEquals(newPesel, student.getPesel());
        assertEquals(newDateOfBirth, student.getDateOfBirth());
        assertEquals(newCity, student.getCity());
        assertEquals(newPostalCode, student.getPostalCode());
        assertEquals(newStreetAndNumber, student.getStreetAndNumber());
        assertEquals(newPhoneNumber, student.getPhoneNumber());
    }
}
