package com.example.adote.ui.addpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adote.R;
import com.example.adote.helper.DBMHelper;
import com.example.adote.models.animals.Dog;
import com.example.adote.models.animals.types.AnimalStatus;
import com.example.adote.models.animals.types.DogRace;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;


public class AddDogFragment extends Fragment {
    private ChipGroup mTrained, mRace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_dog, container, false);
        initializeViews(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Initializes all children views of root.
     * @param root Root View.
     */
    private void initializeViews(View root) {
        mTrained = root.findViewById(R.id.animalIsTrainedChipGroup);
        mRace = root.findViewById(R.id.animalRaceChipGroup);
    }

    public Dog createNewDogInstance() {
        DogRace race = getRace();
        boolean isTrained = getIsTrained();

        Dog dog = new Dog();

        dog.setRace(race);
        dog.setTrained(isTrained);
        dog.setUserID(DBMHelper.getCurrentUser().getId());
        dog.setAdopted(AnimalStatus.AVAILABLE);

        return dog;
    }

    private DogRace getRace() {
        Chip race = mRace.findViewById(mRace.getCheckedChipId());

        switch (race.getId()) {
            case R.id.straightChip:
                return DogRace.DEFAULT;
            case R.id.goldenRetrieverChip:
                return DogRace.GOLDEN_RETRIEVER;
            case R.id.huskyChip:
                return DogRace.HUSKY;
            case R.id.germanShepherdChip:
                return DogRace.GERMAN_SHEPHERD;
            default:
                break;
        }

        return DogRace.DEFAULT;
    }

    private boolean getIsTrained() {
        Chip trained = mTrained.findViewById(mTrained.getCheckedChipId());

        switch (trained.getId()) {
            case R.id.trainedChip:
                return true;
            case R.id.notTrainedChip:
                return false;
        }

        return false;
    }
}