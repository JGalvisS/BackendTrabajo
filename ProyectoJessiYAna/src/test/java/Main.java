

import com.example.ProyectoJessiYAna.daoOdontologo.BD;
import com.example.ProyectoJessiYAna.daoOdontologo.OdontologoDAOH2;
import com.example.ProyectoJessiYAna.model.Odontologo;
import com.example.ProyectoJessiYAna.service.OdontologoService;


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

