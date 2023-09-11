package com.example.ProyectoJessiYAna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //la anotacion sirve para informar que el va ha manejar o responder las exceptions
//CON Ctrl + Q PUEDO VER LOS METODOS DE UNA ANOTACION
public class GlobalException {
    @ExceptionHandler ({ResourceNotFoundException.class})// este es el capturador de exceptions en este caso va a capturar todo lo que que sea de la clase ResourseNotFoundException
    public ResponseEntity<String> manejoResourseNotFoundExption(ResourceNotFoundException resourceNotFoundException){// cuando reciba un ResourceNotFoundException
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage()); // va a devolver el mensaje estipulado en el controller y ResposeEntity 404
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> manejoBadRequestException(BadRequestException badRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestException.getMessage());
    }
}
