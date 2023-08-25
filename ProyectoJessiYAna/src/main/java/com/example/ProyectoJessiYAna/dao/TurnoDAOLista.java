package com.example.ProyectoJessiYAna.dao;

import com.example.ProyectoJessiYAna.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDAOLista implements iDao<Turno> {
    @Autowired
    private List<Turno> turnoList=new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turnoList.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        //recorrer la lista con un ciclo
        for (Turno turno:turnoList
             ) {
            if (turno.getId().equals(id)){//sí el id buscado coincide con alguno en la lista ya guardado retorna ese turno
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

    }

    @Override
    public void actualizar(Turno turno) {
        // uso nuevamente lo ya creado, primero elimino el turno con el id determinado
        eliminar(turno.getId());
        // ahora lo guardo de vuelta
        guardar(turno);

    }

    @Override
    public List<Turno> buscarTodos() {

        return turnoList;
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
