package com.example.ProyectoJessiYAna.service;


import com.example.ProyectoJessiYAna.repository.OdontologoDAOH2;
import com.example.ProyectoJessiYAna.repository.iDao;
import com.example.ProyectoJessiYAna.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private static final Logger logger= Logger.getLogger(OdontologoService.class);
    @Autowired
    private iDao<Odontologo> odontologoiDao =new OdontologoDAOH2();


    public Odontologo guardarOdontolo (Odontologo odontologo){
        logger.info("Se llama a Odontologo Service para guardar");
        return odontologoiDao.guardar(odontologo);
    }
    public List<Odontologo> listarOdontologo( ){
        logger.info("Se llama a Odontologo Service para listar");
        return odontologoiDao.buscarTodos();
    }
    public Odontologo buscarOdontologoPorID (Integer id){
        logger.info("Se llama a Odontologo Service para buscar por ID numero "+id);
        return odontologoiDao.buscar(id);
    }
    public void eliminarOdontologo (Integer id){
        logger.info("Se llama a Odontologo Service para eliminar a este Odontologo de la base de datos");
        odontologoiDao.eliminar(id);
    }
    public void actualizarOdontologo (Odontologo odontologo){
        logger.info("Se llama a Odontologo Service para actualizar al odontologo con id "+odontologo.getId());
        odontologoiDao.actualizar(odontologo);
    }
}
