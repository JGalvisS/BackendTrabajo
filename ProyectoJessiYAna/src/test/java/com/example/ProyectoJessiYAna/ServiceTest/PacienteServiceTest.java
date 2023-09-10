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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// asi le informamos en que orden vamos a realizar el test en este caso especificaremos mediante anotaciones
@SpringBootTest // asi le decimos a spring que haremos un test
public class PacienteServiceTest {
        @Autowired
        private PacienteService pacienteService;
        @Test
        @Order(1)// anotacion donde especificamos en que posicion sera, en eeste caso esta sera la primera
        public void guardarPaciente(){
            //DADO
            Paciente pacienteAGuardar= new Paciente("Jorgito","Pereyra","1234", LocalDate.of(2023,9,05),new Domicilio("calle 1",11,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
            Paciente pacientePrueba = new Paciente("Ana","Camargo","4561", LocalDate.of(2023,6,02),new Domicilio("calle 2",12,"Colonia","Valdense"),"noicam2@gmail.com");
            Paciente pacientePrueba2 =new Paciente("Ana Maria","Camargo Galvis","4561", LocalDate.of(2023,6,02),new Domicilio("calle 28",123,"Colonia","Valdense"),"noicamaria2@gmail.com");
            //CUANDO
            pacienteService.guardarPaciente(pacienteAGuardar);
            pacienteService.guardarPaciente(pacientePrueba);
            //ENTONCES
            assertEquals(1L,pacienteAGuardar.getId());

        }
        @Test
        @Order(2)
        public void buscarPorIdTest(){
            //DADO
            Long idAbuscar= 1L;
            //CUANDO
            Optional<Paciente> pacienteABuscado= pacienteService.buscarPorId(idAbuscar);
            //ENTONCES
            assertNotNull(pacienteABuscado);
        }
        @Test
        @Order(3)
        public void buscarTodos(){
            //DADO Y CUANDO
            List<Paciente> listaPaciente= pacienteService.listarTodos();
            //ENTONCES
            assertEquals(2,listaPaciente.size());
        }
        @Test
        @Order(4)
        public void buscarPoremail(){
            //DADO
            String emailBuscado = "noicam2@gmail.com";
            //CUANDO
            Optional<Paciente> pacienteEcontrado = pacienteService.buscarPorEmail(emailBuscado);
            //ENTONCES
            assertEquals(pacienteEcontrado.get().getEmail(),emailBuscado);

        }
        @Test
        @Order(5)
        public void actualizarPaciente(){
            //DADO
            Long idABuscar= 1L;
            if(pacienteService.buscarPorId(idABuscar).isPresent()){// primero buscamos por un id y si ese paciente esta presente
                Paciente pacienteAGuardar= new Paciente(idABuscar,"Agustin","Pereyra","1234", LocalDate.of(2023,9,05),new Domicilio("calle 1",11,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
                //CUANDO
                pacienteService.actualizarPaciente(pacienteAGuardar); //actualizamos con los nuevos datos de pacienteAGuardar
                //ENTONCES
                Optional<Paciente> pacienteActualizado= pacienteService.buscarPorId(1L);// buscamos nuevamente el id
                assertEquals("Agustin",pacienteActualizado.get().getNombre());// comparamos los nombre

            }
        }
        @Test
        @Order(6)
        public void eliminarPaciente(){
            //DADO
            Long idEliminar= 1L;
            //CUANDO
            pacienteService.eliminarPaciente(idEliminar);
            Optional<Paciente> pacienteEliminado= pacienteService.buscarPorId(idEliminar);
            //ENTONCES
            assertFalse(pacienteEliminado.isPresent());
        }



    }
