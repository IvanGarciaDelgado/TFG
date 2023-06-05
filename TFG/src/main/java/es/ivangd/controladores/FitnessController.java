package es.ivangd.controladores;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging.
@Data // Genera Getter y Setter, equals y hashCode y un constructor que inicializa todos los campos finales.
@Controller
public class FitnessController {

    // Este método mapeado a la ruta "/" muestra la página de inicio (landing page).
    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    // Este método mapeado a la ruta "/proveedores" muestra la página de proveedores.
    @GetMapping("/proveedores")
    public String proveedoresPage() {
        return "proveedores";
    }
}
