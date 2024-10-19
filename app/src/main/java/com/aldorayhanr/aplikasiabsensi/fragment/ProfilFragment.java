package com.aldorayhanr.aplikasiabsensi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.aldorayhanr.aplikasiabsensi.R;
import com.aldorayhanr.aplikasiabsensi.activity.ChangePassword;
import com.aldorayhanr.aplikasiabsensi.activity.Login;
import com.aldorayhanr.aplikasiabsensi.activity.ViewProfile;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfilFragment extends Fragment {

    private Context mContext;
    private SharedPreferences sharedPreferences;
    private Button editprofil, buttonLogout, btnViewProfile;
    private TextView Username, Nim, Prodi, Semester, TVChangePassword;

    public ProfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        buttonLogout = view.findViewById(R.id.btnlogout);
        sharedPreferences = getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Username = view.findViewById(R.id.username);
        Nim = view.findViewById(R.id.nim);
        Prodi = view.findViewById(R.id.prodi);
        Semester = view.findViewById(R.id.semester);
        TVChangePassword = view.findViewById(R.id.change_password);
        editprofil = view.findViewById(R.id.editprofil);
        btnViewProfile = view.findViewById(R.id.view_profile);


        // Mengambil user profile informasi dari api
        String url = "http://192.168.0.10/LoginRegister/login-registration-android/profile.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());

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
                            String prodi = data.getString("program_studi");
                            String semester = data.getString("semester");

                            Username.setText(nama);
                            Nim.setText(nim);
                            Prodi.setText(prodi);
                            Semester.setText(semester);

                            // Load image dari api
//                            byte[] imageBytes = Base64.decode(imageUrl, Base64.DEFAULT);
//                            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                            imageView.setImageBitmap(imageBitmap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (isAdded()) { // Pastikan Fragment terhubung dengan Activity
                    Toast.makeText(getActivity(), "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
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

        TVChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewProfile.class);
                startActivity(intent);
            }
        });


        queue.add(stringRequest);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = "http://192.168.0.10/LoginRegister/login-registration-android/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    // Mengurai respons JSON dari API logout
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String status = jsonResponse.getString("status");
                                    String message = jsonResponse.getString("message");


                                    if (status.equals("success")) {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("logged", "false");
                                        editor.putString("nim", "");
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