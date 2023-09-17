package com.example.ProyectoJessiYAna.service;
import com.example.ProyectoJessiYAna.dto.TurnoDTO;
import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.entity.Turno;
import com.example.ProyectoJessiYAna.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private static final Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private TurnoDTO turnoATurnoDTO(Turno turno) {// cunvierto un Turno a un TurnoDTO que entregara solo la informacion requerida de visibilizar
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        logger.info("Se llama a Turno Service convertir turno a turnoDTO");

        return turnoDTO;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        if (pacienteService.buscarPorId(turnoDTO.getPacienteId()).isPresent() && odontologoService.buscarPorId(turnoDTO.getOdontologoId()).isPresent()) {
            Paciente paciente = pacienteService.buscarPorId(turnoDTO.getPacienteId()).get();
            Odontologo odontologo = odontologoService.buscarPorId(turnoDTO.getOdontologoId()).get();
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turno.setFecha(turnoDTO.getFecha());
            turno.setId(turnoDTO.getId());
            return turno;
        } else {
            return turno;

        }
    }

    //metodos manueales
    public TurnoDTO guardarTurno(Turno turno) {
        logger.info("Se llama a Turno Service para guardar");
        Turno turnoAGuardar = turnoRepository.save(turno);
        return turnoATurnoDTO(turnoAGuardar);
    }

    public void actualizarTurno(Turno turno) {
        logger.info("Se llama a Turno Service para actualizar");
        turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id) {
        logger.info("Se llama a Turno Service para eliminar");
        turnoRepository.deleteById(id);
    }

    public Optional<TurnoDTO> buscarPorId(Long id) {
        logger.info("Se llama a Turno Service para buscar por id");
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);// en esta linea busca el turno con ese ID y lo almacena en un Optional
        if (turnoBuscado.isPresent()) {// pregunto si ese turno esta presente
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));// sí esta presente devuelve un Optional del turnoBuscado que ejecutara turnoAturnoDTO
        } else {
            return Optional.empty();// sino esta devuelvelo vacio
        }
    }

    public List<TurnoDTO> listarTodos() {
        logger.info("Se llama a Turno Service para listar turnos");
        List<Turno> listaDeTurnos = turnoRepository.findAll();//aqui busco todos los turnos
        List<TurnoDTO> listaTurnoDTO = new ArrayList<>();// creo un array vacio que recibira una turnosDTO
        for (Turno turno : listaDeTurnos
        ) {
            listaTurnoDTO.add(turnoATurnoDTO(turno));// agrego al array listaTurnoDTO el turno que quiero, pero dentro del add convierto ese turno en un turnoDTO
        }
        return listaTurnoDTO;

    }
}
