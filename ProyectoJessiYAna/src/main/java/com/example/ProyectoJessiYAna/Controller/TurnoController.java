package com.example.ProyectoJessiYAna.Controller;

import com.example.ProyectoJessiYAna.model.Turno;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import com.example.ProyectoJessiYAna.service.PacienteService;
import com.example.ProyectoJessiYAna.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService=new TurnoService();

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listarturnos (){// con la response entity voy a poder decidir que tipo de respuesta quiero dar
        return ResponseEntity.ok( turnoService.listarTodosLosTurnos());// le digo que de esto voy a querer un ok = 200
    }
    @PostMapping("/guardar")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){// del lado del cliente me envian un body con informacion para construir un objeto de tipo turno
        //aca tengo que filtar
        OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService= new PacienteService();
        if(pacienteService.buscarPorID(turno.getPaciente().getId())!=null &&  odontologoService.buscarOdontologoPorID(turno.getOdontologo().getId())!=null){
            //sí ambos existen
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            return ResponseEntity.badRequest().build();//build me va a permitir autocontruir
        }


        }

}
