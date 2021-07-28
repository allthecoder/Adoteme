package com.example.adote.models.animals.types;

public enum AnimalTemper {
    ASSERTIVE("assertivx"), AGGRESSIVE("agressivx"), NEUTRAL("neutrx"), PASSIVE("passivx"), CALM("calmx"), PLAYFUL("brincalhão/brincalhona"), SHY("tímidx"), SOCIABLE("sociável");
    private String custom;

    private AnimalTemper(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
