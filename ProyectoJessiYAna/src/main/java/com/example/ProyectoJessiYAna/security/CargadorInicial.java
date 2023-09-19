package com.example.ProyectoJessiYAna.security;

import com.example.ProyectoJessiYAna.dto.TurnoDTO;
import com.example.ProyectoJessiYAna.entity.*;
import com.example.ProyectoJessiYAna.repository.UsuarioRepository;
import com.example.ProyectoJessiYAna.service.OdontologoService;
import com.example.ProyectoJessiYAna.service.PacienteService;
import com.example.ProyectoJessiYAna.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CargadorInicial implements ApplicationRunner {// cuando levante el servidor va ejecutar lo que encuentre en esta clase
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();// creo el encriptador
        //_________________________CARGAR USER___________________________________//
        String clave =cifrador.encode("user");//llamo al cifrador para encripto la clave
        //System.out.println("Clave cifrada: "+ cifrador );// solo si quiero ver su funcion

        // crear un usuario
        Usuario usuario1 = new Usuario("user","usuario","user",clave, UserRole.ROLE_USER);

        //guardarlo en la base de datos
        usuarioRepository.save(usuario1);
        //______________________CARGAR ADMIN__________________________________//
        String claveAdmin=cifrador.encode("admin");
        Usuario admin1 = new Usuario("admin","administrador","admin",claveAdmin,UserRole.ROLE_ADMIN);
        usuarioRepository.save(admin1);

        // harcodear paciente
        Paciente pacienteAGuardar= new Paciente("Jorgito","Pereyra","1234", LocalDate.of(2023,9,05),new Domicilio("calle 1",11,"La Rioja","La Rioja"),"jorge.pereyra29@digitalhouse.com");
        Paciente pacientePrueba = new Paciente("Ana","Camargo","4561", LocalDate.of(2023,6,02),new Domicilio("calle 2",12,"Colonia","Valdense"),"noicam29@gmail.com");
        Paciente pacientePrueba2 =new Paciente("Ana Maria","Camargo Galvis","4561", LocalDate.of(2023,6,02),new Domicilio("calle 28",123,"Colonia","Valdense"),"noicamaria29@gmail.com");
        pacienteService.guardarPaciente(pacienteAGuardar);
        pacienteService.guardarPaciente(pacientePrueba);
        pacienteService.guardarPaciente(pacientePrueba2);
        // y odontologo
        Odontologo odontologo1= new Odontologo("ABCZ1223","Jessica","Galvis");
        Odontologo odontologo2= new Odontologo("DFG4Z56","Ana","Camargo");
        Odontologo odontologo3= new Odontologo("HIJZ789","Katherine","Camargo");
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.guardarOdontologo(odontologo3);
        //turno
        TurnoDTO turnoDTO = turnoService.guardarTurno(new Turno(pacientePrueba, odontologo1, LocalDate.of(2023, 10, 20)));
        TurnoDTO turno1DTO = turnoService.guardarTurno(new Turno(pacientePrueba2, odontologo2, LocalDate.of(2023, 10, 29)));
        TurnoDTO turno2DTO = turnoService.guardarTurno(new Turno(pacienteAGuardar, odontologo3, LocalDate.of(2023, 10, 28)));

    }
}
