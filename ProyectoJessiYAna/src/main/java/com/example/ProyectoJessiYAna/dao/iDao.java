package com.example.ProyectoJessiYAna.dao;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface iDao<T> {
    T guardar(T t);
    T buscar(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);
    List<T> buscarTodos();
    T buscarPorString(String valor);
}
