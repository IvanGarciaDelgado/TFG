package es.ivangd.servicios;

import es.ivangd.entidades.Compra;
import es.ivangd.entidades.Producto;
import es.ivangd.entidades.Usuario;
import es.ivangd.repositorios.CompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j // Proporciona funciones de registro mediante la anotación log.info
@RequiredArgsConstructor // Crea un constructor para los campos marcados como 'final' y anotados con '@NotNull'
@Service // Indica que esta clase es un componente de servicio de Spring
public class CompraService {

    private final CompraRepository repositorio; // Repositorio para acceder y gestionar los datos de las compras

    private final ProductoService productoService; // Servicio para operaciones relacionadas con los productos

    public Compra insertar(Compra c, Usuario u) { // Método para insertar una compra asociada a un usuario
        c.setUsuario(u);
        return repositorio.save(c);
    }

    public Compra añadir(Compra c) { // Método para añadir una compra
        return repositorio.save(c);
    }

    public Producto addProductoCompra(Producto p, Compra c) { // Método para añadir un producto a una compra
        p.setCompra(c);
        return productoService.guardar(p);
    }

    public Compra findById(long id) { // Método para buscar una compra por su ID
        return repositorio.findById(id).orElse(null);
    }

    public List<Compra> listar() { // Método para obtener todas las compras
        return repositorio.findAll();
    }

    public List<Compra> findByUsuario(Usuario u) { // Método para buscar las compras de un usuario
        return repositorio.findByUsuario(u);
    }

    public void saveAll(List<Compra> lista) { // Método para guardar una lista de compras
        repositorio.saveAll(lista);
    }

    @Transactional // Indica que este método debe ejecutarse en una transacción
    public void deleteAll() { // Método para eliminar todas las compras
        repositorio.deleteAllInBatch();
    }
}

