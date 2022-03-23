package com.example.calculemental.fichiers.model;

public enum TypeOperationEnum {
    ADD("+"),
    MULTIPLY("x"),
    SUBSTRACT("-"),
    DIVIDE("/");

    private String symbol;

    TypeOperationEnum(String symbol) {
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
