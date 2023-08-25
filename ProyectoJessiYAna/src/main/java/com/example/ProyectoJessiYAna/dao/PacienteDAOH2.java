package com.example.ProyectoJessiYAna.dao;

import com.example.ProyectoJessiYAna.model.Domicilio;
import com.example.ProyectoJessiYAna.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PacienteDAOH2 implements iDao<Paciente>{
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO,DOMICILIO_ID, EMAIL) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";
    private static final String SQL_UPDATE="UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, CEDULA=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM PACIENTES WHERE ID=?";

    @Override
    public Paciente guardar(Paciente paciente) {

        logger.info("inicio de operaciones guarado de un paciente");
        Connection connection= null;

        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            Domicilio domicilio= daoAux.guardar(paciente.getDomicilio());
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5,domicilio.getId());
            psInsert.setString(6,paciente.getEmail());
            psInsert.execute();
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                paciente.setId(clave.getInt(1));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
                  }
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        logger.info("inicio de operacion de : BUSCADO ");
        Connection connection= null;
        Paciente paciente= null;
        Domicilio domicilio=null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            while (rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("inicio de operacion de Eliminar: Paciente ");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1,id);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("inicio de operacion de Actualizar paciente con ID : "+paciente.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            daoAux.actualizar(paciente.getDomicilio());
            PreparedStatement psUpdate=connection.prepareStatement(SQL_UPDATE);
            //vienen las parametrizadas
            //NOMBRE=?, APELLIDO=?, CEDULA=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID=?"
            psUpdate.setString(1,paciente.getNombre());
            psUpdate.setString(2,paciente.getApellido());
            psUpdate.setString(3,paciente.getCedula());
            psUpdate.setDate(4,Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5,paciente.getDomicilio().getId());
            psUpdate.setString(6,paciente.getEmail());
            psUpdate.setInt(7,paciente.getId());
            psUpdate.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("inicio de operacion de : buscado de todos los pacientes ");
        Connection connection= null;
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente= null;
        Domicilio domicilio= null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectAll= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= psSelectAll.executeQuery();
            while (rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
                pacientes.add(paciente);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorString(String valor) {
        logger.info("inicio de operacion de : Buscado por Email  " + valor);
        Connection connection= null;
        Paciente paciente= null;
        Domicilio domicilio=null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectEmail= connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectEmail.setString(1,valor);
            ResultSet rs= psSelectEmail.executeQuery();
            while (rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return paciente;

    }
}
