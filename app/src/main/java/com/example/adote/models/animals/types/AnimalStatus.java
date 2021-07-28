package com.example.adote.models.animals.types;

public enum AnimalStatus{
    AVAILABLE("disponível"), IN_PROCESS("em processo de adoção"), ADOPTED("adotadx");
    private String custom;

    private AnimalStatus(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
