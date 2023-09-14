package com.example.ProyectoJessiYAna.security;

import com.example.ProyectoJessiYAna.entity.UserRole;
import com.example.ProyectoJessiYAna.entity.Usuario;
import com.example.ProyectoJessiYAna.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadorInicial implements ApplicationRunner {// cuando levante el servidor va ejecutar lo que encuentre en esta clase
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();// creo el encriptador
        String clave =cifrador.encode("user");//llamo al cifrador para encripto la clave
        //System.out.println("Clave cifrada: "+ cifrador );// solo si quiero ver su funcion

        // crear un usuario
        Usuario usuario1 = new Usuario("user","usuario","user",clave, UserRole.ROLE_USER);

        //guardarlo en la base de datos
        usuarioRepository.save(usuario1);

        // harcodear paciente y odontologo

    }
}
