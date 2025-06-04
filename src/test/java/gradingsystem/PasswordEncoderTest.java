package gradingsystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";  // <- tu wpisz swoje hasło
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
