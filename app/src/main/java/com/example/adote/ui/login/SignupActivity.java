package com.example.adote.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener {
    EditText mEmail, mPassword, mConfirmPassword;
    Button nextStepButton;
    FirebaseAuth auth;

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            Intent i = new Intent(SignupActivity.this, GetUserInfoActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Erro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();

        boolean isDataValid = true;
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email obrigatório.");
            isDataValid = false;
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Senha obrigatória.");
            isDataValid = false;
        }  else if (password.length() < 8) {
            mPassword.setError("Senha deve conter no mínimo 8 caracteres.");
            isDataValid = false;
        } else if (TextUtils.isEmpty(confirmPassword) ||!password.equals(confirmPassword)) {
            mConfirmPassword.setError("Senhas não batem.");
            isDataValid = false;
        }

        if (isDataValid) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this::onComplete);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nextStepButton = (Button) findViewById(R.id.signupInfoFormButton);
        mEmail = (EditText) findViewById(R.id.signupEmailEditTxt);
        mPassword = (EditText) findViewById(R.id.signupPasswordEditTxt);
        mConfirmPassword = (EditText) findViewById(R.id.signupConfirmPasswordEditTxt);
        auth = FirebaseAuth.getInstance();

        nextStepButton.setOnClickListener(this::onClick);
    }
}