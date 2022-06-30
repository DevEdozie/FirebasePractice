package com.creeds_code.firebasepractice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.creeds_code.firebasepractice.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Initialize
        mAuth = FirebaseAuth.getInstance();
        binding.regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        binding.loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createUser() {
        String email = binding.regEmail.getText().toString();
        String password = binding.regPassword.getText().toString();
        //Check if field is empty
        if (TextUtils.isEmpty(email)) {
            binding.regEmail.setError("Email cannot be empty!");
            binding.regEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            binding.regPassword.setError("Password cannot be empty!");
            binding.regPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed " + task.getException().getMessage(), Toast.LENGTH_LONG);
                    }
                }
            });
        }
    }

}