package bd.edu.seu.sms.audit;


import bd.edu.seu.sms.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return Optional.of("");
        }
        Object principal = auth.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            return Optional.of(user.getName());
        }
        return Optional.of(auth.getName());
    }
}
