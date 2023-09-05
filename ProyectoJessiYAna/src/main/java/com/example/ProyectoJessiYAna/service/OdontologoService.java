package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.repository.OdontologoRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Llama al Service de Odontologo")
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo (Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public void actualizarOdontologo (Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo (Long id){
        odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }
    public Optional<Odontologo> buscarPorId(Long id){
        return odontologoRepository.findById(id);
    }
}
