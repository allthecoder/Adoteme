package com.example.adote.models.animals.types;

public enum AnimalHealth {
    VACCINATED("vacinado"), FOOD_RESTRICTION("restrição alimentar"), HEALTHY("saudável"), INDISPOSED("indisposição");
    private String custom;

    private AnimalHealth(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
