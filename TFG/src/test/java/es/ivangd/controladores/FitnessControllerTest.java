package es.ivangd.controladores;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class FitnessControllerTest {

    @InjectMocks
    private FitnessController fitnesController;

    private MockMvc mockMvc;

    @Before //  El método setup() se utiliza para configurar el entorno de prueba y crear la instancia de MockMvc necesaria para realizar las pruebas de integración en el controlador.
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(fitnesController).build();
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
