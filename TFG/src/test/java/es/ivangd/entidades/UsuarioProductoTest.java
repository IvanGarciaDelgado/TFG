package es.ivangd.entidades;

import es.ivangd.repositorios.ProductoRepository;
import es.ivangd.repositorios.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class UsuarioProductoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void testAgregarProductoAUsuario() {
        // Crear un usuario
        Usuario usuario = new Usuario("Ivan Garcia");
        entityManager.persist(usuario);

        // Crear un producto
        Producto producto = new Producto("Producto A");
        entityManager.persist(producto);

        // Establecer la relación entre usuario y producto
        usuario.agregarProducto(producto);

        // Guardar los cambios en la base de datos
        entityManager.flush();
        entityManager.clear();

        // Obtener el usuario de la base de datos y verificar la relación
        Usuario usuarioEncontrado = usuarioRepository.findById(usuario.getId()).orElse(null);
        assertNotNull(usuarioEncontrado);
        assertTrue(usuarioEncontrado.getProductos().contains(producto));
    }
}
