package com.aldorayhanr.aplikasiabsensi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText ETNim, ETNama, ETPassword, ETConfirm_Password, ETProgram_Studi, ETSemester, ETEmail, ETNo_Telp;
    private TextView tvLogin;
    private Button buttonSubmit;
    private String nim, nama, password, programstudi, semester, email, no_telp, selectedjenis_kelamin;
    private AutoCompleteTextView TVJenis_kelamin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);  // Menghilangkan warna status bar
        }


        ETNim = findViewById(R.id.nim);
        ETNama = findViewById(R.id.nama);
        ETPassword = findViewById(R.id.password);
        ETConfirm_Password = findViewById(R.id.confirmpassword);
        ETProgram_Studi = findViewById(R.id.prodi);
        ETSemester = findViewById(R.id.semester);
        ETEmail = findViewById(R.id.email);
        ETNo_Telp = findViewById(R.id.no_telp);
        tvLogin = findViewById(R.id.tvLogin);
        buttonSubmit = findViewById(R.id.submit);
        TVJenis_kelamin = findViewById(R.id.jenis_kelamin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
                nim = String.valueOf(ETNim.getText());
                nama = String.valueOf(ETNama.getText());
                String password = ETPassword.getText().toString().trim(); // Use a local variable for password
                String confirmation = ETConfirm_Password.getText().toString().trim(); // Get confirmation password
                programstudi = String.valueOf(ETProgram_Studi.getText());
                semester = String.valueOf(ETSemester.getText());
                email = String.valueOf(ETEmail.getText());
                no_telp = String.valueOf(ETNo_Telp.getText());
                selectedjenis_kelamin = String.valueOf(TVJenis_kelamin.getText());

                // Validate password confirmation
                if (!password.equals(confirmation)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method early
                }

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.0.10/LoginRegister/login-registration-android/register.php";


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Registrations Sucessfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("nim", nim);
                        paramV.put("nama", nama);
                        paramV.put("password", password);
                        paramV.put("program_studi", programstudi);
                        paramV.put("semester", semester);
                        paramV.put("jenis_kelamin", selectedjenis_kelamin);
                        paramV.put("email", email);
                        paramV.put("no_telp", no_telp);
                        return paramV;
                    }
                };

                queue.add(stringRequest);
            }
        });

        // AutoCompleteTextView pertama
        AutoCompleteTextView autoCompleteProdi = findViewById(R.id.prodi);

        // Daftar item untuk dropdown pertama (Program Studi)
        String[] prodiItems = {"Teknologi Informasi", "Peternakan", "Pertanian", "Teknik"};

        // Membuat adapter untuk dropdown pertama
        ArrayAdapter<String> prodiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, prodiItems);

        // Mengatur adapter ke AutoCompleteTextView pertama
        autoCompleteProdi.setAdapter(prodiAdapter);

        // Mengatur offset vertikal dan lebar untuk dropdown pertama
        autoCompleteProdi.setDropDownVerticalOffset(0);
        autoCompleteProdi.setDropDownWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        // Menampilkan dropdown saat AutoCompleteTextView pertama diklik
        autoCompleteProdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteProdi.showDropDown();
            }

        });

        AutoCompleteTextView autoCompleteJenisKelamin = findViewById(R.id.jenis_kelamin);

        String[] kategoriItems = {"Laki-laki", "Perempuan"};

        ArrayAdapter<String> kategoriAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, kategoriItems);

        autoCompleteJenisKelamin.setAdapter(kategoriAdapter);

        autoCompleteJenisKelamin.setDropDownVerticalOffset(0);
        autoCompleteJenisKelamin.setDropDownWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        autoCompleteJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteJenisKelamin.showDropDown();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Kembali ke aktivitas sebelumnya (Login)
    }
}

