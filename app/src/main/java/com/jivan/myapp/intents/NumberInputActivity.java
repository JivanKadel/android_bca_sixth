package com.jivan.myapp.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.myapp.R;

public class NumberInputActivity extends AppCompatActivity {

    EditText etNumberInput;
    Button btnSubmitNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number_input);

        etNumberInput = findViewById(R.id.etNumberInput);
        btnSubmitNumber = findViewById(R.id.btnSubmitNumber);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnSubmitNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num;
                try{
                    num = Integer.parseInt(etNumberInput.getText().toString());
                    if(num<0 || num>100){
                        Toast.makeText(getApplicationContext(), "Number must be between 0 and 100", Toast.LENGTH_LONG).show();
                        etNumberInput.setText("");
                        return;
                    }
                    Intent intent = new Intent(getApplicationContext(), NumberDisplayActivity.class);
                    intent.putExtra("number", num);
                    startActivity(intent);
                }catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(), "The number is not correct fam!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}