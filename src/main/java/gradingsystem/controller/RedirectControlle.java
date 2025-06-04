package gradingsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class RedirectControlle {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request, Authentication auth) {
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_0"))) {
            return "redirect:/admin";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_1"))) {
            return "redirect:/student";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_2"))) {
            return "redirect:/teacher";
        } else {
            return "redirect:/login";
        }
    }
}
