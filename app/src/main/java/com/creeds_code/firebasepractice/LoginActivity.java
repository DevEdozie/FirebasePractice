package com.creeds_code.firebasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.creeds_code.firebasepractice.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        //Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        setContentView(binding.getRoot());
        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        binding.regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser() {
        String email = binding.logEmail.getText().toString();
        String password = binding.logPassword.getText().toString();
        //Check if field is empty
        if (TextUtils.isEmpty(email)) {
            binding.logEmail.setError("Email cannot be empty!");
            binding.logEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            binding.logPassword.setError("Password cannot be empty!");
            binding.logPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Registration Successful", Toast.LENGTH_LONG);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_LONG);
                    }
                }
            });
        }
    }
}