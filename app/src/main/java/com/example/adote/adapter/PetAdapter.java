package com.example.adote.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adote.R;
import com.example.adote.models.animals.Animal;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.MyViewHolder> {
    List<Animal> pets;
    Context context;

    public PetAdapter(List<Animal> pets, Context context) {
        this.pets = pets;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pet, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        Animal pet = pets.get(position);
        Log.d("Pet", "aaaaaaaaaaaaaaaaa");

        holder.mName.setText(pet.getName());
        holder.mBio.setText(pet.getBio());

        String url = pet.getPhoto();
        Picasso.get().load(url).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public Animal getItem(int position) {
        return pets.get(position);
    }

    public void updateData(ArrayList<Animal> pets) {
        pets.clear();
        pets.addAll(pets);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName, mBio;
        ImageView mPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.petNameTextView);
            mBio = (TextView) itemView.findViewById(R.id.petBioTextView);
            mPhoto = (ImageView) itemView.findViewById(R.id.petPhotoImageView);
        }
    }

}
