package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.repository.PacienteRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PacienteService {

    private static final Logger logger = Logger.getLogger(PacienteService.class);
    @Autowired // se va autoinyectar cada que sea necesaria o llamada
    private PacienteRepository pacienteRepository; //crea una asociacion con la interface PacienteRepository que tiene los metodos de percisitencia
    //metodos manuales
    public Paciente guardarPaciente (Paciente paciente){
        logger.info("Se llama a Paciente Service para guardar");
        return pacienteRepository.save(paciente);
    }
    public void actualizarPaciente (Paciente paciente){
        logger.info("Se llama a Paciente Service para actualizar");
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente (Long id){
        logger.info("Se llama a Paciente Service para eliminar");
        pacienteRepository.deleteById(id);
    }
    public List<Paciente> listarTodos(){
        logger.info("Se llama a Paciente Service para listar ");
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPorId(Long id){
        logger.info("Se llama a Paciente Service para buscar por id");
        return  pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorEmail(String email){
        logger.info("Se llama a Paciente Service para buscar por email ");
        return  pacienteRepository.findByEmail(email);
    }

}
