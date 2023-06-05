package es.ivangd.controladores;


import es.ivangd.entidades.Compra;
import es.ivangd.entidades.Producto;
import es.ivangd.entidades.Usuario;
import es.ivangd.servicios.CompraService;
import es.ivangd.servicios.ProductoService;
import es.ivangd.servicios.UsuarioService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging.
@Data // Genera Getter y Setter, equals y hashCode y un constructor que inicializa todos los campos finales.
@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    HttpSession httpSession;

    private Usuario usuario;

    private final ProductoService productoService;

    private final CompraService compraService;

    private final UsuarioService usuarioService;


    @ModelAttribute("carrito")
    public List<Producto> productosCarrito() {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        return (contenido == null) ? null : productoService.productosPorSuId(contenido);
    }

    @ModelAttribute("total_carrito")
    public Double totalCarrito() {
        List<Producto> productosCarrito = productosCarrito();
        if (productosCarrito != null)
            return productosCarrito.stream()
                    .mapToDouble(p -> p.getPrecio())
                    .sum();
        return 0.0;
    }

    @ModelAttribute("mis_compras")
    public List<Compra> misCompras() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        usuario = usuarioService.buscarPorEmail(email);
        return compraService.findByUsuario(usuario);
    }


    @GetMapping("")
    public String miCarrito(Model model) {
        return "carrito";
    }

    @GetMapping("/add/{id}")
    public String addCarrito(Model model, @PathVariable Long id) {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        if (contenido == null)
            contenido = new ArrayList<>();
        if (!contenido.contains(id))
            contenido.add(id);
        httpSession.setAttribute("carrito", contenido);
        return "redirect:/carrito";
    }

    @GetMapping("/eliminar/{id}")
    public String borrarDeCarrito(Model model, @PathVariable Long id) {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        if (contenido == null)
            return "redirect:/catalogo";
        contenido.remove(id);
        if (contenido.isEmpty())
            httpSession.removeAttribute("carrito");
        else
            httpSession.setAttribute("carrito", contenido);
        return "redirect:/carrito";

    }

    @GetMapping("/finalizar")
    public String checkout() {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        if (contenido == null)
            return "redirect:/catalogo";

        List<Producto> productos = productosCarrito();

        Compra c = compraService.insertar(new Compra(), usuario);

        productos.forEach(p -> compraService.addProductoCompra(p, c));
        httpSession.removeAttribute("carrito");

        return "redirect:/carrito/confirmar";

    }


    @GetMapping("/miscompras")
    public String verMisCompras() {
        return "compras";
    }


    @GetMapping("/compra/factura/{id}")
    public String factura(Model model, @PathVariable Long id) {
        Compra c = compraService.findById(id);
        List<Producto> productos = productoService.productosDeUnaCompra(c);
        model.addAttribute("productos", productos);
        model.addAttribute("compra", c);
        model.addAttribute("total_compra", productos.stream().mapToDouble(p -> p.getPrecio()).sum());
        return "factura";
    }

    @GetMapping("/confirmar")
    public String confirmarCompra() {
        return "confirmar";
    }

    @GetMapping("/completado")
    public String compraCompletada() {
        return "completado";
    }

}
