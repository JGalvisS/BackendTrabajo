package com.example.ProyectoJessiYAna.dao;

import com.example.ProyectoJessiYAna.model.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DomicilioDAOH2 implements iDao<Domicilio>{
    private static final Logger logger= Logger.getLogger(DomicilioDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM DOMICILIOS";
    private static final String SQL_DELETE="DELETE FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=? ";
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        logger.info("inicio de operacion de : guardado de un domicilio ");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            //tengo que preparar la tabla para insertar datos
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2,domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());
            psInsert.execute();//<<-- ejecutamos y comiteamos automatiocamnete
            ResultSet rs= psInsert.getGeneratedKeys();
            while (rs.next()){
            domicilio.setId(rs.getInt(1));//<-- es la ubicacion del id  en la columna de la tabla
            }



        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        logger.info("inicio de operacion de : ");
        Connection connection = null;
        Domicilio domicilio = null;
        try {
            connection = BD.getConnection();

            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1, id);
            ResultSet rs = psSelectOne.executeQuery();
            while (rs.next()) {
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("inicio de operacion de : ");
            Connection connection= null;
            try{
                connection= BD.getConnection();

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
    public void actualizar(Domicilio domicilio) {
        logger.info("inicio de operacion de Actualizar un Domicilio  : "+domicilio.getId());
                Connection connection= null;
                try{
                    connection= BD.getConnection();
                    //siempre despues van los PreparedStatement
                    PreparedStatement psUpdate=connection.prepareStatement(SQL_UPDATE);
                    // ahora van las parametrizadas
                    psUpdate.setString(1, domicilio.getCalle());
                    psUpdate.setInt(2,domicilio.getNumero());
                    psUpdate.setString(3,domicilio.getLocalidad());
                    psUpdate.setString(4, domicilio.getProvincia());
                    psUpdate.setInt(5,domicilio.getId());
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
    public List<Domicilio> buscarTodos() {
        logger.info("inicio de operacion de : ");
                    Connection connection= null;
                    List<Domicilio> domicilios= new ArrayList<>();
                    Domicilio domicilio= null;
                                        try{
                        connection= BD.getConnection();
                        PreparedStatement psSelectAll= connection.prepareStatement(SQL_SELECT_ALL);
                        ResultSet rs= psSelectAll.executeQuery();
                        while(rs.next()){
                            domicilio= new Domicilio(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                            domicilios.add(domicilio);                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        try {
                            connection.close();
                        }catch (SQLException ex){
                            ex.printStackTrace();
                        }
        return domicilios;
    }
}

    @Override
    public Domicilio buscarPorString(String valor) {
        return null;
    }
}
