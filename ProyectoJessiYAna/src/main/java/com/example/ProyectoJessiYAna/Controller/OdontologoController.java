package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.model.Odontologo;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService = new OdontologoService();

    @PostMapping
    private ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok( odontologoService.guardarOdontolo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> busccarOdontologoPorID(@PathVariable("id") Integer id){ //PathVariable permite hacer match con el endpoint por eso lo que va entre parentecis debe ser igual a la descripcio dada en el getmapping
        return ResponseEntity.ok( odontologoService.buscarOdontologoPorID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable("id") Integer id){
        String respuesta= null;
        if (odontologoService.buscarOdontologoPorID(id).getId()==id){
            odontologoService.eliminarOdontologo(id);
            respuesta= "El odontologo ha sido eliminado";
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }else {respuesta="El odontologo no pudo ser eliminado";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologobuscado = odontologoService.buscarOdontologoPorID(odontologo.getId());
        String respuesta= null;
        if(odontologobuscado!= null){
            odontologoService.actualizarOdontologo(odontologo);
            respuesta= "Odontologo con ID " + odontologo.getId()+" ha sido actualizado con exito";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);

        }else{
            respuesta = "No se pudo actualizar el odontologo solicitado";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologo(){
        return ResponseEntity.ok( odontologoService.listarOdontologo());
    }



}
