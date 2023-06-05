package es.ivangd.seguridad;

import es.ivangd.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor // Crea un constructor que incluye todos los campos marcados como 'final'
@Configuration // Indica que esta clase es una configuración de Spring
@EnableGlobalMethodSecurity(prePostEnabled = true) // Activa la seguridad a nivel de método utilizando las anotaciones de Spring Security
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioService usuarioService;

    @Bean // Indica que este método crea un bean de Spring
    public static PasswordEncoder passwordEncoder() { // Método para codificar las contraseñas
        return new BCryptPasswordEncoder();
    }

    @Bean // Indica que este método crea un bean de Spring
    public DaoAuthenticationProvider authenticationProvider() { // Configura el proveedor de autenticación
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioService); // Establece el servicio para cargar los detalles del usuario
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Configura el administrador de autenticación
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // Configura la seguridad HTTP
        http
                .cors() // Habilita la configuración CORS
                .and()
                .authorizeHttpRequests()
                .antMatchers( "/registro/**","/webjars/**", "/css/**", "/js/**","/img/**", "/api/**", "/set-cookie").permitAll() // Permite el acceso a ciertas rutas sin autenticación
                .anyRequest().authenticated() // Requiere autenticación para todas las demás rutas
                .and()
                .formLogin() // Configura el formulario de inicio de sesión
                .loginPage("/login") // Ruta de la página de inicio de sesión
                .defaultSuccessUrl("/", true) // URL de redirección después del inicio de sesión exitoso
                .permitAll()
                .and()
                .logout() // Configura la funcionalidad de cierre de sesión
                .invalidateHttpSession(true) // Invalida la sesión HTTP
                .clearAuthentication(true) // Limpia la autenticación
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Ruta de solicitud para el cierre de sesión
                .logoutSuccessUrl("/login?logout").permitAll(); // URL de redirección después del cierre de sesión exitoso

        // Para que funcione el h2-console estas dos líneas son necesarias.
        http.csrf().disable(); // Deshabilita la protección CSRF
        http.headers().frameOptions().disable(); // Deshabilita las opciones de marco para poder ver el h2-console

    }

}

