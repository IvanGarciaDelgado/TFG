package es.ivangd.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor // Genera un constructor con un parámetro para cada campo en su clase.
@NoArgsConstructor // Generará un constructor sin parámetros.
@Getter // Genera los getters.
@Setter // Genera los setters.
@Builder // Permite generar automáticamente el código requerido para que su clase sea instanciable.
@ToString // Genera un ToString
@Entity
@Table(name = "role")
public class Role {

    @Id // @Id sirve para identificar tu clave primaria y diferenciar a la entidad del resto.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Generatedvalue para indicarle a JPA que regla de autogeneración de la llave primaria vamos a utilizar. Puede ser Identity (solo hay que indicarle la estrategia, el contador de la columna incremente en 1 cada vez que un nuevo objeto es insertado) o el Sequence con la anotación @SequenceGenerator (se utiliza para indicarle a JPA que secuencia debe de utilizar para insertar en la base de datos)
    private long id;

    private String nombre;

    public Role(String nombre) {
        this.nombre = nombre;
    }
}
