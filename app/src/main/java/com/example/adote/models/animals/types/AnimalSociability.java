package com.example.adote.models.animals.types;

public enum AnimalSociability {
    STRANGERS("estranhos"), DOGS("cães"), CATS("gatos"), OTHER_ANIMALS("outros animais"), KIDS("crianças");
    private String custom;

    private AnimalSociability(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
