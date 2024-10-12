package com.aldorayhanr.aplikasiabsensi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.aldorayhanr.aplikasiabsensi.R;
import com.aldorayhanr.aplikasiabsensi.activity.Login;
import com.aldorayhanr.aplikasiabsensi.activity.MainActivity;
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

public class ProfilFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private ImageView imageView;
    private Button editprofil, buttonLogout, btnDelete;
    private EditText username, email, telNo, dob;

    public ProfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        buttonLogout = view.findViewById(R.id.btnlogout);
        sharedPreferences = getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        imageView = view.findViewById(R.id.foto_profil);
        username = view.findViewById(R.id.etUsername);
        email = view.findViewById(R.id.etEmail);
        telNo = view.findViewById(R.id.etTelNo);
        dob = view.findViewById(R.id.etDob);
        editprofil = view.findViewById(R.id.editprofil);
        btnDelete = view.findViewById(R.id.btnDelete);

        // Mengambil user profile informasi dari api
        String url = "http://192.168.0.23/LoginRegister/login-registration-android/profile.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String nama = jsonResponse.getString("nama");
                            String imageUrl = jsonResponse.getString("foto");

                            username.setText(nama);

                            // Load image dari api
                            byte[] imageBytes = Base64.decode(imageUrl, Base64.DEFAULT);
                            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                            imageView.setImageBitmap(imageBitmap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getContext(), "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = "http://192.168.0.23/LoginRegister/login-registration-android/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    // Mengurai respons JSON dari API logout
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String status = jsonResponse.getString("status");
                                    String message = jsonResponse.getString("message");
                                    String nim = jsonResponse.getString("nim");
                                    String nama = jsonResponse.getString("nama");


                                    if (status.equals("success")) {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("logged", "false");
                                        editor.putString("nim", "");
                                        editor.putString("nama", "");
                                        editor.apply();

                                        //Redirect ke halaman login
                                        Intent intent = new Intent(getContext(), Login.class);
                                        startActivity(intent);

                                        if (getActivity() != null) {
                                            getActivity().finish();
                                        }
                                    } else {
                                        // Jika respons status bukan success, tampilkan pesan error
                                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getContext(), "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);
            }
        });

        return view;
    }
}