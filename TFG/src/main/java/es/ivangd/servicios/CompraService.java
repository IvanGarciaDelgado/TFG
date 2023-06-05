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

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging, para poder añadir la notación log.info
@RequiredArgsConstructor // Genera un constructor para atributos como final no inicializados y anotados con @notNull, también sirve para el ahorro del autowired.
@Service
public class CompraService {


    private final CompraRepository repositorio; // Permite inyectar unas dependencias (inyección de constructor) con otras dentro de Spring, enlazar elementos.

    private final ProductoService productoService;

    public Compra insertar(Compra c, Usuario u) { // Método para añadir una compra con usuario. save: Guarda una entidad determinada. (No sé si lo añadiré finalmente)
        c.setUsuario(u);
        return repositorio.save(c);
    }

    public Compra añadir(Compra c) { // Método para añadir una compra. save: Guarda una entidad determinada.
        return repositorio.save(c);
    }



    public Producto addProductoCompra(Producto p, Compra c) {
        p.setCompra(c);
        return productoService.guardar(p);
    }


    public Compra findById(long id) { // Método para buscar una compra por ID.
        return repositorio.findById(id).orElse(null);
    }

    public List<Compra> listar() { // Método que te devuelve todas las compras.
        return repositorio.findAll();
    }

    public List<Compra> findByUsuario(Usuario u) { // Método para buscar una compra por usuario.
        return repositorio.findByUsuario(u);
    }

    public void saveAll (List<Compra> lista){ // saveAll: Guarda todas las entidades dadas.

        repositorio.saveAll(lista);
    }

    @Transactional
    // Es un metadato que especifica que una interfaz, clase o método debe tener semántica transaccional.
    public void deleteAll() { // deleteAll: Elimina todas las entidades gestionadas por el repositorio.
        repositorio.deleteAllInBatch(); // deleteAllInBatch: Elimina todas las entidades en una llamada por lotes.
    }

}
