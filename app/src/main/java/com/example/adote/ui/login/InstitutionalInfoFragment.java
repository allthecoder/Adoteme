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
import com.example.adote.models.user.InstitutionalUserInfo;

public class InstitutionalInfoFragment extends Fragment {
    private EditText mName, mPhone, mAddress, mSite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_inst_info, container, false);
        initializeViews(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Initializes all children views of root.
     *
     * @param root Root View
     */
    private void initializeViews(View root) {
        mName = (EditText) root.findViewById(R.id.instNameEditTxt);
        mPhone = (EditText) root.findViewById(R.id.instPhoneTxt);
        mAddress = (EditText) root.findViewById(R.id.instAddressEditTxt);
        mSite = (EditText) root.findViewById(R.id.instSiteEditTxt);
    }

    public InstitutionalUserInfo getNewInstUserInfo() {
        String name = mName.getText().toString();
        String contact = mPhone.getText().toString();
        String address = mAddress.getText().toString();
        String site = mSite.getText().toString();

        boolean isDataValid = true;
        if (TextUtils.isEmpty(name)) {
            mName.setError("Nome obrigatório.");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(contact)) {
            mPhone.setError("Telefone obrigatório.");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(address)) {
            mAddress.setError("Endereço obrigatório.");
            isDataValid = false;
        }

        if (!isDataValid) {
            return null;
        }

        return new InstitutionalUserInfo(name,contact, address, site);
    }

}