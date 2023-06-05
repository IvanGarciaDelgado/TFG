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

@Slf4j // Proporciona funciones de registro mediante la anotación log.info
@RequiredArgsConstructor // Crea un constructor para los campos marcados como 'final' y anotados con '@NotNull'
@Service // Indica que esta clase es un componente de servicio de Spring
public class UsuarioService implements UsuarioInterfaz {

    private final UsuarioRepository repositorio; // Repositorio para acceder y gestionar los datos de los usuarios

    private final PasswordEncoder passwordEncoder; // Codificador de contraseñas

    private final RoleRepository roleRepository; // Repositorio de roles

    @Override
    public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO) { // Método para guardar un usuario
        Role roleUser = roleRepository.findFirstByNombre("ROLE_USER"); // Obtener el rol de usuario
        Usuario usuario = new Usuario(registroDTO.getNombre(),registroDTO.getApellidos(),registroDTO.getMovil(),registroDTO.getEmail(),passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(roleUser)); // Crear un nuevo usuario con los datos proporcionados
        return repositorio.save(usuario); // Guardar el usuario en el repositorio
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // Método para cargar un usuario por su nombre de usuario (en este caso, el email)
        Usuario usuario = repositorio.findByEmail(email); // Buscar el usuario en el repositorio por su email
        if(usuario == null) {
            throw  new UsernameNotFoundException("Usuario o password inválidos"); // Lanzar una excepción si el usuario no se encuentra
        }
        return new User(usuario.getEmail(),usuario.getPassword(),mapearAutoridadesRoles(usuario.getRoles())); // Devolver una instancia de UserDetails con los datos del usuario encontrado
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles) { // Método para mapear los roles del usuario a autoridades de Spring Security
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    public Usuario registrar(Usuario u) { // Método para registrar un usuario con contraseña encriptada
        u.setPassword(passwordEncoder.encode(u.getPassword())); // Encriptar la contraseña antes de guardarla
        return repositorio.save(u); // Guardar el usuario en el repositorio
    }

    public Usuario buscarPorEmail(String email) { // Método para buscar un usuario por su email
        return repositorio.findByEmail(email); // Buscar el usuario en el repositorio por su email
    }

    @Transactional // Indica que este método debe ejecutarse en una transacción
    public void deleteAll() { // Método para eliminar todos los usuarios
        repositorio.deleteAllInBatch(); // Eliminar todos los usuarios en una llamada por lotes
    }

}

