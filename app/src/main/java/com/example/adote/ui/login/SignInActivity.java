package com.example.adote.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adote.MainActivity;
import com.example.adote.R;
import com.example.adote.helper.UserDBM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener {
    private EditText mEmail, mPassword;
    private Button signinButton;
    private TextView signupButton;
    private FirebaseAuth auth;

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            Toast.makeText(this, "Logado com sucesso", Toast.LENGTH_SHORT).show();

            String uid = auth.getInstance().getCurrentUser().getUid();
            if (UserDBM.getInstance().getUser(uid) == null) {
                Toast.makeText(this, "Cadastro ainda não foi finalizado.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignInActivity.this, GetUserInfoActivity.class));
            } else {
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }

        } else {
            Toast.makeText(this, "Erro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.siginButton:
                signIn(mEmail.getText().toString(), mPassword.getText().toString());
                break;
            case R.id.signupTxtButton:
                Intent i = new Intent(SignInActivity.this, SignupActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mEmail = (EditText) findViewById(R.id.siginEmailEditTxt);
        mPassword = (EditText) findViewById(R.id.siginPasswordEditTxt);
        signinButton = (Button) findViewById(R.id.siginButton);
        signupButton = (TextView) findViewById(R.id.signupTxtButton);

        auth = FirebaseAuth.getInstance();

        signinButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

    /**
     * Tries to sing a user in.
     *
     * @param email User's email.
     * @param password User's password.
     */
    private void signIn(String email, String password) {
        boolean isDataValid = true;

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email obrigatório.");
            isDataValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Senha obrigatória.");
            isDataValid = false;
        }

        if (isDataValid) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this::onComplete);
        }
    }
}