package es.ivangd.entidades;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor // Genera un constructor con un parámetro para cada campo en su clase.
@NoArgsConstructor // Generará un constructor sin parámetros.
@Getter // Genera los getters.
@Setter // Genera los setters.
@Builder // Permite generar automáticamente el código requerido para que su clase sea instanciable.
@ToString // Genera un ToString
@EntityListeners(AuditingEntityListener.class) // Para que funcione el auditorio como createdDate.
@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id // @Id sirve para identificar tu clave primaria y diferenciar a la entidad del resto.
    @GeneratedValue
    // @Generatedvalue para indicarle a JPA que regla de autogeneración de la llave primaria vamos a utilizar. Puede ser Identity (solo hay que indicarle la estrategia, el contador de la columna incremente en 1 cada vez que un nuevo objeto es insertado) o el Sequence con la anotación @SequenceGenerator (se utiliza para indicarle a JPA que secuencia debe de utilizar para insertar en la base de datos)
    private long id;

    private String nombre;

    private float precio;

    private String imagen;

    private int descuento;

    @Column(columnDefinition="TEXT")
    private String detalles;

    private String imagen2;

    private String imagen3;

    private String imagen4;

    private String proveedores;


    @ManyToMany(mappedBy = "productos")
    private List<Usuario> usuarios;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

    public Producto(String nombre, float precio, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(long id, String nombre, float precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }
    public void agregarUsuario(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            usuario.agregarProducto(this);
        }
    }

    public Producto(String nombre, float precio, String imagen, int descuento, String detalles, String imagen2, String imagen3, String imagen4, String proveedores) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descuento = descuento;
        this.detalles = detalles;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.proveedores = proveedores;
    }
}
