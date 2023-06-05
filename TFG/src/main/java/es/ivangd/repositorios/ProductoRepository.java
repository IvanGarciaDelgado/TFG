package es.ivangd.repositorios;

import es.ivangd.entidades.Compra;
import es.ivangd.entidades.Producto;
import es.ivangd.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCompra(Compra compra); // Buscar todos los productos de una compra.

    List<Producto> findByNombreStartingWithIgnoreCase(String buscador); // Buscar los productos.

    Producto findFirstByNombre(String nombre);
}
