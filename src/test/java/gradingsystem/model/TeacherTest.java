package gradingsystem.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {

    @Test
    public void testGettersAndSetters() {

        String firstName = "John";
        String lastName = "Doe";
        String pesel = "12345678901";
        String dateOfBirth = "1980-01-01";
        String city = "City";
        String postalCode = "12345";
        String streetAndNumber = "Street 123";
        String phoneNumber = "123-456-789";


        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setPesel(pesel);
        teacher.setDateOfBirth(dateOfBirth);
        teacher.setCity(city);
        teacher.setPostalCode(postalCode);
        teacher.setStreetAndNumber(streetAndNumber);
        teacher.setPhoneNumber(phoneNumber);


        assertEquals(firstName, teacher.getFirstName());
        assertEquals(lastName, teacher.getLastName());
        assertEquals(pesel, teacher.getPesel());
        assertEquals(dateOfBirth, teacher.getDateOfBirth());
        assertEquals(city, teacher.getCity());
        assertEquals(postalCode, teacher.getPostalCode());
        assertEquals(streetAndNumber, teacher.getStreetAndNumber());
        assertEquals(phoneNumber, teacher.getPhoneNumber());


        String newFirstName = "Jane";
        String newLastName = "Smith";
        String newPesel = "98765432109";
        String newDateOfBirth = "1975-05-05";
        String newCity = "New City";
        String newPostalCode = "54321";
        String newStreetAndNumber = "New Street 456";
        String newPhoneNumber = "987-654-321";

        teacher.setFirstName(newFirstName);
        teacher.setLastName(newLastName);
        teacher.setPesel(newPesel);
        teacher.setDateOfBirth(newDateOfBirth);
        teacher.setCity(newCity);
        teacher.setPostalCode(newPostalCode);
        teacher.setStreetAndNumber(newStreetAndNumber);
        teacher.setPhoneNumber(newPhoneNumber);

        assertEquals(newFirstName, teacher.getFirstName());
        assertEquals(newLastName, teacher.getLastName());
        assertEquals(newPesel, teacher.getPesel());
        assertEquals(newDateOfBirth, teacher.getDateOfBirth());
        assertEquals(newCity, teacher.getCity());
        assertEquals(newPostalCode, teacher.getPostalCode());
        assertEquals(newStreetAndNumber, teacher.getStreetAndNumber());
        assertEquals(newPhoneNumber, teacher.getPhoneNumber());
    }
}
