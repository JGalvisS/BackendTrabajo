package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller// asi va trabajando con vista o en este caso con thymeleaf
@RestController // cuantro no trabajo con vista
@RequestMapping("/paciente")
public class PacienteController {
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService = new PacienteService();

    @PostMapping // me va permitir crear un nuevo paciente
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){// el Requestbody indica que del lado del cliente me va a llegar la informacion en formato json para construir un objeto de tipo Paciente
        logger.info("Se llama a Paciente Controller");
        return ResponseEntity.ok( pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/buscar/{id}")// esto va a tener que ser parte del endpoint, en este caso el numero de id
    public ResponseEntity<Optional<Paciente>> busccarPacientePorID(@PathVariable("id") Long id){ //PathVariable permite hacer match con el endpoint por eso lo que va entre parentecis debe ser igual a la descripcio dada en el getmapping
        logger.info("Se llama a Paciente Controller");
        return ResponseEntity.ok( pacienteService.buscarPorId(id));
    }
    @GetMapping("/email/{email}")// esto va a tener que ser parte del endpoint, en este caso el numero de id
    public ResponseEntity<Optional<Paciente>> busccarPacientePorEmail(@PathVariable("email") String email){ //PathVariable permite hacer match con el endpoint por eso lo que va entre parentecis debe ser igual a la descripcio dada en el getmapping
        logger.info("Se llama a Paciente Controller");
        return ResponseEntity.ok( pacienteService.buscarPorEmail(email));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable("id") Long id){
        logger.info("Se llama a Paciente Controller");
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPorId(id);
        String respuesta= null;
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            respuesta= "El paciente ha sido eliminado";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
            //return ResponseEntity.status(HttpStatus.CREATED).build();

        }else {respuesta="El paciente no pudo ser eliminado";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente ){
        logger.info("Se llama a Paciente Controller");
        Optional <Paciente> pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
        String respuesta= null;
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            respuesta = "Paciente con ID " + paciente.getId()+" ha sido actualizado con exito ";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }else {
            respuesta = "No se pudo actualizar el paciente solicitado ";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        logger.info("Se llama a Paciente Controller");
        return ResponseEntity.ok(pacienteService.listarTodos());
    }


}
