package com.example.adote.models.animals.types;

public enum DogRace {
    DEFAULT(null), GOLDEN_RETRIEVER("golden"), SAUSAGE("salsicha"), HUSKY("husky"), GERMAN_SHEPHERD("pastor alemão");
    private String custom;

    private DogRace(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
