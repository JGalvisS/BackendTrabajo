package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.repository.PacienteDAOH2;
import com.example.ProyectoJessiYAna.repository.iDao;
import com.example.ProyectoJessiYAna.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService {
    private static final Logger logger= Logger.getLogger(OdontologoService.class);
    @Autowired
    private iDao<Paciente> pacienteiDao= new PacienteDAOH2();
    public Paciente guardarPaciente(Paciente paciente){
        logger.info("Se llama a Paciente Service para guardar");
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        logger.info("Se llama a Paciente Service para buscar por ID numero "+id);
        return pacienteiDao.buscar(id);
    }
    public void eliminarPaciente(Integer id){
        logger.info("Se llama a Paciente Service para eliminar a este Paciente de la base de datos");
        pacienteiDao.eliminar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        logger.info("Se llama a Paciente Service para actualizar al Paciente con id "+paciente.getId());
        pacienteiDao.actualizar(paciente);
    }
    public List<Paciente> obtenerPacientes(){
        logger.info("Se llama a Paciente Service para listar");
        return pacienteiDao.buscarTodos();
    }
    public Paciente buscarPorEmail(String correo){
        logger.info("Se llama a Paciente Service para buscar al paciente  con el correo "+ correo);
        return pacienteiDao.buscarPorString(correo); }
}
