package com.example.ProyectoJessiYAna.daoOdontologo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IDaoOdontologo<T>{
    T guardar (T t);


    List<T> listar ();

    T buscar(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);
}
