package gradingsystem.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGettersAndSetters() {

        String email = "test@example.com";
        String password = "password123";
        int roles = 1;


        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);


        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(roles, user.getRoles());


        String newEmail = "newtest@example.com";
        String newPassword = "newpassword456";
        int newRoles = 2;

        user.setEmail(newEmail);
        user.setPassword(newPassword);
        user.setRoles(newRoles);

        assertEquals(newEmail, user.getEmail());
        assertEquals(newPassword, user.getPassword());
        assertEquals(newRoles, user.getRoles());
    }
}
