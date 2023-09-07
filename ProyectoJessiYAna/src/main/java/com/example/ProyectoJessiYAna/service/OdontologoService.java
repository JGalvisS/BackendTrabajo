package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.entity.Odontologo;
import com.example.ProyectoJessiYAna.repository.OdontologoRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo (Odontologo odontologo){
        logger.info("Se llama a odotologo Service para guardar");
        return odontologoRepository.save(odontologo);
    }
    public void actualizarOdontologo (Odontologo odontologo){
        logger.info("Se llama a odotologo Service para actualizar");
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo (Long id){
        logger.info("Se llama a odotologo Service para eliminar");
        odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarTodos(){
        logger.info("Se llama a odotologo Service para listar");
        return odontologoRepository.findAll();
    }
    public Optional<Odontologo> buscarPorId(Long id){
        logger.info("Se llama a odotologo Service para buscar por id");
        return odontologoRepository.findById(id);
    }
    public Optional<Odontologo> buscarPorMatricula(String matricula){
        logger.info("Se llama a odotologo Service para buscar por matricula");
        return odontologoRepository.findByMatricula(matricula);
    }
}
