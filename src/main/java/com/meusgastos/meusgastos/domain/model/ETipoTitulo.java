package com.meusgastos.meusgastos.domain.model;

public enum ETipoTitulo {

        A_RECEBER ("A receber"),
        A_Pagar("A pagar");

        private String valor;

        private ETipoTitulo(String valor){
            this.valor = valor;
        }

        public String getValor(){
            return this.valor;
        }

}
