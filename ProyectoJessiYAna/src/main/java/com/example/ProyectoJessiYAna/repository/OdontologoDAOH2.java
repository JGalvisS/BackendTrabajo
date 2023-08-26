package com.example.ProyectoJessiYAna.repository;

import com.example.ProyectoJessiYAna.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logguer = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_All="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_ONE= "SELECT * FROM ODONTOLOGOS WHERE ID= ?";
    private static final String SQL_DELETE = "DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE ODONTOLOGOS SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID =?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        try {
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute();
            ResultSet clave = psInsert.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }
            logguer.info("Se ha guardado los datos del odontologo en la base de datos");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }
    @Override
    public Odontologo buscar(Integer id) {
        Odontologo odontologo = null;
        Connection connection= null;
        try {
            connection= BD.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1)+" Numero matricula: "+rs.getString(2)+" Nombre: "+ rs.getString(3)+ " Apellido: "+ rs.getString(4));
                odontologo = new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
            logguer.info("Se ha encontrado al Odontologo buscado por ID");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        buscar(id);
        Connection connection= null;
        try {
            connection= BD.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1,id);
            statement.execute();

            logguer.info("Se ha eliminado a este Odontologo de la base de datos ");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }



    }
    @Override
    public void actualizar(Odontologo odontologo) {
        Connection connection= null;
        try {
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_UPDATE);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.setInt(4,odontologo.getId());
            psInsert.execute();
            logguer.info("El odontologo con ID " +odontologo.getId()+" ha sido actualizado correctamenete ");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }
    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection= null;
        List<Odontologo> odontologos= new ArrayList<>();
        Odontologo odontologo = null;
        try {
            connection= BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_All);
            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                odontologos.add(odontologo);
            }
            logguer.info("Se ha listado a todos los odontologos");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologos;

    }

    @Override
    public Odontologo buscarPorString(String valor) {
        return null;
    }


}
