package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.model.Turno;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import com.example.ProyectoJessiYAna.service.PacienteService;
import com.example.ProyectoJessiYAna.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService=new TurnoService();


    @PostMapping("/guardar")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){// del lado del cliente me envian un body con informacion para construir un objeto de tipo turno
        //aca tengo que filtar
        OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService= new PacienteService();
        if(pacienteService.buscarPorID(turno.getPaciente().getId())!=null &&  odontologoService.buscarOdontologoPorID(turno.getOdontologo().getId())!=null){
            //sí ambos existen
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            return ResponseEntity.badRequest().build();//build me va a permitir autocontruir una respuesta
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarTurnoPorID (@PathVariable("id")Integer id){
       return ResponseEntity.ok(turnoService.buscarPorId(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable("id")Integer id){
        String respuesta= null;
        if (turnoService.buscarPorId(id).getId()==id){
            turnoService.eliminarTurno(id);
            respuesta= "El paciente ha sido eliminado";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);// de esta forma me va retornar un mensaje de respuesta configurado
            // return ResponseEntity.status(HttpStatus.CREATED).build(); // esta forma solo el 201
        }else {respuesta="El paciente no pudo ser eliminado";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);

        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService= new PacienteService();
        String respuesta=null;
        if(pacienteService.buscarPorID(turno.getPaciente().getId())!=null &&  odontologoService.buscarOdontologoPorID(turno.getOdontologo().getId())!=null){
            //sí ambos existen
            turnoService.actualizarTurno(turno);
            respuesta = "El tuerno con ID "+turno.getId()+" ha sido actualizado con exito";
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }else {
            respuesta ="no se pudo actualizar el turno solicitado";
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listarturnos (){// con la response entity voy a poder decidir que tipo de respuesta quiero dar
        return ResponseEntity.ok( turnoService.listarTodosLosTurnos());// le digo que de esto voy a querer un ok = 200
    }

}
