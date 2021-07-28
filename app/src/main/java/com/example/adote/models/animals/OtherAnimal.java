package com.example.adote.models.animals;

import com.example.adote.models.animals.types.OtherAnimalHabitat;

import java.io.Serializable;

public class OtherAnimal extends Animal  implements Serializable {
    private String species;
    private OtherAnimalHabitat habitat;

    public void setSpecies(String species) {
        this.species = species;
    }
    public void setHabitat(OtherAnimalHabitat habitat) {
        this.habitat = habitat;
    }

    public OtherAnimalHabitat getHabitat() {
        return habitat;
    }
    public String getSpecies() {
        return species;
    }
}
