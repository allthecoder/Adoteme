package com.example.adote.ui.addpet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adote.R;
import com.example.adote.helper.AnimalDBM;
import com.example.adote.models.animals.Animal;

import java.io.File;
import java.io.InputStream;

public class AddBasicInfoFragment extends Fragment implements View.OnClickListener {
    private ImageButton mPhotoButton;
    private EditText mName, mAge, mAddress, mBio;
    private Spinner mAddressSpinner;
    private String photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_basic_info, container, false);
        initializeViews(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner();
        mPhotoButton.setOnClickListener(this::onClick);
    }

    /**
     * Initializes all children of root View.
     *
     * @param root Root View.
     */
    private void initializeViews(View root) {
        mName = root.findViewById(R.id.animalAddNameEditText);
        mAge = root.findViewById(R.id.animalAddAgeEditText);
//        mAddress = root.findViewById(R.id.animalAddAddressEditText);
        mBio = root.findViewById(R.id.animalAddBioEditText);
        mPhotoButton = root.findViewById(R.id.animalAddPhotoButton);
        mAddressSpinner = root.findViewById(R.id.animalAddAddressSpinner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animalAddPhotoButton:
                onAnimalPhotoButtonClicked();
                break;
        }
    }

    ActivityResultLauncher<Intent> launchImageGalleryForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        addAnimalPhoto(data);
                    }
                }
            });

    /**
     * Gets and sets the animal photo as the user chooses one.
     *
     * @param data Choose photo resulting data. In this case the ImageUri
     */
    private void addAnimalPhoto(Intent data) {
        Uri imageUri = data.getData();

        photo = imageUri.toString();
        mPhotoButton.setImageURI(imageUri);
    }

    /**
     * Launches the activity for getting the animals photo.
     *
     */
    public void onAnimalPhotoButtonClicked() {
        File photoDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String path = photoDir.getPath();
        Uri data = Uri.parse(path);

        Intent addPhotoIntent = new Intent(Intent.ACTION_PICK);
        addPhotoIntent.setDataAndType(data, "image/*");

        launchImageGalleryForResult.launch(addPhotoIntent);
    }

    /**
     * Gets the Animal's basic info from the EditTexts
     * and puts it in the animal instance.
     *
     * @param animal Animal instance.
     * @return true if given data is valid and false otherwise.
     */
    public boolean animalSetBasicInfo(Animal animal) {
        String name = mName.getText().toString();
        String age = mAge.getText().toString();
        String address = mAddressSpinner.getSelectedItem().toString();
        String bio = mBio.getText().toString();

        boolean isDataValid = true;
        if (photo == null) {
            isDataValid = false;
        }

        if (TextUtils.isEmpty(name)) {
            mName.setError("Nome obrigatório");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(address)) {
            mAddress.setError("Endereço obrigatorio");
            isDataValid = false;
        }

        if (!isDataValid) {
            return false;
        }

        animal.setPhoto(photo);
        animal.setName(name);
        animal.setAddress(address);
        animal.setAge(Integer.parseInt(age));
        animal.setBio(bio);

        return true;
    }

    /**
     * Sets the Spinner's display option
     */
    private void setSpinner() {
        String[] states = getResources().getStringArray(R.array.address);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, states
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mAddressSpinner.setAdapter(adapter);
    }

}