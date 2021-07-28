package com.example.adote.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adote.R;
import com.example.adote.helper.DBMHelper;

import org.jetbrains.annotations.NotNull;

public class InstProfileFragment extends Fragment implements View.OnClickListener {
    TextView mSite;
    ImageButton mChangeSite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inst_profile, container, false);
        initializeViews(rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showInstUserInfo();
        mChangeSite.setOnClickListener(this::onClick);

    }

    private void initializeViews(View root) {
        mSite = root.findViewById(R.id.userSiteTextView);
        mChangeSite = root.findViewById(R.id.changeUserSiteButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeUserSiteButton:
                // Make an EditText to edit the usersite
                break;
        }
    }

    private void showInstUserInfo() {
        mSite.setText(DBMHelper.getCurUserSite());
    }
}