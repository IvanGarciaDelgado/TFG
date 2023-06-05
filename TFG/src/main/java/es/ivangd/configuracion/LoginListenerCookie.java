package es.ivangd.configuracion;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class LoginListenerCookie implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        Authentication authentication = event.getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String sanitizedUsername = sanitizeCookieName(username);
            String uniqueId = generateUniqueId();


            Cookie cookie = new Cookie(sanitizedUsername, uniqueId);
            cookie.setMaxAge(3600); // Duración de la cookie en segundos (aquí es 1 hora)
            cookie.setPath("/"); // Ruta de la cookie
            response.addCookie(cookie);
        }
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    private String sanitizeCookieName(String name) {
        return name.replace("@", "_");
    }
}



