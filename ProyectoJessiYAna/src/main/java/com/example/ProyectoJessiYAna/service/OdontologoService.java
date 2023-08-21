package com.example.ProyectoJessiYAna.service;


import com.example.ProyectoJessiYAna.daoOdontologo.IDaoOdontologo;
import com.example.ProyectoJessiYAna.model.Odontologo;
import org.apache.log4j.Logger;

public class OdontologoService {
    private static final Logger logger= Logger.getLogger(OdontologoService.class);
    private IDaoOdontologo<Odontologo> odontologoIDao;



    public OdontologoService(IDaoOdontologo<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }
    public Odontologo guardarOdontolo (Odontologo odontologo){
        logger.info("Se llama a Odontologo Service para guardar");
        return odontologoIDao.guardar(odontologo);
    }
    public Odontologo listarOdontologo(Odontologo odontologo){
        logger.info("Se llama a Odontologo Service para listar");
        return odontologoIDao.listar(odontologo);
    }
}
