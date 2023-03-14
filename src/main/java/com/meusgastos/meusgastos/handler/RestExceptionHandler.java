package com.meusgastos.meusgastos.handler;

import com.meusgastos.meusgastos.common.ConversorData;
import com.meusgastos.meusgastos.domain.exception.ResourceBadRequestException;
import com.meusgastos.meusgastos.domain.exception.ResourceNotFoundRequestException;
import com.meusgastos.meusgastos.domain.model.ErrorResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    public ResponseEntity<ErrorResposta> handlerResourceNotFoundException(ResourceNotFoundRequestException exception){

        String dataHora = ConversorData.convererDateParaString(new Date());

        ErrorResposta erroNotFound = new ErrorResposta(dataHora, 404, "Not Found", exception.getMessage());
        return new ResponseEntity<>(erroNotFound, HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<ErrorResposta> handlerResourceBadRequestException(ResourceBadRequestException exception){

        String dataHora = ConversorData.convererDateParaString(new Date());

        ErrorResposta erroBadRequest = new ErrorResposta(dataHora, 400, "Bad Request", exception.getMessage());
        return new ResponseEntity<>(erroBadRequest, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ErrorResposta> handlerRequestException(ResourceBadRequestException exception){

        String dataHora = ConversorData.convererDateParaString(new Date());

        ErrorResposta erro = new ErrorResposta(dataHora, 500 , "Internal Server Errror", exception.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
