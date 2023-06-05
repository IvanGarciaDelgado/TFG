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

@RequiredArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioService usuarioService;

    @Bean // Es una clase simple en Java que cumple con ciertas normas con los nombres de sus propiedades y métodos.
    // Es un componente (similar a una caja) que nos permite encapsular el contenido, con la finalidad de otorgarle una mejor estructura.
    public static PasswordEncoder passwordEncoder() { // Indicamos como vamos a codificar nuestra contraseña.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() { // Obtenemos, mostramos datos de un usuario.
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioService); // Implementamos setUserDetailsService con su método ladUserByUsername
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Valida si los datos del authenticationProvider que he obtenido son válidos o no.
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers( "/registro/**","/webjars/**", "/css/**", "/js/**","/img/**", "/api/**", "/set-cookie").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();

        // Para que funcione el h2-console estas dos líneas son necesarias.
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

}
