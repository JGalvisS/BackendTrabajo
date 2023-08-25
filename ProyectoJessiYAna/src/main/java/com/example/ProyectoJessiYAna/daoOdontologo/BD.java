package com.example.ProyectoJessiYAna.daoOdontologo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
    private static final Logger logger = Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE="DROP TABLE IF EXISTS ODONTOLOGO; CREATE TABLE ODONTOLOGO ( ID INT AUTO_INCREMENT PRIMARY KEY,  MATRICULA VARCHAR(20) NOT NULL, NOMBRE VARCHAR(50) NOT NULL, APELLIDO VARCHAR(50) NOT NULL)";
    public static void createTabla(){
        Connection connection= null;
        try {
            connection=getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            logger.info("La tabla de odontologos ya fue creada");

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            try {
                connection.close();
                logger.warn("Conexion cerrada");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public static Connection getConnection()throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/Odontologo-Ana_Camargo-Jessica_Katherine_Galvis_Silva");
    }
}
