package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.model.Odontologo;
import com.example.ProyectoJessiYAna.model.Paciente;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService = new OdontologoService();

    @PostMapping("/guardar")
    private Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontolo(odontologo);
    }

    @PutMapping("/actualizar")
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologobuscado = odontologoService.buscarOdontologoPorID(odontologo.getId());
        String respuesta= null;
        if(odontologobuscado!= null){
            odontologoService.actualizarOdontologo(odontologo);
            respuesta= "Odontologo con ID " + odontologo.getId()+" ha sido actualizado con exito";
        }else{
            respuesta = "No se pudo actualizar el odontologo solicitado";
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public Odontologo busccarOdontologoPorID(@PathVariable("id") Integer id){ //PathVariable permite hacer match con el endpoint por eso lo que va entre parentecis debe ser igual a la descripcio dada en el getmapping
        return odontologoService.buscarOdontologoPorID(id);
    }

    @DeleteMapping("/delete/{id}")
    public String eliminarOdontologo(@PathVariable("id") Integer id){
        String respuesta= null;
        if (odontologoService.buscarOdontologoPorID(id).getId()==id){
            odontologoService.eliminarOdontologo(id);
            respuesta= "El odontologo ha sido eliminado";
        }else {respuesta="El odontologo no pudo ser eliminado";}
        return respuesta;
    }
    @GetMapping("/listar")
    public List<Odontologo> listarOdontologo(){
        return odontologoService.listarOdontologo();
    }



}
