package es.ivangd.controladores;


import es.ivangd.entidades.Dieta;
import es.ivangd.repositorios.DietaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class DietaController {

    private final DietaRepository dietaRepository;

    public DietaController(DietaRepository dietaRepository) {
        this.dietaRepository = dietaRepository;
    }

    @GetMapping("/dieta")
    public String mostrarPagina(@RequestParam(name = "calorias", required = false) Integer calorias, Model model) {
        if (calorias != null) {
            List<Dieta> dietas = dietaRepository.findByCalorias(calorias);
            model.addAttribute("dietas", dietas);
        }
        return "dieta";
    }
}


