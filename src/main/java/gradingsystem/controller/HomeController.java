package gradingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import gradingsystem.model.User;
import gradingsystem.service.UserService;

import java.util.Optional;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String handleRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication.isAuthenticated())) {
            authentication.getName();
            Optional<User> user = userService.getUserByEmail(authentication.getName());
            if (user.isPresent()) {
                int role = user.get().getRoles();
                if (role == 0) {
                    return "redirect:/admin";
                } else if (role == 1) {
                    return "redirect:/student";
                } else {
                    return "redirect:/teacher";
                }

            }
        }
            return "redirect:/login";

    }
}
