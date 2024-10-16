package com.aldorayhanr.aplikasiabsensi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aldorayhanr.aplikasiabsensi.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView textViewRegisterNow;
    TextInputEditText textInputEditTextNimNip, textInputEditTextPassword;
    Button buttonSubmit;
    String nipNim, nama, password;
    TextView textViewError;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewRegisterNow = findViewById(R.id.registerNow);
        textInputEditTextNimNip = findViewById(R.id.nim_nip);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonSubmit = findViewById(R.id.submit);
        textViewError = findViewById(R.id.error);
        progressBar = findViewById(R.id.loading);
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

//        Cek apakah sudah login
        if(sharedPreferences.getString("logged", "false").equals("true")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                nipNim = textInputEditTextNimNip.getText().toString().trim();
                password = textInputEditTextPassword.getText().toString().trim();

                if (nipNim.isEmpty() || nipNim.length() < 8) {
                    textViewError.setText("Masukkan NIP atau NIm yang valid");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (password.isEmpty()) {
                    textViewError.setText("Masukkan password");
                    textViewError.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                //Menentukan role berdasarkan panjang input username
                String role = nipNim.length() >= 10 ? "dosen" : "mahasiswa";

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

//              Mengecek Login sesuai role
                cekLogin(queue, role);
            }
        });

        textViewRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();
            }
        });
    }

                private void cekLogin(RequestQueue queue, String role) {

                String url ="http://192.168.18.12/LoginRegister/login-registration-android/login.php";

                StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                Log.d("RawResponse", response); // Menampilkan respons mentah di Logcat

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String message = jsonObject.getString("message");

                                    if (status.equals("success")){
//                                        Login Berhasil
                                        if(role.equals("dosen")) {
                                            nipNim = jsonObject.getString("nip");
                                        } else {
                                            nipNim = jsonObject.getString("nim");
                                        }
                                        nama = jsonObject.getString("nama");


//                                        Simpan Data Login di SharedPreferences
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("logged", "true");
                                        if (role.equals("dosen")) {
                                            editor.putString("nip", nipNim);
                                        } else {
                                            editor.putString("nim", nipNim);
                                        }
                                        editor.putString("nama", nama);
                                        editor.apply();

//                                        Maka Pindah ke MainActivity
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
//                                        Jika Tetap gagal maka akan menampilkan pesan error
                                        textViewError.setText(message);
                                        textViewError.setVisibility(View.VISIBLE);
                                    }

                                } catch (JSONException e) {
                                    textViewError.setText("Error in parsing response. Please try again.");
                                    textViewError.setVisibility(View.VISIBLE);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        textViewError.setText("Error: " + error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        if (role.equals("dosen")) {
                            paramV.put("nip", nipNim);  // Kirim NIP untuk dosen
                        } else {
                            paramV.put("nim", nipNim);  // Kirim NIM untuk mahasiswa
                        }

                        paramV.put("password", password);
                        paramV.put("role", role);
                        return paramV;
                    }
                };
                queue.add(loginRequest);
            }


}