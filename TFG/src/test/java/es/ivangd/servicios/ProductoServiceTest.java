package es.ivangd.servicios;


import es.ivangd.entidades.Producto;
import es.ivangd.repositorios.ProductoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    public void testGetProductById() {
        // Crear un producto esperado
        long productId = 1;
        Producto expectedProduct = new Producto(productId, "Product A", (float) 10.99, "imagen");

        // Configurar el comportamiento simulado del repositorio de productos
        when(productoRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));


        // Llamar al método bajo prueba
        Producto actualProduct = productoService.findById(1);

        // Afirmar que el producto devuelto es igual al producto esperado
        assertEquals(expectedProduct, actualProduct);
    }

}
