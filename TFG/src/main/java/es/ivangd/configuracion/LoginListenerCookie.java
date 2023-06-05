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
        // Obtener el objeto HttpServletResponse para establecer la cookie en la respuesta
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        // Obtener la información de autenticación del evento
        Authentication authentication = event.getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            // Obtener los detalles del usuario autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // Sanitizar el nombre de usuario para usarlo como nombre de la cookie
            String sanitizedUsername = sanitizeCookieName(username);

            // Generar un identificador único para la cookie
            String uniqueId = generateUniqueId();

            // Crear la cookie y establecer sus propiedades
            Cookie cookie = new Cookie(sanitizedUsername, uniqueId);
            cookie.setMaxAge(3600); // Duración de la cookie en segundos (aquí es 1 hora)
            cookie.setPath("/"); // Ruta de la cookie

            // Agregar la cookie a la respuesta
            response.addCookie(cookie);
        }
    }

    private String generateUniqueId() {
        // Generar un identificador único utilizando UUID
        return UUID.randomUUID().toString();
    }

    private String sanitizeCookieName(String name) {
        // Sanitizar el nombre de la cookie reemplazando "@" por "_"
        return name.replace("@", "_");
    }
}




