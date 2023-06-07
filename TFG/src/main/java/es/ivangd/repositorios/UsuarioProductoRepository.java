package es.ivangd.repositorios;

import es.ivangd.entidades.UsuarioProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioProductoRepository extends JpaRepository<UsuarioProducto, Long> {

}
