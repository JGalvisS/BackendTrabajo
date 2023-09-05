package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.dto.TurnoDTO;
import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.entity.Turno;
import com.example.ProyectoJessiYAna.repository.TurnoRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Llama al Service de Turno")
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    private TurnoDTO turnoATurnoDTO(Turno turno){// cunvierto un Turno a un TurnoDTO que entregara solo la informacion requerida de visibilizar
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        return turnoDTO;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        //Odontologo odontologo = new Odontologo();
        Optional <Odontologo> odontologo = new OdontologoService().buscarPorId(turnoDTO.getId());
        //odontologo.setId (turnoDTO.getOdontologoId());// usando el id que trae turnoDTO me traigo al odontologo con ese ID
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setId(turnoDTO.getId());
        //turno.setOdontologo(odontologo);// luego le cargo ese valor a turno
        turno.setPaciente(paciente);
        return turno;
    }
    //metodos manueales
    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoAGuardar= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoAGuardar);
    }
    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }
    public void eliminarTurno (Long id){
        turnoRepository.deleteById(id);
    }
    public Optional<TurnoDTO> buscarPorId (Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);// en esta linea busca el turno con ese ID y lo almacena en un Optional
        if(turnoBuscado.isPresent()){// pregunto si ese turno esta presente
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));// sí esta presente devuelve un Optional del turnoBuscado que ejecutara turnoAturnoDTO
        }else {
           return Optional.empty();// sino esta devuelvelo vacio
        }
    }
    public List<TurnoDTO> listarTodos(){
        List<Turno> listaDeTurnos= turnoRepository.findAll();//aqui busco todos los turnos
        List<TurnoDTO> listaTurnoDTO = new ArrayList<>();// creo un array vacio que recibira una turnosDTO
        for (Turno turno:listaDeTurnos
             ) {
            listaTurnoDTO.add(turnoATurnoDTO(turno));// agrego al array listaTurnoDTO el turno que quiero, pero dentro del add convierto ese turno en un turnoDTO
        }
        return listaTurnoDTO;

    }
}
