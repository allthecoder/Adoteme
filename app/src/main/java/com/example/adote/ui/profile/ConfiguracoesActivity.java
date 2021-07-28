package com.example.adote.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adote.R;
import com.example.adote.models.user.UserType;
import com.example.adote.helper.DBMHelper;

public class ConfiguracoesActivity extends AppCompatActivity {
    TextView mName, mContact, mAddress;
    ImageButton mChangeName, mChangeContact, mChangeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Configurações");


        initializeViews();
        showUserInfo();
        initializeUserExtraInfo();
    }

    private void initializeViews() {
        mName = findViewById(R.id.userNameTextView);
        mContact = findViewById(R.id.userContactTextView);
        mAddress = findViewById(R.id.userAddressTextView);

        mChangeName = findViewById(R.id.changeUserNameButton);
        mChangeContact = findViewById(R.id.changeUserContactButton);
        mChangeAddress = findViewById(R.id.changeUserAddressButton);
    }

    private void initializeUserExtraInfo() {
        UserType type = DBMHelper.getCurUserType();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (type.equals(UserType.INSTITUTIONAL)) {
            transaction.replace(R.id.userExtraInfo, new InstProfileFragment());
        } else {
            transaction.replace(R.id.userExtraInfo, new PrivProfileFragment());
        }
        transaction.commit();
    }

    private void showUserInfo() {
        mName.setText(DBMHelper.getCurUserName());
        mContact.setText(DBMHelper.getCurUserContact());
        mAddress.setText(DBMHelper.getCurUserContact());
    }
}