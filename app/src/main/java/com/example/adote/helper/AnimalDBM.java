package com.example.adote.helper;

import com.example.adote.models.animals.Animal;
import com.example.adote.models.animals.Cat;
import com.example.adote.models.animals.Dog;
import com.example.adote.models.animals.OtherAnimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Animal Database Manager Class
 */
public class AnimalDBM {
	private final static AnimalDBM dbm = new AnimalDBM(); // Singleton Database
	private HashMap<Integer, Animal> animals;

	private int nextId; // The unique id for each new HashMap entry

	/**
	 * Initializes a new AnimalDBM instance
	 */
	public AnimalDBM() {
		this.animals = new HashMap<>();
		nextId = 0;
	}

	/**
	 * Gets the singleton instance of the AnimalDBM class.
	 *
	 * @return AnimalDBM singleton.
	 */
	public static AnimalDBM getInstance(){
		return dbm;
	}

	/**
	 * Gets all animals in the a AnimalDBM instance.
	 *
	 * @return Animals ArrayList.
	 */
	public ArrayList<Animal> getAnimals() {
		ArrayList<Animal> animalsDisplay = new ArrayList<>(animals.values());

		Collections.shuffle(animalsDisplay); // So that the display is not always the same
		return animalsDisplay;
	}

	/**
	 * Adds a new Animal Instance to the AnimalDBM hashmap.
	 *
	 * @param animal Animla instance to be added.
	 */
	public void addAnimal(Animal animal) {
		animal.setAnimalID(this.nextId);

		this.animals.put(animal.getAnimalID(), animal);
		this.nextId++;
	}

	/**
	 * Filters ands returns a list containing only animals with a
	 * certain address.
	 *
	 * @param address Address filter.
	 * @return ArrayList containing only Animal instances with `address`
	 */
	public ArrayList<Animal> filterByAddress(String address) {
		ArrayList<Animal> filtered = new ArrayList<>();
		Iterator i = animals.entrySet().iterator();

		while (i.hasNext()) {
			HashMap.Entry mapElement = (HashMap.Entry)i.next();
			Animal animal = (Animal) mapElement.getValue();

			if (animal.getAddress().equals(address)) {
				filtered.add(animal);
			}
		}

		return filtered;
	}

	/**
	 * Filters ands returns a list containing only animals of a certain
	 * species.
	 *
	 * @param species Species filter.
	 * @return ArrayList containing only Animal instance with `species`.
	 */
	public ArrayList<Animal> filterBySpecies(String species) {
		ArrayList<Animal> filtered = new ArrayList<>();
		Iterator i = animals.entrySet().iterator();

		while (i.hasNext()) {
			HashMap.Entry mapElement = (HashMap.Entry)i.next();
			Animal animal = (Animal) mapElement.getValue();

			if (species.equals("Outros") && animal instanceof OtherAnimal) {
				filtered.add(animal);
			}

			if (species.equals("Gato") && animal instanceof Cat) {
				filtered.add(animal);
			}

			if (species.equals("Cachorro") && animal instanceof Dog) {
				filtered.add(animal);
			}

		}

		return filtered;
	}

	/**
	 * Gets an Animal from the AnimalDBM given its id.
	 *
	 * @param targetId Animal's id.
	 * @return Animal instance.
	 */
	public Animal getAnimalById(int targetId) {
		return animals.get(targetId);
	}

	/**
	 * Gets an list of animals given a list of matching ids.
	 *
	 * @param ids ArrayList of Animal's ids.
	 * @return Matching Animal ArrayList.
	 */
	public ArrayList<Animal> getAnimalsByIds(HashSet<Integer> ids) {
		ArrayList<Animal> searchedAnimals = new ArrayList<>();

		for (Integer id : ids) {
			searchedAnimals.add(animals.get(id));
		}

		return searchedAnimals;
	}

	/**
	 * Deletes an animal from the AnimalDBM.
	 *
	 * @param targetId Animal's id.
	 */
	public void deleteAnimal(int targetId) {
		animals.remove(targetId);
	}


	private void print() {

	}
}