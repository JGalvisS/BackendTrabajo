package com.example.ProyectoJessiYAna;
import com.example.ProyectoJessiYAna.dto.TurnoDTO;
import com.example.ProyectoJessiYAna.entity.Domicilio;
import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.entity.Turno;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import com.example.ProyectoJessiYAna.service.PacienteService;
import com.example.ProyectoJessiYAna.service.TurnoService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)//desactiva seguridad =====SE PODRAN HACER PRUBAS EN POSTMAN===== pero resulta mejor hacerlas desde aca
public class TestIntegracionTurnos {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarInfo() {//cargo datos iniciales para mi test
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Martin", "Cerbin", "66999900", LocalDate.of(2023, 9, 16), new Domicilio("Siempre viva", 742, "Santa fe", "Entre rios"), "martin@dj.com"));
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("TUT749", "Romina", "Mazzuco"));
        TurnoDTO turnoDTO = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDate.of(2023, 10, 26)));//<---------------opcion 1
        Turno turno1 = new Turno(paciente, odontologo, LocalDate.of(2023, 10, 23));//<----------Opcion2
        TurnoDTO turno1DTO = turnoService.guardarTurno(turno1);
       /* TurnoDTO turno2DTO= new TurnoDTO();//<-------------Opcion3
        turno2DTO.setPacienteId(paciente.getId());
        turno2DTO.setOdontologoId(odontologo.getId());
        turno2DTO.setFecha(LocalDate.of(2023,10,28));
        turnoService.guardarTurno(turnoServic.);*/
    }

    @Test
    public void listarTodosTest() throws Exception {
        cargarInfo();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))//llamo mock de spring y con perform voy a enviar una perfom contruida, MockMvcRequestBuilders construyo una respuesta que apunta a un endpoint el cual solo acepara aplicaciones en formato JSON
                .andDo(MockMvcResultHandlers.print())// captura la respuesta imprimiendola en consola
                .andExpect(MockMvcResultMatchers.status().isOk())//y aqui va lo que esperamos en este caso un 200
                .andReturn();// y que ademas nos lo retorne  la respuesta
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());// la respuesta que obtiene la convierte en un string y verifica si esta vacia
    }

    @Test
    public void guardarTest() throws Exception {
        String contentenido = "{\"paciente\":{\"id\":1\"},\"odontologo\":{\"id\":1\"},\"fecha\":\"2023-08-22\"}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentenido))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())


                                        .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


}
