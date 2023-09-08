package com.example.ProyectoJessiYAna.ServiceTest;
import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        //DADO
        Odontologo odontologo1= new Odontologo("ABC1223","Jessi","Galvis");
        Odontologo odontologo2= new Odontologo("DFG456","Ana","Camargo");
        Odontologo odontologo3= new Odontologo("HIJ789","Katherine","Camargo");
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



}
