package es.ivangd.controladores;

import es.ivangd.entidades.Producto;
import es.ivangd.repositorios.ProductoRepository;
import es.ivangd.servicios.ProductoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging.
@Data // Genera Getter y Setter, equals y hashCode y un constructor que inicializa todos los campos finales.
@Controller
@RequestMapping("/catalogo")
public class ProductosController {

    private final ProductoService productoService;

    // Este constructor permite inyectar el servicio ProductoService al controlador.
    public ProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Este método mapeado a la ruta "/catalogo" muestra el catálogo de productos.
    @GetMapping("")
    public String mostrarCatalogo(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "catalogo";
    }

    // Este método mapeado a la ruta "/catalogo/producto/{id}" muestra los detalles de un producto específico.
    @GetMapping("/producto/{id}")
    public String showProduct(Model model, @PathVariable Long id) {
        Producto result = productoService.findById(id);
        if (result != null) {
            model.addAttribute("producto", result);
            return "producto";
        }
        return "redirect:/catalogo";
    }

    // Este método mapeado a la ruta "/catalogo/list/filter" realiza un listado filtrado de productos por nombre.
    @GetMapping("/list/filter")
    public String listadoFiltrado(@RequestParam("nombre") String nombre, Model model) {
        List<Producto> listaProductos = productoService.buscador(nombre);
        model.addAttribute("productos", listaProductos);
        return "fragmentos::catalogo-productos";
    }
}
