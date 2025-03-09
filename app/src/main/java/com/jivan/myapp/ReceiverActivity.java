package com.jivan.myapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReceiverActivity extends AppCompatActivity {

    TextView tvName, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver);

        tvName = findViewById(R.id.textViewName);
        tvPhone = findViewById(R.id.textViewPhone);

        String name = getIntent().getStringExtra("Name");
        String phone = getIntent().getStringExtra("Phone");


        tvName.setText(String.format("Name: %s", name));
        tvPhone.setText(String.format("Phone: %s", phone));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}