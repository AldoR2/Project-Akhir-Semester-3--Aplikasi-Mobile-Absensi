package com.aldorayhanr.aplikasiabsensi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textViewName, textViewNim, textViewFetchResult;
    SharedPreferences sharedPreferences;
    Button buttonLogout, buttonFetchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewName = findViewById(R.id.nama);
        textViewNim = findViewById(R.id.nim);
        textViewFetchResult = findViewById(R.id.fetchResult);
        buttonLogout = findViewById(R.id.logout);
        buttonFetchUser = findViewById(R.id.fetchProfile);

        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);

        if (sharedPreferences.getString("logged", "false").equals("false")) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.18.12/LoginRegister/login-registration-android/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    // Mengurai respons JSON dari API logout
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String status = jsonResponse.getString("status");
                                    String message = jsonResponse.getString("message");

                                if(status.equals("success")) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("logged", "false");
                                    editor.putString("nim", "");
                                    editor.putString("nama", "");
                                    editor.apply();

//                                    Redirect ke halaman login
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika respons status bukan success, tampilkan pesan error
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this, "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("nim", sharedPreferences.getString("nim", ""));
                        paramV.put("nama", sharedPreferences.getString("nama", ""));
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });


    }
}