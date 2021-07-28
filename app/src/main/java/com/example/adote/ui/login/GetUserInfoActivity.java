package com.example.adote.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adote.MainActivity;
import com.example.adote.R;
import com.example.adote.models.user.User;
import com.example.adote.helper.UserDBM;
import com.example.adote.models.user.UserInfo;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashSet;

public class GetUserInfoActivity extends AppCompatActivity implements View.OnClickListener, ChipGroup.OnCheckedChangeListener {
    private ChipGroup mUserType;
    private PrivateInfoFragment privUserFragment;
    private InstitutionalInfoFragment instUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);

        privUserFragment = new PrivateInfoFragment();
        instUserFragment = new InstitutionalInfoFragment();

        mUserType = (ChipGroup) findViewById(R.id.userTypeChipGroup);
        Button finish = (Button) findViewById(R.id.signupFinishButton);
        setUserFragment(privUserFragment); // Initializes the user fragment

        mUserType.setOnCheckedChangeListener(this::onCheckedChanged);
        finish.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupFinishButton:
                if (createNewUserInstance()) {
                    startActivity(new Intent(GetUserInfoActivity.this, MainActivity.class));
                }
        }
    }

    @Override
    public void onCheckedChanged(ChipGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.instUserChip:
                setUserFragment(instUserFragment);
                break;
            case R.id.privUserChip:
                setUserFragment(privUserFragment);
                break;
        }
    }

    private void setUserFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.signupUserTypeFrameLayout, fragment);
        transaction.commit();
    }

    /**
     * Creates a new user instance based on the registration fields
     * and adds it to the usr database.
     *
     * @return true in case of success and false otherwise.
     */
    private boolean createNewUserInstance() {
            User user = new User();
            UserInfo info = getUserInfo();

            if (info == null) {
                return false;
            }

            user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
            user.setInfo(info);
            user.setAnimalsAdopting(new HashSet<Integer>());
            user.setAnimalsForAdoption(new HashSet<Integer>());

            UserDBM.getInstance().addNewUser(user);

            return true;
    }

    private UserInfo getUserInfo() {
        UserInfo info;

        switch (mUserType.getCheckedChipId()) {
            case R.id.instUserChip:
                info = instUserFragment.getNewInstUserInfo();
                break;
            case R.id.privUserChip:
                info = privUserFragment.getNewPrivateUserInfo();
                break;
            default:
                info = null;
                break;
        }

        return info;
    }

}