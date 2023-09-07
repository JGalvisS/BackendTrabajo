package com.example.ProyectoJessiYAna.ServiceTest;

import com.example.ProyectoJessiYAna.entity.Domicilio;
import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.service.PacienteService;
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
public class PacienteServiceTest {
        @Autowired
        private PacienteService pacienteService;
        @Test
        @Order(1)
        public void guardarPaciente(){
            Paciente pacienteAGuardar= new Paciente("Jorgito","Pereyra","1234", LocalDate.of(2023,9,05),new Domicilio("calle 1",11,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
            pacienteService.guardarPaciente(pacienteAGuardar);
            assertEquals(1L,pacienteAGuardar.getId());
        }
        @Test
        @Order(2)
        public void buscarPorIdTest(){
            Long idAbuscar= 1L;
            Optional<Paciente> pacienteABuscado= pacienteService.buscarPorId(idAbuscar);
            assertNotNull(pacienteABuscado);
        }
        @Test
        @Order(3)
        public void buscarTodos(){
            List<Paciente> listaPaciente= pacienteService.listarTodos();
            assertEquals(1,listaPaciente.size());
        }
        @Test
        @Order(4)
        public void actualizarPaciente(){
            Long idABuscar= 1L;
            Optional<Paciente> paciente= pacienteService.buscarPorId(idABuscar);
            if(paciente.isPresent()){
                Paciente pacienteAGuardar= new Paciente(idABuscar,"Agustin","Pereyra","1234", LocalDate.of(2023,9,05),new Domicilio("calle 1",11,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
                pacienteService.actualizarPaciente(pacienteAGuardar);
                Optional<Paciente> pacienteActualizado= pacienteService.buscarPorId(1L);
                assertEquals("Agustin",pacienteActualizado.get().getNombre());

            }
        }
        @Test
        @Order(5)
        public void eliminarPaciente(){
            Long idEliminar= 1L;
            pacienteService.eliminarPaciente(idEliminar);
            Optional<Paciente> pacienteEliminado= pacienteService.buscarPorId(idEliminar);
            assertFalse(pacienteEliminado.isPresent());
        }



    }
