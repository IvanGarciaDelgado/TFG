package es.ivangd.controladores.restController;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CookieController {

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        // Crear una nueva cookie con nombre "miCookie" y valor "valorDeMiCookie"
        Cookie cookie = new Cookie("miCookie", "valorDeMiCookie");
        cookie.setMaxAge(3600); // Establecer la duración de la cookie en segundos (aquí es 1 hora)
        cookie.setPath("/"); // Establecer la ruta de la cookie como la raíz del dominio
        response.addCookie(cookie); // Agregar la cookie a la respuesta HTTP

        return "Cookie establecida";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue("miCookie") String miCookie) {
        // Obtener el valor de la cookie "miCookie" usando la anotación @CookieValue
        return "Valor de miCookie: " + miCookie;
    }
}

