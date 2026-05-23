package com.example.CashFlowControlBackEnd.Exceptions;

import com.example.CashFlowControlBackEnd.Exceptions.Enums.GenericExceptionKey;

public class GenericException extends Exception {

    private final GenericExceptionKey genericExceptionKey;

    public GenericException(GenericExceptionKey genericExceptionKey) {
        super(genericExceptionKey.name());
        this.genericExceptionKey = genericExceptionKey;
    }

    public GenericExceptionKey getGenericExceptionKey() {
        return genericExceptionKey;
    }
}
