package com.aldorayhanr.aplikasiabsensi.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aldorayhanr.aplikasiabsensi.R;
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

public class ViewProfile extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView TVUsername, TVNim, TVDob, TVNo_Telp, TVEmail, TVJenis_Kelamin,  TVProdi, TVSemester, TVChange_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        TVUsername = findViewById(R.id.nama);
        TVNim = findViewById(R.id.nim);
        TVDob = findViewById(R.id.dob);
        TVNo_Telp = findViewById(R.id.no_telp);
        TVEmail = findViewById(R.id.email);
        TVJenis_Kelamin = findViewById(R.id.jenis_kelamin);
        TVProdi = findViewById(R.id.prodi);
        TVSemester = findViewById(R.id.semester);

        // Mengambil user profile informasi dari api
        String url = "http://192.168.0.10/LoginRegister/login-registration-android/profile.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Ambil data dari database dan tampilkan
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("API Response", response);

                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject data = jsonResponse.getJSONObject("data");

                            String nama = data.getString("nama");
                            String nim = data.getString("nim");
                            String no_telp = data.getString("no_telp");
                            String email = data.getString("email");
                            String jenis_kelamin = data.getString("jenis_kelamin");
                            String prodi = data.getString("program_studi");
                            String semester = data.getString("semester");

                            TVUsername.setText(nama);
                            TVNim.setText(nim);
                            TVNo_Telp.setText(no_telp);
                            TVEmail.setText(email);
                            TVJenis_Kelamin.setText(jenis_kelamin);
                            TVProdi.setText(prodi);
                            TVSemester.setText(semester);

                            // Load image dari api
//                            byte[] imageBytes = Base64.decode(imageUrl, Base64.DEFAULT);
//                            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                            imageView.setImageBitmap(imageBitmap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Tambahkan parameter sesuai kebutuhan
                Map<String, String> params = new HashMap<>();
                String nim = sharedPreferences.getString("nim", "");
                Log.d("NIM Sent", nim); // Log untuk memverifikasi NIM yang dikirim
                params.put("nim", nim);
                return params;
            }
        };
        queue.add(stringRequest);
                    }
                }

