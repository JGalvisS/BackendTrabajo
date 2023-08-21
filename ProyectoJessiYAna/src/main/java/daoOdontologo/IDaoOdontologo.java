package daoOdontologo;

import model.Odontologo;

public interface IDaoOdontologo<T>{
    T guardar (T t);


    T listar (T t);
}
