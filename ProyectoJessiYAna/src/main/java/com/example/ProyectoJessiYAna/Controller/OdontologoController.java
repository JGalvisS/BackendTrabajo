package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.exception.ResourceNotFoundException;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/odontologos")
public class OdontologoController {
    private static final Logger logger = Logger.getLogger(OdontologoController.class);
    @Autowired
    private OdontologoService odontologoService = new OdontologoService();

    @PostMapping
    private ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        logger.info("Se llama a Odontologo Controller");
        return ResponseEntity.ok( odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <Optional<Odontologo>> busccarOdontologoPorID(@PathVariable("id") Long id){ //PathVariable permite hacer match con el endpoint por eso lo que va entre parentecis debe ser igual a la descripcio dada en el getmapping
        logger.info("Se llama a Odontologo Controller");
        return ResponseEntity.ok( odontologoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.info("Se llama a Odontologo Controller");
        Optional <Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        String respuesta= null;
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            respuesta= "El odontologo ha sido eliminado";
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }else {
            //respuesta="El odontologo no pudo ser eliminado"; <-----SIN MANEJO DE EXPTION
            //return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST); <-----SIN MANEJO DE EXPTION
            throw  new ResourceNotFoundException("No existe el Odontologo ha eliminar");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        logger.info("Se llama a Odontologo Controller");
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        String respuesta= null;
        if(odontologoBuscado.isPresent()){
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
        logger.info("Se llama a Odontologo Controller");
        return ResponseEntity.ok( odontologoService.listarTodos());
    }
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> busccarOdontologoMatricula(@PathVariable("matricula") String matricula){
        logger.info("Se llama a Odontologo Controller");
        return ResponseEntity.ok( odontologoService.buscarPorMatricula(matricula));
    }


}
