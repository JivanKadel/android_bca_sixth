package com.jivan.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReceiverActivity extends AppCompatActivity {

    Button btnSubmitBack, btnShowDialog;
    EditText etAddress;
    TextView tvName, tvPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver);

        tvName = findViewById(R.id.textViewName);
        tvPhone = findViewById(R.id.textViewPhone);

        etAddress = findViewById(R.id.editTextAddress);
        btnSubmitBack = findViewById(R.id.btnSubmitBack);
        btnShowDialog = findViewById(R.id.btnShowDialogFragment);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");


        tvName.setText(String.format("Name: %s", name));
        tvPhone.setText(String.format("Phone: %s", phone));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnSubmitBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etAddress != null && etAddress.getText() != null) {
                    String address = etAddress.getText().toString();

                    Intent intent = new Intent();
                    intent.putExtra("address", address);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(), "MyDialog");
            }
        });
    }
}