package com.jivan.myapp.fetch;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jivan.myapp.R;
import com.jivan.myapp.models.Course;
import com.jivan.myapp.models.Student;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParseActivity extends AppCompatActivity {

    TextView tvNoData;
    Button btnFetchJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_parse);

        tvNoData = findViewById(R.id.tvNoData);
        btnFetchJson = findViewById(R.id.btnFetchJson);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var padding = findViewById(R.id.main).getPaddingTop();
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
        Log.i("JOHN DESERIALIZED", john.toString());


        btnFetchJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://jsonplaceholder.typicode.com/users/1";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest objectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSON Response", response.toString());
                        try {
                            int userId = response.getInt("id");
                            String name = response.getString("name");
                            String userName = response.getString("username");
                            String email = response.getString("email");

                            Log.i("ID", String.valueOf(userId));
                            Log.i("Name", name);
                            Log.i("User Name", userName);
                            Log.i("Email", email);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error fetching data: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(objectRequest);
                queue.start();
            }
        });
    }
}