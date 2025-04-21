package com.jivan.myapp.fragments;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jivan.myapp.R;

public class AuthenticationActivity extends AppCompatActivity {

    Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentication);

        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // login, signup btn click listener
        btnLogin.setOnClickListener(view -> {
            replaceFragment(new LoginFragment());
        });

        btnSignup.setOnClickListener(view -> {
            replaceFragment(new SignupFragment());
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.auth_frame, fragment);
        fragmentTransaction.commit();
    }

}