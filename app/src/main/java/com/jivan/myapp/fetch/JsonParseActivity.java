package com.jivan.myapp.fetch;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.jivan.myapp.R;
import com.jivan.myapp.models.Course;
import com.jivan.myapp.models.Student;

public class JsonParseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_parse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var padding =  findViewById(R.id.main).getPaddingTop();
            v.setPadding(padding, systemBars.top, padding, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        Course bca = new Course("BCA", "IT in humanities, Worst of both world", 5);
        Student jivan = new Student(10, "Jivan", "Kadel", "Moon", bca);
        Gson gson = new Gson();
        String studentJson = gson.toJson(jivan);
        Log.i("STUDENT JSON", studentJson);

        String johnJson = "{\"address\":\"Moon\",\"course\":{\"desc\":\"IT in humanities, Worst of both world\",\"duration\":5,\"name\":\"BCA\"},\"firstName\":\"Jivan\",\"lastName\":\"Kadel\",\"rollNo\": 10}";
        Student john = gson.fromJson(johnJson, Student.class);
        Log.i("JOHN DESERIALIZED" , john.toString());
    }
}