package com.example.adote.models.animals.types;

public enum CatColor {
    DEFAULT(null), ORANGE("laranja"), CALICO("calico"), BLACK("preto"), WHITE("branco"), GREY("cinza");
    private String custom;

    private CatColor(String custom) {
        this.custom = custom;
    }
    public String getString() {
        return custom;
    }
}
