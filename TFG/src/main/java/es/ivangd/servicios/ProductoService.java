package es.ivangd.servicios;

import es.ivangd.entidades.Compra;
import es.ivangd.entidades.Producto;
import es.ivangd.repositorios.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j // Proporciona funciones de registro mediante la anotación log.info
@RequiredArgsConstructor // Crea un constructor para los campos marcados como 'final' y anotados con '@NotNull'
@Service // Indica que esta clase es un componente de servicio de Spring
public class ProductoService {

    private final ProductoRepository repositorio; // Repositorio para acceder y gestionar los datos de los productos

    public Producto añadir(Producto p) { // Método para añadir un producto. save: Guarda una entidad determinada.
        return repositorio.save(p);
    }

    public void borrar(long id) { // Método para borrar un producto por su ID. deleteById: Elimina una entidad determinada por su ID.
        repositorio.deleteById(id);
    }

    public void borrar(Producto p) { // Método para borrar un producto. delete: Elimina una entidad determinada.
        repositorio.delete(p);
    }

    public Producto guardar(Producto p) { // Método para guardar un producto
        return repositorio.save(p);
    }

    public Producto findById(long id) { // Método para buscar un producto por su ID. findById: Recupera una entidad por su ID.
        return repositorio.findById(id).orElse(null);
    }

    public List<Producto> findAll() { // Método para obtener todos los productos. findAll: Devuelve todas las instancias del tipo.
        return repositorio.findAll();
    }

    public List<Producto> productosDeUnaCompra(Compra c) { // Método para obtener los productos de una compra.
        return repositorio.findByCompra(c);
    }

    public List<Producto> buscador(String b) { // Método para buscar productos por su nombre.
        return repositorio.findByNombreStartingWithIgnoreCase(b);
    }

    public List<Producto> productosPorSuId(List<Long> id) { // Método para obtener productos por su ID.
        return repositorio.findAllById(id);
    }

    public void saveAll(List<Producto> lista) { // Método para guardar una lista de productos
        repositorio.saveAll(lista);
    }

    @Transactional // Indica que este método debe ejecutarse en una transacción
    public void deleteAll() { // Método para eliminar todos los productos
        repositorio.deleteAllInBatch();
    }
}

