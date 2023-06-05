package es.ivangd.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {

    // Este método mapeado a la ruta "/login" muestra la página de inicio de sesión.
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }
}
