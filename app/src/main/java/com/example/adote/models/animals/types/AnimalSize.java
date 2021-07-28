package com.example.adote.models.animals.types;

public enum AnimalSize {
    DEFAULT(null), SMALL("pequeno"), MEDIUM("médio"), BIG("grande");
    private String custom;

    private AnimalSize(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return this.custom;
    }
}
