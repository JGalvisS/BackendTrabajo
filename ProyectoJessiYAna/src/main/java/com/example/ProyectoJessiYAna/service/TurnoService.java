package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.dao.TurnoDAOLista;
import com.example.ProyectoJessiYAna.dao.iDao;
import com.example.ProyectoJessiYAna.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private iDao<Turno> turnoiDao=new TurnoDAOLista();
    /*public TurnoService() {
        turnoiDao = new TurnoDAOLista();
    }*/
    // metodos manuales
    public List<Turno> listarTodosLosTurnos(){
        return turnoiDao.buscarTodos();
    }
    public Turno buscarPorId(Integer id){
        return turnoiDao.buscar(id);
    }
    public void eliminarTurno(Integer id){
        turnoiDao.eliminar(id);
    }
    public void actualizarTurno(Turno turno){
        turnoiDao.actualizar(turno);
    }
    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }

}
