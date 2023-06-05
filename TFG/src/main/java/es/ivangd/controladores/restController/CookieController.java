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
        Cookie cookie = new Cookie("miCookie", "valorDeMiCookie");
        cookie.setMaxAge(3600); // Duración de la cookie en segundos (aquí es 1 hora)
        cookie.setPath("/"); // Ruta de la cookie (aquí es la raíz del dominio)
        response.addCookie(cookie);

        return "Cookie establecida";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue("miCookie") String miCookie) {
        return "Valor de miCookie: " + miCookie;
    }
}
