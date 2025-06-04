package gradingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http .csrf(csrf -> csrf.disable()).authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/admin/**").hasRole("0")
                    .requestMatchers("/student/**").hasAnyRole("0", "1")
                    .requestMatchers("/teacher/**").hasAnyRole("0", "2")
                    .anyRequest().permitAll()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .permitAll()
            )
            .logout(logout ->
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
            );
    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
