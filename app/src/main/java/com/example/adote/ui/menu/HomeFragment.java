package com.example.adote.ui.menu;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adote.R;
import com.example.adote.adapter.PetAdapter;
import com.example.adote.helper.RecyclerItemClickListener;
import com.example.adote.models.animals.Animal;
import com.example.adote.helper.AnimalDBM;
import com.example.adote.models.user.User;
import com.example.adote.helper.DBMHelper;
import com.example.adote.ui.showpet.ShowPetDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    Button mAddressFilter, mSpeciesFilter;
    Spinner filterSpinner;
    RecyclerView allPets;
    ArrayList<Animal> pets = new ArrayList<>();
    PetAdapter petAdapter;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Initializes all other views inside root
        initializeViews(root);
        getAllPets();

        // Setting up the RecyclerView
        allPets.setLayoutManager(new GridLayoutManager(getContext(), 2));
        allPets.setHasFixedSize(true);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();
        mAddressFilter.setOnClickListener(this);
        mSpeciesFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addressFilterButton:
                filterByAddress(v);
                break;
            case R.id.speciesFilterButton:
                filterBySpecies(v);
                break;
        }
    }

    /**
     * Initializes all children views of root.
     *
     * @param root Root View.
     */
    private void initializeViews(View root) {
        mAddressFilter = root.findViewById(R.id.addressFilterButton);
        mSpeciesFilter = root.findViewById(R.id.speciesFilterButton);
        allPets = root.findViewById(R.id.homeRecyclerView);
    }

    /**
     * Shows address filter dialog.
     *
     * @param root Root view.
     */
    private void filterByAddress(View root) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(root.getContext());
        dialog.setTitle("Selecione o estado desejado");

        View viewSpinner = getLayoutInflater().inflate(R.layout.filter_dialog_spiner, null);
        loadSpinner(viewSpinner, getResources().getStringArray(R.array.address));
        dialog.setView(viewSpinner);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String addressFilter = filterSpinner.getSelectedItem().toString();
                pets = AnimalDBM.getInstance().filterByAddress(addressFilter);
                setAdapter();
                petAdapter.notifyDataSetChanged();
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });

        dialog.create().show();
    }

    /**
     * Shows filter by species dialog.
     *
     * @param root
     */
    private void filterBySpecies(View root) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(root.getContext());
        dialog.setTitle("Selecione a especie");

        View viewSpinner = getLayoutInflater().inflate(R.layout.filter_dialog_spiner, null);
        loadSpinner(viewSpinner, getResources().getStringArray(R.array.species));
        dialog.setView(viewSpinner);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String species = filterSpinner.getSelectedItem().toString();
                pets = AnimalDBM.getInstance().filterBySpecies(species);
                setAdapter();
                petAdapter.notifyDataSetChanged();
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });

        dialog.create().show();
    }

    /**
     * Gets all pets (NO FILTER).
     */
    private void getAllPets() {
        User user = DBMHelper.getCurrentUser();
        if (user == null) {
            return;
        }

        pets = AnimalDBM.getInstance().getAnimals();
    }

    /**
     * Load the Spinner and Set's its adapter.
     *
     * @param viewSpinner Spinners view
     * @param states Adapter states
     */
    private void loadSpinner(View viewSpinner, String[] states) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                viewSpinner.getContext(), android.R.layout.simple_spinner_item, states
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filterSpinner = viewSpinner.findViewById(R.id.spinnerFilter);
        filterSpinner.setAdapter(adapter);
    }

    /**
     * Sets the adapter for the recycler view.
     */
    private void setAdapter() {
        petAdapter = new PetAdapter(pets, getContext());
        allPets.setAdapter(petAdapter);

        allPets.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        allPets,
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

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        }
                )
        );
    }


}