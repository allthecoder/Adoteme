package com.example.adote.ui.showpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.adote.R;
import com.example.adote.helper.DBMHelper;
import com.example.adote.helper.UserDBM;
import com.example.adote.models.animals.Animal;
import com.example.adote.models.animals.Dog;
import com.example.adote.models.animals.OtherAnimal;
import com.example.adote.models.user.User;

public class ShowPetDetailsActivity extends AppCompatActivity {
    ShowBasicInfoFragment showBasicInfoFragment;
    ShowDogFragment dogFragment;
    ShowOtherAnimalFragment otherAnimalFragment;
    ShowPredifinedInfoFragment showPredifinedInfoFragment;
    TextView mContact;

    Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pet_details);

        initializeFragments();

        animal = (Animal) getIntent().getSerializableExtra("Pet");
        showFragments(animal);
    }

    private void initializeFragments() {
        showBasicInfoFragment = new ShowBasicInfoFragment();
        dogFragment = new ShowDogFragment();
        otherAnimalFragment = new ShowOtherAnimalFragment();
        showPredifinedInfoFragment = new ShowPredifinedInfoFragment();
    }

    private void showFragments(Animal animal) {
        if (animal == null) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.showBasicInfoFrameLayout, showBasicInfoFragment);
        transaction.replace(R.id.showAnimalTypeFrameLayout, getAnimalTypeFragment(animal));
        transaction.replace(R.id.showPredifinedFieldsFrameLayout, showPredifinedInfoFragment);

        transaction.commit();
    }

    private Fragment getAnimalTypeFragment(Animal animal) {
        if (animal instanceof OtherAnimal) {
            return otherAnimalFragment;
        } else if (animal instanceof Dog) {
            return dogFragment;
        } else {
            return new Fragment();
        }
    }

    public void visualizePhone(View view){
        User user = UserDBM.getInstance().getUser(animal.getUserID());

        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", user.getInfo().getContact(),null));
        startActivity(i);
    }

}