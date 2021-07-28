package com.example.adote.ui.addpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adote.MainActivity;
import com.example.adote.R;
import com.example.adote.helper.DBMHelper;
import com.example.adote.models.animals.Animal;
import com.example.adote.helper.AnimalDBM;
import com.example.adote.models.user.User;

public class AddPetActivity extends AppCompatActivity implements View.OnClickListener {
    private AddPredifinedFieldsFragment addPredifinedFieldsFragment;
    private AddBasicInfoFragment addBasicInfoFragment;
    private AddPetTypeFragment petTypeFragment;
    private Button finishButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);

        // Getting all fragments
        addBasicInfoFragment = new AddBasicInfoFragment();
        addPredifinedFieldsFragment = new AddPredifinedFieldsFragment();
        petTypeFragment = new AddPetTypeFragment();
        displayFragments();

        // Button
        finishButton = (Button) findViewById(R.id.animalAddFinishButton);

        finishButton.setOnClickListener(this::onClick);
    }

    /**
     * Displays all inital fragments.
     *
     */
    private void displayFragments() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.addBasicInfoFrameLayout, addBasicInfoFragment);
        transaction.replace(R.id.addPredifinedFieldsFrameLayout, addPredifinedFieldsFragment);
        transaction.replace(R.id.addAnimalTypeFrameLayout, petTypeFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animalAddFinishButton:
                if (createNewAnimalInstance()) {
                    startActivity(new Intent(AddPetActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Cadastro Incompleto.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * Creates an new Animal instance if all data is valid and adds
     * it to the animal database.
     *
     * @return true in case of success and false otherwise.
     */
    private boolean createNewAnimalInstance() {
        Animal animal = null;

        switch (petTypeFragment.getAnimalType()) {
            case R.id.catChip:
                animal = petTypeFragment.catFragment.createNewCatInstance();
                break;
            case R.id.dogChip:
                animal = petTypeFragment.dogFragment.createNewDogInstance();
                break;
            case R.id.otherAnimalChip:
                animal = petTypeFragment.otherFragment.createNewOtherAnimalInstance();
                break;
            default:
                break;
        }

        if (animal == null) {
            return false;
        }

        boolean isDataValid = addPredifinedFieldsFragment.animalSetPredifinedFields(animal)
                              && addBasicInfoFragment.animalSetBasicInfo(animal);

        if (!isDataValid) {
            return false;
        }

        AnimalDBM.getInstance().addAnimal(animal);
        DBMHelper.getCurrentUser().addAnimalForAdoption(animal.getAnimalID());

        return true;
    }


}