package es.ivangd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter // Genera los getters.
@Setter // Genera los setters.
@AllArgsConstructor // Genera un constructor con un parámetro para cada campo en su clase.
@NoArgsConstructor // Generará un constructor sin parámetros.
public class UsuarioRegistroDTO {

    private long id;

    private String nombre;

    private String apellidos;

    private String movil;

    private String email;

    private String password;

    @CreatedDate // @CreatedDate añadirán la fecha de creación del registro de una manera automática.
    private LocalDateTime fechaAlta;


}
