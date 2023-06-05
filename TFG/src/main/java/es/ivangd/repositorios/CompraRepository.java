package es.ivangd.repositorios;

import es.ivangd.entidades.Compra;
import es.ivangd.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByUsuario(Usuario usuario); // Método para buscar las compras de un usuario.

}
