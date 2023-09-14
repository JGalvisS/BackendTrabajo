package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.entity.Usuario;
import com.example.ProyectoJessiYAna.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(email); // aqui uso mi repository y buecar el email
        if(usuarioBuscado.isPresent()){// sí el usuario buscado existe
            return usuarioBuscado.get();
        }else {
            throw new UsernameNotFoundException("No se encontro el Usuario con el correo suministrado. ");
        }
    }
}
