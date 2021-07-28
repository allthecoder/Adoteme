package com.example.adote.ui.showpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adote.R;
import com.example.adote.models.animals.Animal;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;


public class ShowBasicInfoFragment extends Fragment {
    ImageView mPhoto;
    TextView mNameAndAge, mBio, mAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_show_basic_info, container, false);
        initializeViews(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Animal animal = (Animal) getActivity().getIntent().getSerializableExtra("Pet");
        showBasicAnimalInfo(animal);
    }

    private void initializeViews(View v) {
        mPhoto = v.findViewById(R.id.showAnimalPhoto);
        mNameAndAge = v.findViewById(R.id.showAnimalNameAndAge);
        mBio = v.findViewById(R.id.showAnimalBio);
        mAddress = v.findViewById(R.id.showAnimalAddress);
    }

    private void showBasicAnimalInfo(Animal animal) {
        String url = animal.getPhoto();
        Picasso.get().load(url).into(mPhoto);
        mNameAndAge.setText(animal.getName() + ", " + animal.getAge());
        mBio.setText(animal.getBio());
        mAddress.setText(animal.getAddress());
    }



}