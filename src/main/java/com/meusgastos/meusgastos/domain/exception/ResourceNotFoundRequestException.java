package com.meusgastos.meusgastos.domain.exception;

public class ResourceNotFoundRequestException extends  RuntimeException{

    public ResourceNotFoundRequestException(String mensagem){
        super(mensagem);
    }
}
