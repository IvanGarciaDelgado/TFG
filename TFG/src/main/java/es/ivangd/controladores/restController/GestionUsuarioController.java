package es.ivangd.controladores.restController;

import es.ivangd.entidades.Usuario;
import es.ivangd.excepciones.ResourceNotFoundException;
import es.ivangd.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
class GestionUsuarioController {

    @Autowired
    private UsuarioRepository repositorio;

    // Este método sirve para listar todos los usuarios.
    @GetMapping("/usuarios")
    public List<Usuario> listarTodosLosUsuarios() {
        return repositorio.findAll();
    }

    // Este método sirve para guardar el usuario.
    @PostMapping("/usuarios")
    public Usuario guardarUsuarios(@RequestBody Usuario usuario) {
        return repositorio.save(usuario);
    }

    // Este método sirve para buscar un usuario.
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
        return ResponseEntity.ok(usuario);
    }

    // Este método sirve para actualizar un usuario.
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario detallesUsuario) {
        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));

        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setApellidos(detallesUsuario.getApellidos());
        usuario.setEmail(detallesUsuario.getEmail());

        Usuario usuarioActualizado = repositorio.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Este método sirve para eliminar un usuario.
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));

        repositorio.delete(usuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
