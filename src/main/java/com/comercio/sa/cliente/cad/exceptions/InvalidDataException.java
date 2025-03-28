package com.comercio.sa.cliente.cad.exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(){ super("Erro de execução"); }

    public InvalidDataException(String message){ super(message); }
}
