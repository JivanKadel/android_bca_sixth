package com.jivan.myapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class NumberDisplayActivity extends AppCompatActivity {
    GridView gridViewNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number_display);

        gridViewNumber = findViewById(R.id.gridViewNumber);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        int num = getIntent().getIntExtra("number", 10);
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            nums.add(num + i);
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, nums);
        gridViewNumber.setAdapter(arrayAdapter);
        gridViewNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) != null) {
                    String selected = adapterView.getItemAtPosition(i).toString();
//                    Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_LONG).show();
                    MyDialogFragment myDialogFragment = new MyDialogFragment("Number", selected, "OK");
                    myDialogFragment.show(getSupportFragmentManager(), "MyDialog");
                }
            }
        });
    }
}