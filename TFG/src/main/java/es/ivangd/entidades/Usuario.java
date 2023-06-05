package es.ivangd.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor // Genera un constructor con un parámetro para cada campo en su clase.
@NoArgsConstructor // Generará un constructor sin parámetros.
@Getter // Genera los getters.
@Setter // Genera los setters.
@Builder // Permite generar automáticamente el código requerido para que su clase sea instanciable.
@ToString // Genera un ToString
@EntityListeners(AuditingEntityListener.class) // Para que funcione el auditorio como createdDate.

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email")) // Indico que la columna email será único con @UniqueConstraint
public class Usuario {

    @Id // @Id sirve para identificar tu clave primaria y diferenciar a la entidad del resto.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Generatedvalue para indicarle a JPA que regla de autogeneración de la llave primaria vamos a utilizar. Puede ser Identity (solo hay que indicarle la estrategia, el contador de la columna incremente en 1 cada vez que un nuevo objeto es insertado) o el Sequence con la anotación @SequenceGenerator (se utiliza para indicarle a JPA que secuencia debe de utilizar para insertar en la base de datos)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "movil")
    private String movil;

    @Column(unique = true)
    private String email;

    private String password;

    @CreatedDate // @CreatedDate añadirán la fecha de creación del registro de una manera automática.
    private LocalDateTime fechaAlta;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) // Indico una relación ManyToMany donde con @JoinTable creo una tabla llamada usuarios_roles, con @JoinColumn uno columnas dónde usuario_id se une a la columna referenciada de esta clase("id") y la columna "role_id" con el inverseJoinColumns lo une con el "id" de la otra clase.
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_producto", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Compra> compra;

/*    @OneToOne(mappedBy = "usuario", cascade = CascadeType.MERGE, orphanRemoval = true) // orphanRemoval = true, si un objeto relacionado es eliminado o desvinculado de la entidad padre, entonces ese objeto se eliminará de la base de datos automáticamente.
    private Dieta dieta;*/

    public Usuario(String nombre, String apellidos, String movil, String email, String password, Collection<Role> roles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.movil = movil;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProducto(Producto producto) {
        if (productos == null) {
            productos = new ArrayList<>();
        }

        if (!productos.contains(producto)) {
            productos.add(producto);
            producto.agregarUsuario(this);
        }
    }



}
