
package com.example.adote.ui.addpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.adote.R;
import com.example.adote.helper.DBMHelper;
import com.example.adote.models.animals.OtherAnimal;
import com.example.adote.models.animals.types.AnimalStatus;
import com.example.adote.models.animals.types.OtherAnimalHabitat;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;

public class AddOtherFragment extends Fragment {
    private EditText mSpecie;
    private ChipGroup mHabitat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_other_animal, container, false);
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
     * @param root Root View.
     */
    private void initializeViews(View root) {
        mSpecie = root.findViewById(R.id.otherAnimalSpeciesEditText);
        mHabitat = root.findViewById(R.id.otherAnimalHabitatChipGroup);
    }

    public OtherAnimal createNewOtherAnimalInstance() {
        String specie = mSpecie.getText().toString();
        OtherAnimalHabitat habitat = getHabitat();

        if (TextUtils.isEmpty(specie)) {
            mSpecie.setError("Espécie obrigatória");
            return null;
        }

        OtherAnimal otherAnimal = new OtherAnimal();

        otherAnimal.setSpecies(specie);
        otherAnimal.setHabitat(habitat);
        otherAnimal.setUserID(DBMHelper.getCurrentUser().getId());
        otherAnimal.setAdopted(AnimalStatus.AVAILABLE);

        return otherAnimal;
    }

    private OtherAnimalHabitat getHabitat() {
        Chip habit = mHabitat.findViewById(mHabitat.getCheckedChipId());

        switch (habit.getId()) {
            case R.id.indoorsChip:
                return OtherAnimalHabitat.INDOORS;
            case R.id.outdoorsChip:
                return OtherAnimalHabitat.OUTDOORS;
            case R.id.aquariumChip:
                return OtherAnimalHabitat.AQUARIUM;
            case R.id.otherHabitatChip:
                return OtherAnimalHabitat.TANK;
            default:
                break;
        }

        return OtherAnimalHabitat.DEFAULT;
    }
}