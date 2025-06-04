package gradingsystem.config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_0")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_1")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/student");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_2")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/teacher");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException();
            }
        });
    }
}
