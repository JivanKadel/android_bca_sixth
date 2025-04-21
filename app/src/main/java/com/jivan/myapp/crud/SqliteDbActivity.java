package com.jivan.myapp.crud;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.myapp.R;
import com.jivan.myapp.dialog.DeleteCourseDialogFragment;
import com.jivan.myapp.helpers.DBHandler;

import java.util.ArrayList;

public class SqliteDbActivity extends AppCompatActivity {
    EditText etCourseName, etCourseDuration;
    Button btnAddCourse, btnDeleteCourse;
    DBHandler dbHandler;
    ListView lvCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_sqlite_db);

        etCourseName = findViewById(R.id.etCourseName);
        etCourseDuration = findViewById(R.id.etCourseDuration);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnDeleteCourse = findViewById(R.id.btnDeleteCourse);
        lvCourses = findViewById(R.id.lvCourses);

        dbHandler = new DBHandler(getApplicationContext());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadSqliteData();

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etCourseName.getText() != null && etCourseDuration.getText() != null) {
                    try {
                        dbHandler.addNewCourse(etCourseName.getText().toString(), etCourseDuration.getText().toString());
                        etCourseName.getText().clear();
                        etCourseDuration.getText().clear();
                        Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                        loadSqliteData();
                    } catch (Exception e) {
                        Log.d("Error: ", e.toString());
                    }
                }
            }
        });

        btnDeleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the dialog fragment
                DeleteCourseDialogFragment deleteCourseDialog = new DeleteCourseDialogFragment();

                // Show the dialog using the fragment manager
                deleteCourseDialog.show(getSupportFragmentManager(), "DeleteCourseDialog");
            }
        });

    }

    // Load data from Sqlite Database
    private void loadSqliteData() {
        Cursor cursor = dbHandler.getAlCourses();
        ArrayList<String> courseName = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                courseName.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, courseName);
        lvCourses.setAdapter(adapter);
    }
}