package com.example.ProyectoJessiYAna.ServiceTest;

import com.example.ProyectoJessiYAna.dto.TurnoDTO;
import com.example.ProyectoJessiYAna.entity.Domicilio;
import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.entity.Turno;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import com.example.ProyectoJessiYAna.service.PacienteService;
import com.example.ProyectoJessiYAna.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {

        @Autowired
        private TurnoService turnoService;
        OdontologoService odontologoService;
        PacienteService pacienteService;
    @Autowired
    public TurnoServiceTest(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

        @Test
        @Order(1)
        public void guardarTurno(){
            //DADO
            Turno turno1=new Turno(pacienteService.guardarPaciente(new Paciente("Camilo","Gomez","1234", LocalDate.of(2023,9,14),new Domicilio("calle 100",11,"Engativa","La Rioja"),"camilo.gomez@digitalhouse.com")),odontologoService.guardarOdontologo( new Odontologo("OWJ789","Jose","Camargo")), LocalDate.of(2023,9,25));
            Turno turno2=new Turno(pacienteService.guardarPaciente(new Paciente("Marta","Ramirez","1234833", LocalDate.of(2023,9,11),new Domicilio("calle 110",74,"Engativa","Caqueta"),"marta.ra@digitalhouse.com")),odontologoService.guardarOdontologo( new Odontologo("FGH456","Sebastian","Cardenas")), LocalDate.of(2023,10,25));
            Turno turno3=new Turno(pacienteService.guardarPaciente(new Paciente("Pepe","Saebz","172234", LocalDate.of(2023,11,14),new Domicilio("calle 130",11,"Engativa","Atlantico"),"p3p3.sa@digitalhouse.com")),odontologoService.guardarOdontologo( new Odontologo("KLS677","Laura","Pulido")), LocalDate.of(2023,11,25));
            //CUANDO
           turnoService.guardarTurno(turno1);
           turnoService.guardarTurno(turno2);
           turnoService.guardarTurno(turno3);
            //ENTONCES
            assertEquals(1L,turno1.getId());
        }
        @Test
        @Order(2)
        public void buscarPorIdTest(){
            //DADO
            Long idAbuscar= 1L;
            //CUANDO
            Optional<TurnoDTO> turnoABuscado= turnoService.buscarPorId(idAbuscar);
            //ENTONCES
            assertNotNull(turnoABuscado);
        }
        @Test
        @Order(3)
        public void buscarTodos(){
            //DADO Y CUANDO
            List<TurnoDTO> listaTurnos= turnoService.listarTodos();
            //ENTONCES
            assertEquals(3,listaTurnos.size());
        }
        @Test
        @Order(4)
        public void actualizarTurno(){
            //DADO
            Long idABuscar= 1L;
            Optional<TurnoDTO> turno = turnoService.buscarPorId(idABuscar);//busco el turno
            if(turno.isPresent()){// pregunto si me trajo un turno en la busqueda
                Optional<Paciente> pacienteBuscadoPorid = pacienteService.buscarPorId(turno.get().getPacienteId());//me traigo un objeto Optional con ese id extraido del turno
                Optional<Odontologo> odontologoBuscadoporid =odontologoService.buscarPorId(turno.get().getOdontologoId());
                Long pacienteId = pacienteBuscadoPorid.get().getId();//utilizo el metodo get de Optional para converturlo en clase correspondiente y luego obtengo su id de tipo Long
                Long odontologoId = odontologoBuscadoporid.get().getId();
                Turno turnoAGuardar= new Turno(idABuscar,pacienteService.guardarPaciente(new Paciente(pacienteId,"Camilo","Gomez","1234", LocalDate.of(2023,9,14),new Domicilio("calle 100",11,"Engativa","La Rioja"),"camilo.gomez@digitalhouse.com")),odontologoService.guardarOdontologo( new Odontologo(odontologoId,"OWJ789","Jose","Camargo")), LocalDate.of(2023,9,30));// cargo los datos incluyendo el id de Paciente y Odontologo
                //CUANDO
                turnoService.actualizarTurno(turnoAGuardar);//actualizo
                //ENTONCES
                Optional<TurnoDTO> turnoActualizado= turnoService.buscarPorId(idABuscar);//busco nuevamente el turno con el id
                assertEquals(LocalDate.of(2023,9,30),turnoActualizado.get().getFecha()); // y comparo la fecha

            }
        }
        @Test
        @Order(5)
        public void eliminarTurno(){
            //DADO
            Long idEliminar= 1L;
            //CUANDO
            turnoService.eliminarTurno(idEliminar);
            //ENTONCES
            Optional<TurnoDTO> turnoEliminado= turnoService.buscarPorId(idEliminar);
            assertFalse(turnoEliminado.isPresent());
        }
    }
