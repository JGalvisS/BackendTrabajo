package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.entity.Paciente;
import com.example.ProyectoJessiYAna.repository.PacienteRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Llama al Service de Paciente")
public class PacienteService {
    @Autowired // se va autoinyectar cada que sea necesaria o llamada
    private PacienteRepository pacienteRepository; //crea una asociacion con la interface PacienteRepository que tiene los metodos de percisitencia
    //metodos manuales
    public Paciente guardarPaciente (Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public void actualizarPaciente (Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente (Long id){
        pacienteRepository.deleteById(id);
    }
    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPorId(Long id){
        return  pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorEmail(String email){
        return  pacienteRepository.findByEmail(email);
    }

}
