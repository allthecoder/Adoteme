package com.example.adote.models.animals.types;

public enum OtherAnimalHabitat {
    DEFAULT(null), OUTDOORS("ao ar livre"), INDOORS("dentro de casa"), AQUARIUM("aquário"), TANK("tanque");
    private String custom;

    private OtherAnimalHabitat(String custom) {
        this.custom = custom;
    }

    public String getString() {
        return custom;
    }
}