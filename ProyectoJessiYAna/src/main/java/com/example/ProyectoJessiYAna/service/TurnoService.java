package com.example.ProyectoJessiYAna.service;

import com.example.ProyectoJessiYAna.repository.TurnoDAOLista;
import com.example.ProyectoJessiYAna.repository.iDao;
import com.example.ProyectoJessiYAna.model.Turno;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private static final Logger logger = Logger.getLogger(TurnoDAOLista.class);
    @Autowired
    private iDao<Turno> turnoiDao=new TurnoDAOLista();
    /*public TurnoService() {
        turnoiDao = new TurnoDAOLista();
    }*/
    // metodos manuales
    public List<Turno> listarTodosLosTurnos(){
        logger.info("Se llama a Turno Service para listar todos los turnos ");
        return turnoiDao.buscarTodos();
    }
    public Turno buscarPorId(Integer id){
        logger.info("Se llama Turno Service para buscar por id");
        return turnoiDao.buscar(id);
    }
    public void eliminarTurno(Integer id){
        logger.info("Se llama Turno Service para eliminar un turno ");
        turnoiDao.eliminar(id);
    }
    public void actualizarTurno(Turno turno){
        logger.info("Se llama Turno Service para actualizar un turno ");
        turnoiDao.actualizar(turno);
    }
    public Turno guardarTurno(Turno turno){
        logger.info("Se llama a Turno Service para guardar un turno");
        return turnoiDao.guardar(turno);
    }

}
