package com.example.adote.models.animals.types;

public enum AnimalGender {
    DEFAULT(null), MAS("macho"), FEM("fêmea");
    private String custom;

    private AnimalGender(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
