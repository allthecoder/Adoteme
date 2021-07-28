package com.example.adote.models.user;

import java.io.Serializable;
import java.util.HashSet;

final public class User implements Serializable {
    private String id;
    private UserInfo info;

    private HashSet<Integer> animalsForAdoption;
    private HashSet<Integer> animalsAdopting;

    public String getId() {
        return id;
    }

    public UserInfo getInfo() {
        return info;
    }

    public HashSet<Integer> getAnimalsForAdoption() {
        return animalsForAdoption;
    }

    public HashSet<Integer> getAnimalsAdopting() {
        return animalsAdopting;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public void setAnimalsForAdoption(HashSet<Integer> animalsForAdoption) {
        this.animalsForAdoption = animalsForAdoption;
    }

    public void setAnimalsAdopting(HashSet<Integer> animalsAdopting) {
        this.animalsAdopting = animalsAdopting;
    }

    /**
     * Adds a new Animal in the User's "for adoption" section.
     *
     * @param animalId New animal's id.
     */
    public void addAnimalForAdoption(Integer animalId) {
        this.animalsForAdoption.add(animalId);
    }

    /**
     * Adds a new Animal in the User's "adopting" section.
     *
     * @param animalId New animal's id.
     */
    public void addAnimalAdopting(Integer animalId) {this.animalsAdopting.add(animalId);}

    /**
     * Removes an Animal from the User's "for adoption" section.
     *
     * @param animalId Animal's id.
     */
    public void removeAnimalForAdoption(Integer animalId) {
        this.animalsForAdoption.remove(animalId);
    }

    /**
     *  Removes an Animal from the User's "adopting" section.
     *
     * @param animalId Animal's id.
     */
    public void removeAnimalAdopting(Integer animalId) {
        this.animalsAdopting.remove(animalId);
    }

}
