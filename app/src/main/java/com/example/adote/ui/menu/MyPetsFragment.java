package com.example.adote.ui.menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.adote.R;
import com.example.adote.models.user.User;
import com.example.adote.helper.DBMHelper;
import com.example.adote.ui.showpet.ShowPetDetailsActivity;
import com.example.adote.adapter.PetAdapter;
import com.example.adote.models.animals.Animal;
import com.example.adote.helper.AnimalDBM;
import com.example.adote.helper.RecyclerItemClickListener;
import com.example.adote.ui.addpet.AddPetActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class MyPetsFragment extends Fragment implements View.OnClickListener {
    RecyclerView myPetRecycler;
    FloatingActionButton addPetButton;
    ArrayList<Animal> myPets = new ArrayList<>();
    PetAdapter petAdapter;
    AlertDialog dialog;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_pets, container, false);

        initializeViews(root);
        getPets();

        myPetRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        myPetRecycler.setHasFixedSize(true);

        addPetButton.setOnClickListener(this::onClick);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();
    }

    /**
     * Sets an adapter for the recycler view.
     */
    private void setAdapter() {
        petAdapter = new PetAdapter(myPets, getContext());
        myPetRecycler.setAdapter(petAdapter);
        getPets();

        myPetRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        myPetRecycler,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Animal pet = petAdapter.getItem(position);

                                Intent i = new Intent(view.getContext(), ShowPetDetailsActivity.class);
                                i.putExtra("Pet", pet);
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Animal pet = petAdapter.getItem(position);
                                removePet(pet, view);
                                petAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        }
                )
        );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addPetActionButton:
                startActivity(new Intent(getActivity(), AddPetActivity.class));
                break;
        }
    }

    /**
     * Initializes all children views of root.
     *
     * @param root Root View
     */
    private void initializeViews(View root) {
        myPetRecycler = root.findViewById(R.id.myPetGalelryRecyclerView);
        addPetButton = root.findViewById(R.id.addPetActionButton);
    }

    /**
     * Gets the pets of an user
     */
    private void getPets() {
        User user = DBMHelper.getCurrentUser();

        if (user == null) {
            return;
        }

        myPets = AnimalDBM.getInstance().getAnimalsByIds(user.getAnimalsForAdoption());
    }

    /**
     * Removes a pet that a user put
     */
    private void removePet(Animal animal, View view) {
        dialog = new SpotsDialog.Builder()
                .setContext(view.getContext())
                .setMessage("Deletando Anúncio")
                .setCancelable(false)
                .build();
        dialog.show();

        User user = DBMHelper.getCurrentUser();

        if (user == null) {
            return;
        }

        user.removeAnimalAdopting(animal.getAnimalID());
        AnimalDBM.getInstance().deleteAnimal(animal.getAnimalID());
        dialog.dismiss();
    }
}
