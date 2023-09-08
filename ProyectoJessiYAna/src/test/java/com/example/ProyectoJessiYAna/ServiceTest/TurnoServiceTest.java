package com.example.ProyectoJessiYAna.ServiceTest;

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
    /*
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
        public void guardarOdontologo(){
            //DADO
            Turno turno1=new Turno(new Paciente("Camilo","Gomez","1234", LocalDate.of(2023,9,14),new Domicilio("calle 100",11,"Engativa","La Rioja"),"camilo.gomez@digitalhouse.com"),new Odontologo("OWJ   789","Jose","Camargo"), LocalDate.of(2023,9,25));
            Turno turno2 = new Turno(1l,2l, LocalDate.of(2023,9,16));
            ---------------------------------------DESARROLLADO HASTA AQUI -------------------------------------
            Turno turno3 =new Turno();
            //CUANDO
            odontologoService.guardarOdontologo(odontologo1);
            odontologoService.guardarOdontologo(odontologo2);
            odontologoService.guardarOdontologo(odontologo3);
            //ENTONCES
            assertEquals(1L,odontologo1.getId());
        }
        @Test
        @Order(2)
        public void buscarPorIdTest(){
            //DADO
            Long idAbuscar= 1L;
            //CUANDO
            Optional<Odontologo> odontologoABuscado= odontologoService.buscarPorId(idAbuscar);
            //ENTONCES
            assertNotNull(odontologoABuscado);
        }
        @Test
        @Order(3)
        public void buscarTodos(){
            //DADO Y CUANDO
            List<Odontologo> listaOdontologos= odontologoService.listarTodos();
            //ENTONCES
            assertEquals(3,listaOdontologos.size());
        }
        @Test
        @Order(4)
        public void buscarMatricula(){
            //DADO
            String matriculaBuscada = "ABC1223";
            //CUANDO
            Optional<Odontologo> odontologoEcontrado = odontologoService.buscarPorMatricula(matriculaBuscada);
            //ENTONCES
            assertEquals(odontologoEcontrado.get().getMatricula(),matriculaBuscada);

        }
        @Test
        @Order(5)
        public void actualizarOdontologo(){
            //DADO
            Long idABuscar= 1L;
            Optional<Odontologo> odontologo = odontologoService.buscarPorId(idABuscar);
            if(odontologo.isPresent()){
                Odontologo odontologoAGuardar= new Odontologo(idABuscar,"ABC1223","Maria","Galvis");
                //CUANDO
                odontologoService.actualizarOdontologo(odontologoAGuardar);
                //ENTONCES
                Optional<Odontologo> odontologoActualizado= odontologoService.buscarPorId(idABuscar);
                assertEquals("Maria",odontologoActualizado.get().getNombre());//

            }
        }
        @Test
        @Order(6)
        public void eliminarOdontologo(){
            //DADO
            Long idEliminar= 1L;
            //CUANDO
            odontologoService.eliminarOdontologo(idEliminar);
            Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorId(idEliminar);
            //ENTONCES
            assertFalse(odontologoEliminado.isPresent());
        }

     */


    }
