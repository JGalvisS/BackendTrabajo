package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.dto.TurnoDTO;
import com.example.ProyectoJessiYAna.entity.Turno;
import com.example.ProyectoJessiYAna.exception.BadRequestException;
import com.example.ProyectoJessiYAna.exception.ResourceNotFoundException;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import com.example.ProyectoJessiYAna.service.PacienteService;
import com.example.ProyectoJessiYAna.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger logger = Logger.getLogger(TurnoController.class);
    private TurnoService turnoService;
    OdontologoService odontologoService;
    PacienteService pacienteService;
    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno) throws BadRequestException {// del lado del cliente me envian un body con informacion para construir un objeto de tipo turno
        logger.info("Se llama a Turno Controller");
        //aca tengo que filtar

        if(pacienteService.buscarPorId(turno.getPaciente().getId()).isPresent() &&  odontologoService.buscarPorId(turno.getOdontologo().getId()).isPresent()){
            //sí ambos existen o estan presentes
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            //return ResponseEntity.badRequest().build();//build me va a permitir autocontruir una respuesta/ <-----SIN MANEJO DE EXPTION
            throw new BadRequestException("Paciente y Odontologos son requeridos");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoPorID (@PathVariable("id")Long id){
        logger.info("Se llama a Turno Controller");
        Optional<TurnoDTO> turnoDTO = turnoService.buscarPorId(id);
        if(turnoDTO.isPresent()){
            return ResponseEntity.ok(turnoDTO.get());// el get es necesario para obtener traer al dto
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable("id")Long id) throws ResourceNotFoundException {
        logger.info("Se llama a Turno Controller");
        //Optional<TurnoDTO> turnoAEliminar=turnoService.buscarPorId(id);<----- Linea del profe que no le encuentro sentido
        String respuesta= null;
        if (turnoService.buscarPorId(id).isPresent()){
            turnoService.eliminarTurno(id);
            respuesta= "El turno ha sido eliminado";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);// de esta forma me va retornar un mensaje de respuesta configurado
            // return ResponseEntity.status(HttpStatus.CREATED).build(); // esta forma solo el 201
        }else {
            //respuesta="El turno no pudo ser eliminado"; <-----SIN MANEJO DE EXPTION
            //return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST); <-----SIN MANEJO DE EXPTION
            throw new ResourceNotFoundException("No existe el Turno ha eliminar");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        logger.info("Se llama a turno Controller");
        String respuesta=null;
        if(pacienteService.buscarPorId(turno.getPaciente().getId()).isPresent() &&  odontologoService.buscarPorId(turno.getOdontologo().getId()).isPresent()){
            //sí ambos existen o estan presentes
            turnoService.actualizarTurno(turno);
            respuesta = "El tuerno con ID "+turno.getId()+" ha sido actualizado con exito";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }else {
            respuesta ="no se pudo actualizar el turno solicitado";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarturnos (){// con la response entity voy a poder decidir que tipo de respuesta quiero dar, en este caso una una lista de turnoDTO
        logger.info("Se llama a Turno Controller");
        return ResponseEntity.ok( turnoService.listarTodos());// le digo que de esto voy a querer un ok = 200
    }

}
