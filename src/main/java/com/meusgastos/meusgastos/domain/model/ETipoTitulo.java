package com.meusgastos.meusgastos.domain.model;

public enum ETipoTitulo {

    RECEBER("A receber"),
    PAGAR("A pagar");

    private String valor;

    private ETipoTitulo(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }

}
