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


public class PrivProfileFragment extends Fragment implements View.OnClickListener {
    TextView mNickname, mBirthday;
    ImageButton mChangeNickname, mChangeBirthday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_priv_profile, container, false);
        initializeViews(v);
        
        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showPrivateUserInfo();
        mChangeBirthday.setOnClickListener(this);
        mChangeNickname.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeUserBirthdayButton:
                // Make an EditText to edit the user birthday
                break;
            case R.id.changeUserNicknamesButton:
                // Make an editText to edit the user nickname
                break;
        }
    }

    private void initializeViews(View v) {
        mNickname = v.findViewById(R.id.userNicknameTextView);
        mBirthday = v.findViewById(R.id.userBirthdayTextView);

        mChangeBirthday = v.findViewById(R.id.changeUserBirthdayButton);
        mChangeNickname = v.findViewById(R.id.changeUserNicknamesButton);
    }

    private void showPrivateUserInfo() {
        mBirthday.setText(DBMHelper.getCurUserBirthday());
        mNickname.setText(DBMHelper.getCurUserNickname());
    }

}