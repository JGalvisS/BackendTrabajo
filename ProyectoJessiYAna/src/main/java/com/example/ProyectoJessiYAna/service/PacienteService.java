package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.dao.PacienteDAOH2;
import com.example.ProyectoJessiYAna.dao.iDao;
import com.example.ProyectoJessiYAna.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService {
    @Autowired
    private iDao<Paciente> pacienteiDao= new PacienteDAOH2();
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscar(id);
    }
    public void eliminarPaciente(Integer id){
        pacienteiDao.eliminar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public List<Paciente> obtenerPacientes(){
        return pacienteiDao.buscarTodos();
    }
    public Paciente buscarPorEmail(String correo){return pacienteiDao.buscarPorString(correo); }
}
