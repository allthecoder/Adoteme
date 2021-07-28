package com.example.adote.ui.addpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adote.R;
import com.google.android.material.chip.ChipGroup;


public class AddPetTypeFragment extends Fragment implements ChipGroup.OnCheckedChangeListener {
    ChipGroup mAnimalType;
    AddDogFragment dogFragment;
    AddCatFragment catFragment;
    AddOtherFragment otherFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_animal_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dogFragment = new AddDogFragment();
        otherFragment = new AddOtherFragment();
        catFragment = new AddCatFragment();

        mAnimalType = (ChipGroup) view.findViewById(R.id.animalTypeChipGroup);
        mAnimalType.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    @Override
    public void onCheckedChanged(ChipGroup group, int checkedId) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (checkedId) { // Gets the correct animal fragment to complete the registration
            case R.id.catChip:
                transaction.replace(R.id.chosenAnimalTypeFrameLayout, catFragment);
                break;
            case R.id.dogChip:
                transaction.replace(R.id.chosenAnimalTypeFrameLayout, dogFragment);
                break;
            case R.id.otherAnimalChip:
                transaction.replace(R.id.chosenAnimalTypeFrameLayout, otherFragment);
                break;
            default:
                break;
        }

        transaction.commit();
    }

    public int getAnimalType() {
        return mAnimalType.getCheckedChipId();
    }
}