package com.example.ProyectoJessiYAna.repository;

import com.example.ProyectoJessiYAna.model.Turno;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDAOLista implements iDao<Turno> {
    private static final Logger logguer= Logger.getLogger(TurnoDAOLista.class);
    @Autowired
    private List<Turno> turnoList=new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turnoList.add(turno);
        logguer.info("Se ha guardado el turno correctamente ");
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        //recorrer la lista con un ciclo
        for (Turno turno:turnoList
             ) {
            if (turno.getId().equals(id)){//sí el id buscado coincide con alguno en la lista ya guardado retorna ese turno
                logguer.info("Se ha encontrado al turno solicitado");
                return turno;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
         //buscar el turno con ese id y la anterior funcion creada
        Turno turnobuscado= buscar(id);
        // elimina el turno encontrado
        turnoList.remove(turnobuscado);
        logguer.info("Se ha eliminado el turno con id "+id);

    }

    @Override
    public void actualizar(Turno turno) {
        // uso nuevamente lo ya creado, primero elimino el turno con el id determinado
        eliminar(turno.getId());
        // ahora lo guardo de vuelta
        guardar(turno);
        logguer.info("Se ha actualizado el turno con id "+turno.getId());

    }

    @Override
    public List<Turno> buscarTodos() {
        logguer.info("Se listara todos los turnos ");
        return turnoList;
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
