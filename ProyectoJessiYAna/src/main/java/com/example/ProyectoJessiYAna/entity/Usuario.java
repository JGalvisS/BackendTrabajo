package com.example.ProyectoJessiYAna.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario implements UserDetails { // UserDatails es una clase de SpringBoot
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column (unique = true,nullable = false)
    private String userName;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private UserRole userRole;

    public Usuario() {
    }

    public Usuario(String nombre, String userName, String email, String password, UserRole userRole) {
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public Usuario(Long id, String nombre, String userName, String email, String password, UserRole userRole) {
        this.id = id;
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {// devuelve el estado de la autorizacion, es decir una collection que dira que es lo que puede hacer ese usuario.
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(userRole.name());//Clase de SpringBoot,que le vamos a dar el rol que hayamos decidido para esta coleccion de acciones.
        return Collections.singletonList(simpleGrantedAuthority);// Patron de diseño que crea una unica instacia.
    }
    @Override
    public String getUsername() {
        return email;
    }
// Los metodos aqui en adelante podrian tener un atributo en la clase para cada metodo y en la logica del metodo ir verificando uno a uno su estado.
// Para este caso vamos como vamos a dejar un usuario y admin predeterinado lo vamos a dejar en true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
