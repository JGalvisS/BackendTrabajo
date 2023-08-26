package com.example.ProyectoJessiYAna.repository;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
    private static final Logger logger= Logger.getLogger(BD.class);
    private static final String SQL_CREATE="DROP TABLE IF EXISTS PACIENTES; " +
            "CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, " +
            "APELLIDO VARCHAR(100) NOT NULL, CEDULA VARCHAR(20) NOT NULL, FECHA_INGRESO DATE NOT NULL, DOMICILIO_ID INT NOT NULL, EMAIL VARCHAR(100) NOT NULL); " +
            "DROP TABLE IF EXISTS DOMICILIOS; CREATE TABLE DOMICILIOS ( ID INT AUTO_INCREMENT PRIMARY KEY, CALLE VARCHAR(100) NOT NULL, " +
            "NUMERO INT NOT NULL, LOCALIDAD VARCHAR(100) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL);"+
            "DROP TABLE IF EXISTS ODONTOLOGO; CREATE TABLE ODONTOLOGOS ( ID INT AUTO_INCREMENT PRIMARY KEY,  MATRICULA VARCHAR(20) NOT NULL, NOMBRE VARCHAR(50) NOT NULL, APELLIDO VARCHAR(50) NOT NULL)";
    private static final String SQL_PRUEBA="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES" +
            " ('Jorgito','Pereyra','122222','2023-05-08','1','jorge.pereyra@digitalhouse.com'),('Miguel Angel','Buitrago Rico','333333','2023-05-08','2','miguelbuitrago@digitalhouse.com'); " +
            "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES('Siempre Viva','742','Sprinfield','La Rioja'), ('Av. Bolivar','100','Bogota','Colombia') ";
    public static void crearTablas(){
        Connection connection= null;
        try{
            connection= getConnection();

            Statement statement= connection.createStatement();
            statement.execute(SQL_CREATE);
            statement.execute(SQL_PRUEBA);
            logger.info("datos de prueba cargados exitosamente");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }
    public static  Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/ClinicaOdontologica-Ana_Camargo-Jessica_Katherine_Galvis_Silva","admin","admin");

        // return DriverManager.getConnection("jdbc:h2:~/C3_a0723_ClinicaOdontologica","admin","admin");
    }
}
