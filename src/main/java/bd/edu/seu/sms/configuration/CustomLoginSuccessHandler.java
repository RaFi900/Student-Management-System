package bd.edu.seu.sms.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.getSession().setAttribute("successMessage", "Successfully Signed in!");
       // response.sendRedirect("/dashboard");


        boolean isStudent = false;
        boolean isAdmin = false;

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("STUDENT")) {
                isStudent = true;
            } else if (authority.getAuthority().equals("ADMIN")) {
                isAdmin = true;
            }
        }
        if (isStudent) {
            response.sendRedirect("/courses");
        } else if (isAdmin) {
            response.sendRedirect("/dashboard");
        } else {
            response.sendRedirect("/login?error=role");
        }
    }
}

