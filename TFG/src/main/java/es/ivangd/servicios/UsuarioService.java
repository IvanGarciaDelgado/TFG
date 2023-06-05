package es.ivangd.servicios;

import es.ivangd.dto.UsuarioRegistroDTO;
import es.ivangd.entidades.Role;
import es.ivangd.entidades.Usuario;
import es.ivangd.interfaces.UsuarioInterfaz;
import es.ivangd.repositorios.RoleRepository;
import es.ivangd.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging, para poder añadir la notación log.info
@RequiredArgsConstructor // Genera un constructor para atributos como final no inicializados y anotados con @notNull, también sirve para el ahorro del autowired.
@Service
public class UsuarioService implements UsuarioInterfaz {

    private final UsuarioRepository repositorio; // Permite inyectar unas dependencias (inyección de constructor) con otras dentro de Spring, enlazar elementos.

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Override
    public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO) {
        Role roleUser = roleRepository.findFirstByNombre("ROLE_USER");
        Usuario usuario = new Usuario(registroDTO.getNombre(),registroDTO.getApellidos(),registroDTO.getMovil(),registroDTO.getEmail(),passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(roleUser));
        return repositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByEmail(email);
        if(usuario == null) {
            throw  new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(),usuario.getPassword(),mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    public Usuario registrar(Usuario u) { // Método para almacenar usuario con contraseña encriptada. save: Guarda una entidad determinada.
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return repositorio.save(u);
    }
//
//    public Usuario findById(long id) { // Método para buscar usuario por su ID
//        return repositorio.findById(id).orElse(null);
//    }
//
    public Usuario buscarPorEmail(String email) { // Método para buscar a usuario por su Email.
        return repositorio.findByEmail(email);
    }

    @Transactional // Es un metadato que especifica que una interfaz, clase o método debe tener semántica transaccional.
    public void deleteAll() { // deleteAll: Elimina todas las entidades gestionadas por el repositorio.
        repositorio.deleteAllInBatch(); // deleteAllInBatch: Elimina todas las entidades en una llamada por lotes.
    }

}
