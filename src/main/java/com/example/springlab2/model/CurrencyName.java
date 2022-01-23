package com.example.springlab2.model;

public enum CurrencyName {

    US("Dollar"),
    EUR("Euro"),
    UK("Pound sterling");

    private String name;

    CurrencyName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
