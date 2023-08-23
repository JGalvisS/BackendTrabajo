package com.example.ProyectoJessiYAna.daoOdontologo;

public interface IDaoOdontologo<T>{
    T guardar (T t);


    void listar ();

    T buscar(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);
}
