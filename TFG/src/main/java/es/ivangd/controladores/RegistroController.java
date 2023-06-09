package es.ivangd.controladores;

import es.ivangd.dto.UsuarioRegistroDTO;
import es.ivangd.servicios.UsuarioService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging.
@Data // Genera Getter y Setter, equals y hashCode y un constructor que inicializa todos los campos finales.
@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;

    // Este método anotado con @ModelAttribute inicializa un nuevo objeto UsuarioRegistroDTO y lo agrega al modelo.
    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    // Este método mapeado a la ruta "/registro" muestra la página de registro.
    @GetMapping
    public String mostrarRegistro(@RequestParam(name = "exito", required = false) String exito, Model model) {
        if (exito != null) {
            model.addAttribute("exito", "¡Registro exitoso! Por favor, inicie sesión.");
        }
        return "registro";
    }

    // Este método mapeado a la ruta "/registro" maneja la solicitud de registro de una cuenta de usuario.
    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, Model model) {
        try {
            usuarioService.guardarUsuario(registroDTO);
            return "redirect:/registro?exito";
        } catch (DataIntegrityViolationException ex) {
            String mensajeError = "El correo electrónico ya está en uso";
            model.addAttribute("error", mensajeError);
            return "registro";
        }
    }
}



