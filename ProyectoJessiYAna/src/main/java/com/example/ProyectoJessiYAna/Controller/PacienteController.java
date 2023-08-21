package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.model.Paciente;
import com.example.ProyectoJessiYAna.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller// ahora trabajo con vista, no va RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService = new PacienteService();
    @GetMapping
    public String buscarPorCorreo(Model model, @RequestParam ("email")String correo){
        //busqueda la tiene en el paciente
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        //estos resultados se los debo pasar a la vista
        return "index";
    }

}
