package com.example.adote.ui.addpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adote.R;
import com.example.adote.models.animals.Animal;
import com.example.adote.models.animals.types.AnimalGender;
import com.example.adote.models.animals.types.AnimalHealth;
import com.example.adote.models.animals.types.AnimalSize;
import com.example.adote.models.animals.types.AnimalSociability;
import com.example.adote.models.animals.types.AnimalTemper;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import java.util.ArrayList;
import java.util.List;

public class AddPredifinedFieldsFragment extends Fragment {
    private ChipGroup mSize, mGender, mSociabilities, mTemper, mHealth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_predifined_fields, container, false);
        initializeViews(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Initializes all children views of root.
     *
     * @param root
     */
    private void initializeViews(View root) {
        mSize = (ChipGroup) root.findViewById(R.id.animalAddSizeChipGroup);
        mGender = (ChipGroup) root.findViewById(R.id.animalAddGenderChipGroup);
        mSociabilities = (ChipGroup) root.findViewById(R.id.animalAddSociabilitiesChipGroup);
        mTemper = (ChipGroup) root.findViewById(R.id.animalAddTemperChipGroup);
        mHealth = (ChipGroup) root.findViewById(R.id.animalsAddHealtChipGroup);
    }

    /**
     * Gets and all predefined fields info ands sets into instance.
     *
     * @param animal Animal instance where the info will be stored.
     * @return true if fields are valid and false if not.
     */
    public boolean animalSetPredifinedFields(Animal animal) {
        AnimalSize size = getSize();
        AnimalGender gender = getGender();
        ArrayList<AnimalSociability> sociabilities = getSociabilities();
        ArrayList<AnimalTemper> tempers = getTempers();
        ArrayList<AnimalHealth> health = getHealth();

        boolean isDataValid = true;
        if (size.equals(AnimalSize.DEFAULT)) {
            isDataValid = false;
        }

        if (gender.equals(AnimalGender.DEFAULT)) {
            isDataValid = false;
        }

        if (sociabilities.isEmpty()) {
            isDataValid = false;
        }

        if (tempers.isEmpty()) {
            isDataValid = false;
        }

        if (health.isEmpty()) {
            isDataValid = false;
        }

        if (!isDataValid) {
            return false;
        }

        animal.setSize(size);
        animal.setGender(gender);
        animal.setSociabilities(sociabilities);
        animal.setTempers(tempers);
        animal.setAnimalHealths(health);

        return true;
    }


    private AnimalSize getSize() {
        Chip checked = mSize.findViewById(mSize.getCheckedChipId());

        switch (checked.getId()) {
            case R.id.smallChip:
                return AnimalSize.SMALL;
            case R.id.bigChip:
                return AnimalSize.BIG;
            case R.id.mediumChip:
                return AnimalSize.MEDIUM;
            default:
                break;
        }

        return AnimalSize.DEFAULT;
    }

    private AnimalGender getGender() {
        Chip checked = mGender.findViewById(mGender.getCheckedChipId());

        switch (checked.getId()) {
            case R.id.maleChip:
                return AnimalGender.MAS;
            case R.id.femaleChip:
                return AnimalGender.FEM;
            default:
                break;
        }

        return AnimalGender.DEFAULT;
    }

    private ArrayList<AnimalSociability> getSociabilities() {
        ArrayList<AnimalSociability> sociabilities = new ArrayList();

        List<Integer> ids = mSociabilities.getCheckedChipIds();
        for (Integer id:ids) {
            Chip checked = mSociabilities.findViewById(id);

            switch (checked.getId()) {
                case R.id.strangersChip:
                    sociabilities.add(AnimalSociability.STRANGERS);
                    break;
                case R.id.catsChip:
                    sociabilities.add(AnimalSociability.CATS);
                    break;
                case R.id.dogsChip:
                    sociabilities.add(AnimalSociability.DOGS);
                    break;
                case R.id.kidsChip:
                    sociabilities.add(AnimalSociability.KIDS);
                    break;
                case R.id.otherAnimalsChip:
                    sociabilities.add(AnimalSociability.OTHER_ANIMALS);
                    break;
                default:
                    break;
            }
        }

        return sociabilities;
    }

    private ArrayList<AnimalTemper> getTempers() {
        ArrayList<AnimalTemper> tempers = new ArrayList();

        List<Integer> ids = mTemper.getCheckedChipIds();
        for (Integer id:ids) {
            Chip checked = mTemper.findViewById(id);

            switch (checked.getId()) {
                case R.id.agressiveChip:
                    tempers.add(AnimalTemper.AGGRESSIVE);
                    break;
                case R.id.neutralChip:
                    tempers.add(AnimalTemper.NEUTRAL);
                    break;
                case R.id.calmChip:
                    tempers.add(AnimalTemper.CALM);
                    break;
                case R.id.playfulChip:
                    tempers.add(AnimalTemper.PLAYFUL);
                    break;
                case R.id.shyChip:
                    tempers.add(AnimalTemper.SHY);
                    break;
                case R.id.sociableChip:
                    tempers.add(AnimalTemper.SOCIABLE);
                    break;
                default:
                    break;
            }
        }

        return tempers;
    }

    private ArrayList<AnimalHealth> getHealth() {
        ArrayList<AnimalHealth> health = new ArrayList();

        List<Integer> ids = mHealth.getCheckedChipIds();
        for (Integer id:ids) {
            Chip checked = mHealth.findViewById(id);

            switch (checked.getId()) {
                case R.id.vaccinetedChip:
                    health.add(AnimalHealth.VACCINATED);
                    break;
                case R.id.foodRestrictionChip:
                    health.add(AnimalHealth.FOOD_RESTRICTION);
                    break;
                case R.id.healthyChip:
                    health.add(AnimalHealth.HEALTHY);
                    break;
                case R.id.indisposedChip:
                    health.add(AnimalHealth.INDISPOSED);
                    break;
                default:
                    break;
            }
        }

        return health;
    }
}