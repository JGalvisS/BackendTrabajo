

import daoOdontologo.BD;
import daoOdontologo.OdontologoDAOH2;
import model.Odontologo;
import service.OdontologoService;


public class Main {


    public static void main(String[] args) {



        BD.createTabla();
        Odontologo odontologo = new Odontologo("AB123", "Katherine", "Galvis", 1);
        Odontologo odontologo1 = new Odontologo("AB456", "Ana", "Camargo", 2);
        OdontologoService service = new OdontologoService(new OdontologoDAOH2());

        service.guardarOdontolo(odontologo);
        service.guardarOdontolo(odontologo1);
        service.listarOdontologo(odontologo);





    }
}

