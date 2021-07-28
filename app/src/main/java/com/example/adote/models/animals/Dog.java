package com.example.adote.models.animals;


import com.example.adote.models.animals.types.DogRace;

import java.io.Serializable;

public class Dog extends Animal implements Serializable {
    private DogRace race;
    private boolean trained;

    public void setRace(DogRace race) {
        this.race = race;
    }
    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public DogRace getRace() {
        return race;
    }
    public boolean isTrained() {
        return trained;
    }
}
