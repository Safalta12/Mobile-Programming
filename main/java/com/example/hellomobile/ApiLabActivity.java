package com.example.hellomobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiLabActivity extends AppCompatActivity {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private UserAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_lab);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewApiLab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);

        fetchUsers();
    }

    private void fetchUsers() {
        RequestQueue requestQueue = MyApp.getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject userObject = response.getJSONObject(i);
                            int id = userObject.getInt("id");
                            String name = userObject.getString("name");
                            String email = userObject.getString("email");

                            userList.add(new User(id, name, email));
                        } catch (JSONException e) {
                            Log.e("API_LAB", "JSON parsing error", e);
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> {
                    Log.e("API_LAB_ERROR", error.toString());
                    Toast.makeText(ApiLabActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}