package com.example.adote.ui.showpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adote.R;
import com.example.adote.models.animals.OtherAnimal;
import com.example.adote.models.animals.types.OtherAnimalHabitat;
import com.google.android.material.chip.ChipGroup;

import org.jetbrains.annotations.NotNull;

public class ShowOtherAnimalFragment extends Fragment {
    TextView mSpecie;
    ChipGroup mHabitat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_other_animal, container, false);
        initializeViews(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OtherAnimal animal = (OtherAnimal) getActivity().getIntent().getSerializableExtra("Pet");
        showOtherAnimalInfo(new OtherAnimal());
    }

    private void initializeViews(View v) {
        mSpecie = v.findViewById(R.id.showSpeciesTextView);
        mHabitat = v.findViewById(R.id.showOtherAnimalHabitatChipGroup);
    }

    private void showOtherAnimalInfo(OtherAnimal animal) {
        mSpecie.setText(animal.getSpecies());
        showHabitat(animal.getHabitat());
    }

    private void showHabitat(OtherAnimalHabitat habitat) {
        switch (habitat) {
            case OUTDOORS:
                mHabitat.check(R.id.showOutdoorsChip);
                break;
            case TANK:
                mHabitat.check(R.id.showOtherHabitatChip);
                break;
            case INDOORS:
                mHabitat.check(R.id.showIndoorsChip);
                break;
            case AQUARIUM:
                mHabitat.check(R.id.showAquariumChip);
                break;
        }
    }
}