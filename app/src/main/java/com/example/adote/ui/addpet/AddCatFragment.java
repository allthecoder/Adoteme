package com.example.adote.ui.addpet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adote.R;
import com.example.adote.helper.DBMHelper;
import com.example.adote.models.animals.Cat;
import com.example.adote.models.animals.types.AnimalStatus;
import com.google.firebase.auth.FirebaseAuth;

public class AddCatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_cat, container, false);
    }

    public Cat createNewCatInstance() {
        Cat cat = new Cat();

        cat.setUserID(DBMHelper.getCurrentUser().getId());
        cat.setAdopted(AnimalStatus.AVAILABLE);

        return cat;
    }

}