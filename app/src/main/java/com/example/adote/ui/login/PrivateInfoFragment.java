package com.example.adote.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.adote.R;
import com.example.adote.models.user.PrivateUserInfo;

public class PrivateInfoFragment extends Fragment {
    private EditText mName, mPhone, mAddress, mBirthday, mNickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_priv_info, container, false);
        initializeViews(root);

        return root;
    }

    /**
     * Initializes all children views of root.
     *
     * @param root Root View.
     */
    private void initializeViews(View root) {
        mName = (EditText) root.findViewById(R.id.privNameEditTxt);
        mPhone = (EditText) root.findViewById(R.id.privPhoneEditTxt);
        mAddress = (EditText) root.findViewById(R.id.privAddressEditTxt);
        mBirthday = (EditText) root.findViewById(R.id.privBirthdayEditTxt);
        mNickname = (EditText) root.findViewById(R.id.privNicknameTxt);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public PrivateUserInfo getNewPrivateUserInfo() {
        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String address = mAddress.getText().toString();
        String birthday = mBirthday.getText().toString();
        String nickName = mNickname.getText().toString();

        boolean isDataValid = true;
        if (TextUtils.isEmpty(name)) {
            mName.setError("Nome obrigatório.");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(phone)) {
            mPhone.setError("Telefone obrigatório.");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(address)) {
            mAddress.setError("Endereço obrigatório.");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(birthday)) {
            mBirthday.setError("Data de nascimento obrigatória");
            isDataValid = false;
        }

        if (!isDataValid) {
            return null;
        }

        return new PrivateUserInfo(name, phone, address, birthday, nickName);
    }
}