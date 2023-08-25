package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.model.Paciente;
import com.example.ProyectoJessiYAna.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller// ahora trabajo con vista, no va RestController
@RestController // cuantro no trabajo con vista o en este caso con thymeleaf
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService = new PacienteService();

    @PostMapping // me va permitir crear un nuevo paciente
    public Paciente registrarPaciente(@RequestBody Paciente paciente){// el Requestbody indica que del lado del cliente me va a llegar la informacion en formato json para construir un objeto de tipo Paciente
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente ){
        Paciente pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        String respuesta= null;
        if(pacienteBuscado!= null){
            pacienteService.actualizarPaciente(paciente);
            respuesta = "Paciente con ID " + paciente.getId()+" ha sido actualizado con exito ";
        }else {
            respuesta = "No se pudo actualizar el paciente solicitado ";
        }
        return respuesta;
    }
    @GetMapping("/{id}")// esto va a tener que ser parte del endpoint, en este caso el numero de id
    public Paciente busccarPacientePorID(@PathVariable("id") Integer id){ //PathVariable permite hacer match con el endpoint por eso lo que va entre parentecis debe ser igual a la descripcio dada en el getmapping
        return pacienteService.buscarPorID(id);
    }
    @DeleteMapping("/delete/{id}")
    public String eliminarPaciente(@PathVariable("id") Integer id){
        String respuesta= null;
        if (pacienteService.buscarPorID(id).getId()==id){
            pacienteService.eliminarPaciente(id);
            respuesta= "El paciente ha sido eliminado";
        }else {respuesta="El paciente no pudo ser eliminado";}
        return respuesta;
    }
    @GetMapping("/listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.obtenerPacientes();
    }

    /*@GetMapping
    public String buscarPorCorreo(Model model, @RequestParam ("email")String correo){
        //busqueda la tiene en el paciente
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        //estos resultados se los debo pasar a la vista
        return "index";
    }*/

}
