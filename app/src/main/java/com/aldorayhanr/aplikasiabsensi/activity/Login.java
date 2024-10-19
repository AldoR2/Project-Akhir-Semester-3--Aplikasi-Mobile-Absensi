package com.aldorayhanr.aplikasiabsensi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView tvRegister, tvForgot;
    private EditText ETUsername, ETPassword;
    private Button buttonSubmit;
    private String nipNim, nama, password;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);  // Menghilangkan warna status bar
        }

        tvRegister = findViewById(R.id.register);
        tvForgot = findViewById(R.id.forgot);
        ETUsername = findViewById(R.id.username);
        ETPassword = findViewById(R.id.password);
        buttonSubmit = findViewById(R.id.submit);
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        // Cek apakah sudah login
        if(sharedPreferences.getString("logged", "false").equals("true")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nipNim = ETUsername.getText().toString().trim();
                password = ETPassword.getText().toString().trim();

                if (nipNim.isEmpty() || nipNim.length() < 8) {
                    Toast.makeText(Login.this, "NIP/NIM harus memiliki panjang minimal 8 karakter", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty() || password.length() < 8) {
                    Toast.makeText(Login.this, "Password harus memiliki panjang minimal 8 karakter", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Menentukan role berdasarkan panjang input username
                String role = nipNim.length() >= 10 ? "dosen" : "mahasiswa";

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

//              Mengecek Login sesuai role
                cekLogin(queue, role);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

                private void cekLogin(RequestQueue queue, String role) {

                String url ="http://192.168.0.10/LoginRegister/login-registration-android/login.php";

                StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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

                                    }

                                } catch (JSONException e) {

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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