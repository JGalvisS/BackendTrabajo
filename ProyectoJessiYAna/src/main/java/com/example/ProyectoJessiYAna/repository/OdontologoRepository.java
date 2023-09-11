package com.example.ProyectoJessiYAna.repository;

import com.example.ProyectoJessiYAna.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> { //CON Ctrl + CLICK IZQUIERDO REPETIDAMENTE PUEDO VER LOS METODOS DEL PADRE
    Optional<Odontologo> findByMatricula(String matricula);
}
