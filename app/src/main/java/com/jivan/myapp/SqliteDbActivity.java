package com.jivan.myapp;

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

import com.jivan.myapp.helpers.DBHandler;

public class SqliteDbActivity extends AppCompatActivity {
    EditText etCourseName, etCourseDuration;
    Button btnAddCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_sqlite_db);

        etCourseName = findViewById(R.id.etCourseName);
        etCourseDuration = findViewById(R.id.etCourseDuration);
        btnAddCourse = findViewById(R.id.btnAddCourse);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCourseName.getText() != null && etCourseDuration.getText() != null) {
                    try (DBHandler dbHandler = new DBHandler(getApplicationContext())) {
                        dbHandler.addNewCourse(etCourseName.getText().toString(), etCourseDuration.getText().toString());
                        etCourseName.getText().clear();
                        etCourseDuration.getText().clear();
                        Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}