package com.jivan.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    Button homeButton;

    public void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get intent data
        boolean IS_LOGGED_IN = getIntent().getBooleanExtra("IS_LOGGED_IN", false);
        Toast.makeText(getApplicationContext(), IS_LOGGED_IN ? "Logged in" : "Not Logged In", Toast.LENGTH_LONG).show();

        homeButton = findViewById(R.id.btnSubmit);
    }

    @Override
    protected void onStart() {
        super.onStart();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
