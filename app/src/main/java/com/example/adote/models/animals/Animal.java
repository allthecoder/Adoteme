package com.example.adote.models.animals;

import android.text.TextUtils;

import com.example.adote.models.animals.types.AnimalGender;
import com.example.adote.models.animals.types.AnimalHealth;
import com.example.adote.models.animals.types.AnimalSize;
import com.example.adote.models.animals.types.AnimalSociability;
import com.example.adote.models.animals.types.AnimalStatus;
import com.example.adote.models.animals.types.AnimalTemper;

import java.io.Serializable;
import java.util.ArrayList;

public class Animal implements Serializable {
    private String name;
    private String bio;
    private String address;
    private Integer age;

    private String userID;
    private Integer animalID;

    private AnimalStatus adopted;
    private String adopterID;

    private AnimalSize animalSize;
    private AnimalGender animalGender;
    private ArrayList<AnimalSociability> sociabilities;
    private ArrayList <AnimalTemper> animalTempers;
    private ArrayList <AnimalHealth> animalHealths;

    private String photo;

    public void setName(String name) {
        this.name = name;
    }
    public void setBio(String bio) {
        if (TextUtils.isEmpty(bio)) {
            createStandardBio();
        } else {
            this.bio = bio;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setAnimalID(Integer animalID) {
        this.animalID = animalID;
    }
    public void setAdopted(AnimalStatus adopted) {
        this.adopted = adopted;
    }
    public void setAdopterID(String adopterID) {
        this.adopterID = adopterID;
    }
    public void setSize(AnimalSize animalSize) {
        this.animalSize = animalSize;
    }
    public void setGender(AnimalGender animalGender) {
        this.animalGender = animalGender;
    }
    public void setSociabilities(ArrayList <AnimalSociability> sociabilities) {
        this.sociabilities = sociabilities;
    }
    public void setTempers(ArrayList <AnimalTemper> animalTempers) {
        this.animalTempers = animalTempers;
    }
    public void setAnimalHealths(ArrayList <AnimalHealth> animalHealths) {
        this.animalHealths = animalHealths;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }
    public String getBio() {
        return bio;
    }
    public String getAddress() {
        return address;
    }
    public Integer getAge() {
        return age;
    }
    public String getUserID() {
        return userID;
    }
    public Integer getAnimalID() {
        return animalID;
    }
    public AnimalStatus getAdopted() {
        return adopted;
    }
    public String getAdopterID() {
        return adopterID;
    }
    public AnimalSize getSize() {
        return animalSize;
    }
    public AnimalGender getGender() {
        return animalGender;
    }
    public ArrayList <AnimalTemper>  getTempers() {
        return animalTempers;
    }
    public ArrayList <AnimalSociability>  getSociabilities() {
        return sociabilities;
    }
    public ArrayList <AnimalHealth>  getHealth() {
        return animalHealths;
    }
    public String getPhoto() {
        return photo;
    }

    /**
     * Creates an standard bio for the animal in case the user
     * has not given one.
     */

    private void createStandardBio () {
        String stdBio = "Oooi, eu sou o(a)" + getName() + " e tenho porte " + getSize().getString() + ". " +
                "Falando mais sobre mim, sou ";
        for (AnimalTemper temper : getTempers()) {
            stdBio += temper.getString() + ", ";
        }
        stdBio += "e também sou sociável com ";
        for (AnimalSociability social : getSociabilities()) {
            stdBio += social.getString() + ", ";
        }
        stdBio += "atualmente estou andando por " + getAddress() + ". Será que você poderia me dar um novo lar?";

        bio = stdBio;
    }
}
