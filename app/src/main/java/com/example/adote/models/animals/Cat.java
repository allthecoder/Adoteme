package com.example.adote.models.animals;

import com.example.adote.models.animals.types.CatColor;

import java.io.Serializable;

public class Cat extends Animal implements Serializable {
    private CatColor color;

    public CatColor getColor() {
        return color;
    }
    public void setColor(CatColor color) {
        this.color = color;
    }
}
