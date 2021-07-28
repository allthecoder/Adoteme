package com.example.adote.ui.showpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adote.R;
import com.example.adote.models.animals.Animal;
import com.example.adote.models.animals.types.AnimalGender;
import com.example.adote.models.animals.types.AnimalHealth;
import com.example.adote.models.animals.types.AnimalSize;
import com.example.adote.models.animals.types.AnimalSociability;
import com.example.adote.models.animals.types.AnimalTemper;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShowPredifinedInfoFragment extends Fragment {
    ChipGroup mSize, mGender, mSociabilities, mTemper, mHealth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_predifined_info, container, false);
        initializeViews(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Animal animal = (Animal) getActivity().getIntent().getSerializableExtra("Pet");
        showPredifinedFieldsInfo(animal);
    }

    private void initializeViews(View v) {
        mSize = v.findViewById(R.id.showSizeChipGroup);
        mGender = v.findViewById(R.id.showGenderChipGroup);
        mSociabilities = v.findViewById(R.id.showSociabilitiesChipGroup);
        mTemper = v.findViewById(R.id.showTemperChipGroup);
        mHealth = v.findViewById(R.id.showHealtChipGroup);
    }

    private void showPredifinedFieldsInfo(Animal animal) {
        if (animal == null) {
            return;
        }
        showSize(animal.getSize());
        showGender(animal.getGender());
        showSociabilities(animal.getSociabilities());
        showTemper(animal.getTempers());
        showHealth(animal.getHealth());
    }

    private void showSize(AnimalSize size) {
        Chip chip = null;

        switch (size) {
            case SMALL:
                chip = mSize.findViewById(R.id.showSmallChip);
                break;
            case MEDIUM:
                chip = mSize.findViewById(R.id.showMediumChip);
                break;
            case BIG:
                chip = mSize.findViewById(R.id.showBigChip);
                break;
        }

        if (chip != null) {
            chip.setCheckable(true);
            chip.setChecked(true);
        }
    }

    private void showGender(AnimalGender gender) {
        Chip chip = null;

        switch (gender) {
            case FEM:
                chip = mGender.findViewById(R.id.showFemaleChip);
                break;
            case MAS:
                chip = mGender.findViewById(R.id.showMaleChip);
                break;
        }

        if (chip != null) {
            chip.setCheckable(true);
            chip.setChecked(true);
        }
    }

    private void showSociabilities(ArrayList<AnimalSociability> sociabilities) {
        for (AnimalSociability s : sociabilities) {
            Chip chip = null;

            switch (s) {
                case CATS:
                    chip = mSociabilities.findViewById(R.id.showCatsChip);
                    break;
                case DOGS:
                    chip = mSociabilities.findViewById(R.id.showDogsChip);
                    break;
                case KIDS:
                    chip = mSociabilities.findViewById(R.id.showKidsChip);
                    break;
                case STRANGERS:
                    chip = mSociabilities.findViewById(R.id.showStrangersChip);
                    break;
                case OTHER_ANIMALS:
                    chip = mSociabilities.findViewById(R.id.showOtherAnimalsChip);
                    break;
            }

            if (chip != null) {
                chip.setCheckable(true);
                chip.setChecked(true);
            }
        }
    }

    private void showTemper(ArrayList<AnimalTemper> tempers) {
        for (AnimalTemper t : tempers) {
            Chip chip = null;

            switch (t) {
                case AGGRESSIVE:
                    chip = mTemper.findViewById(R.id.showAgressiveChip);
                    break;
                case SHY:
                    chip = mTemper.findViewById(R.id.showShyChip);
                    break;
                case CALM:
                    chip = mTemper.findViewById(R.id.showCalmChip);
                    break;
                case NEUTRAL:
                    chip = mTemper.findViewById(R.id.showNeutralChip);
                    break;
                case PLAYFUL:
                    chip = mTemper.findViewById(R.id.showPlayfulChip);
                    break;
                case SOCIABLE:
                    chip = mTemper.findViewById(R.id.showSociableChip);
                    break;
            }

            if (chip != null) {
                chip.setCheckable(true);
                chip.setChecked(true);
            }
        }
    }

    private void showHealth(ArrayList<AnimalHealth> health) {
        for (AnimalHealth h : health) {
            Chip chip = null;

            switch (h) {
                case HEALTHY:
                    chip = mHealth.findViewById(R.id.showHealthyChip);
                    break;
                case INDISPOSED:
                    chip = mHealth.findViewById(R.id.showIndisposedChip);
                    break;
                case VACCINATED:
                    chip = mHealth.findViewById(R.id.showVaccinetedChip);
                    break;
                case FOOD_RESTRICTION:
                    chip = mHealth.findViewById(R.id.showFoodRestrictionChip);
                    break;
            }

            if (chip != null) {
                chip.setCheckable(true);
                chip.setChecked(true);
            }
        }
    }

}