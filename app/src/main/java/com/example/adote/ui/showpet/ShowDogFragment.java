package com.example.adote.ui.showpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adote.R;
import com.example.adote.models.animals.Dog;
import com.example.adote.models.animals.types.DogRace;
import com.google.android.material.chip.ChipGroup;

import org.jetbrains.annotations.NotNull;

public class ShowDogFragment extends Fragment {
    ChipGroup mTrained, mRace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_dog, container, false);
        initializeViews(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Dog dog = (Dog) getActivity().getIntent().getSerializableExtra("Pet");
        showDogInfo(dog);
    }

    private void initializeViews(View v) {
        mTrained = v.findViewById(R.id.showAnimalIsTrainedChipGroup);
        mRace = v.findViewById(R.id.showAnimalRaceChipGroup);
    }

    private void showDogInfo(Dog dog) {
        showRace(dog.getRace());
        showTrained(dog.isTrained());
    }

    private void showRace(DogRace race) {
        switch (race) {
            case HUSKY:
                mRace.check(R.id.showHuskyChip);
                break;
            case SAUSAGE:
                mRace.check(R.id.showSausageChip);
                break;
            case GERMAN_SHEPHERD:
                mRace.check(R.id.showGermanShepherdChip);
                break;
            case GOLDEN_RETRIEVER:
                mRace.check(R.id.showGoldenRetrieverChip);
                break;
        }
    }

    private void showTrained(boolean trained) {
        if (!(trained)) {
            mTrained.check(R.id.showTrainedChip);
        } else if (trained) {
            mTrained.check(R.id.showNotTrainedChip);
        }
    }

}