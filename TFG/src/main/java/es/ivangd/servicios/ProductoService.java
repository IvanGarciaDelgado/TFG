package es.ivangd.servicios;

import es.ivangd.entidades.Compra;
import es.ivangd.entidades.Producto;
import es.ivangd.repositorios.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging, para poder añadir la notación log.info
@RequiredArgsConstructor // Genera un constructor para atributos como final no inicializados y anotados con @notNull, también sirve para el ahorro del autowired.
@Service
public class ProductoService {

    private final ProductoRepository repositorio; // Permite inyectar unas dependencias (inyección de constructor) con otras dentro de Spring, enlazar elementos.

    public Producto añadir(Producto p) { // Método añadir productos. save: Guarda una entidad determinada.
        return repositorio.save(p);
    }

    public void borrar(long id) { // Método para borrar productos por su ID. deleteById: Elimina una entidad determinada por su ID.
        repositorio.deleteById(id);
    }

    public void borrar(Producto p) { // Método para borrar productos. delete: Elimina una entidad determinada.
        repositorio.delete(p);
    }

    public Producto guardar(Producto p) {
        return repositorio.save(p);
    }

    public Producto findById(long id) { // Método encontrar id de los productos. findById: Recupera una entidad por su id.
        return repositorio.findById(id).orElse(null);
    }

    public List<Producto> findAll() { // Método para devolverte la lista de los productos. findAll: Devuelve todas las instancias del tipo.
        return repositorio.findAll();
    }

    public List<Producto> productosDeUnaCompra(Compra c) { // Método para devolverte los productos de una compra.
        return repositorio.findByCompra(c);
    }

    public List<Producto> buscador(String b) { // Método para buscar los productos.
        return repositorio.findByNombreStartingWithIgnoreCase(b);
    }

    public List<Producto> productosPorSuId(List<Long> id) { // ??????????
        return repositorio.findAllById(id);
    }

    public void saveAll (List<Producto> lista){ // saveAll: Guarda todas las entidades dadas.

        repositorio.saveAll(lista);
    }

    @Transactional // Es un metadato que especifica que una interfaz, clase o método debe tener semántica transaccional.
    public void deleteAll() { // deleteAll: Elimina todas las entidades gestionadas por el repositorio.
        repositorio.deleteAllInBatch(); // deleteAllInBatch: Elimina todas las entidades en una llamada por lotes.
    }

}
