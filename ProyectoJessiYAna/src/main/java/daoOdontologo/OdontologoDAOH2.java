package daoOdontologo;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;

public class OdontologoDAOH2 implements IDaoOdontologo<Odontologo> {
    private static final Logger logguer = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGO VALUES (?,?,?,?)";
    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGO";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        try {
            connection= daoOdontologo.BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.setInt(4,odontologo.getId());
            psInsert.execute();
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
    public Odontologo listar(Odontologo odontologo) {
        Connection connection= null;
        try {
            connection= BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(4)+" Numero matricula: "+rs.getString(1)+" Nombre: "+ rs.getString(2)+ " Apellido: "+ rs.getString(3));
            }

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
}
