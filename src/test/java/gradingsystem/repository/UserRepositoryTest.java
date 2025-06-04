package gradingsystem.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import gradingsystem.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {

        String email = "test@example.com";
        String password = "password123";
        int roles = 1;


        User user = new User(email, password, roles);
        userRepository.save(user);


        Optional<User> foundUser = userRepository.findByEmail(email);


        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getEmail());
        assertEquals(password, foundUser.get().getPassword());
        assertEquals(roles, foundUser.get().getRoles());
    }
}