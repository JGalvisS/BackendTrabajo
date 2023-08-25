package com.example.ProyectoJessiYAna.daoOdontologo;

import org.springframework.stereotype.Component;

@Component
public interface IDaoOdontologo<T>{
    T guardar (T t);


    void listar ();

    T buscar(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);
}
