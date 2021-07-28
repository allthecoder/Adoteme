package com.example.adote.models.animals.types;

public enum AnimalType {
    DEFAULT(null), DOG("cachorrx"), CAT("gatx"), OTHER("outro");
    private String custom;

    private AnimalType(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
