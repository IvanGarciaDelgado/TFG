package es.ivangd.interfaces;

import es.ivangd.dto.UsuarioRegistroDTO;
import es.ivangd.entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioInterfaz extends UserDetailsService { // Hago un extends del UserDetailsService por su uso en la clase de seguridad.

    public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO);
}
