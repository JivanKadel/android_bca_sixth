package com.jivan.myapp.displays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.myapp.R;

public class ListViewActivity extends AppCompatActivity {
    String[] courses = {"BCA", "CSIT", "BBA", "BIM", "BBS", "BTECH", "MSc. CSIT", "MCA", "MBS", "MBA"};

    ListView listView;
    Button btnGotoGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listViewCourses);

        btnGotoGrid = findViewById(R.id.btnGotoGrid);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, courses);
        listView.setAdapter(adapter);

        btnGotoGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GridViewActivity.class);
                startActivity(intent);
            }
        });
    }
}