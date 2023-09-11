package com.example.ProyectoJessiYAna.exception;

public class ResourceNotFoundException extends Exception{//esta es una clase padre de java y trae metodos predeterminados de manejo de excepciones
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
