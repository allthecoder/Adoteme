package com.example.adote.ui.menu;

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
import com.example.adote.adapter.PetAdapter;
import com.example.adote.helper.RecyclerItemClickListener;
import com.example.adote.models.animals.Animal;
import com.example.adote.helper.AnimalDBM;
import com.example.adote.models.user.User;
import com.example.adote.helper.DBMHelper;
import com.example.adote.ui.addpet.AddPetActivity;
import com.example.adote.ui.showpet.ShowPetDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdoptingPetsFragment extends Fragment {
    private RecyclerView adoptingRecView;
    private ArrayList<Animal> adoptingPets;
    PetAdapter petAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_adopting_pets, container, false);

        initializeViews(root);
        getAdoptingPets();

        adoptingRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adoptingRecView.setHasFixedSize(true);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        petAdapter = new PetAdapter(adoptingPets, getContext());
        adoptingRecView.setAdapter(petAdapter);

        adoptingRecView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        adoptingRecView,
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

    /**
     * Initializes all children views of root
     * @param root Root View
     */
    private void initializeViews(View root) {
        adoptingRecView = root.findViewById(R.id.adoptingPetsRecyclerView);
    }

    /**
     * Gets current user "adopting" pets
     */
    private void getAdoptingPets() {
        User user = DBMHelper.getCurrentUser();

        if (user == null) {
            return;
        }

        adoptingPets = AnimalDBM.getInstance().getAnimalsByIds(user.getAnimalsAdopting());
    }
}
