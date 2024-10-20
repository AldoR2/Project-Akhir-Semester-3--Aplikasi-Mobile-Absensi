package com.example.minggu7;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private EditText username, email, telNo, dob, password, confirmPassword;
    private DbHelper DB;
    private TextView tvLogin;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register); // pastikan Anda memiliki layout register.xml

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);  // Menghilangkan warna status bar
        }

        tvLogin = findViewById(R.id.tvLogin);
        btnSubmit = findViewById(R.id.btnSubmit);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        username = findViewById(R.id.username);
//        email = findViewById(R.id.email);
//        telNo = findViewById(R.id.telNo);
//        dob = findViewById(R.id.dob);
//        password =findViewById(R.id.password);
//        confirmPassword =findViewById(R.id.repeatpassword);
//        tvlogin =findViewById(R.id.tvLogin);
//        Button daftar = findViewById(R.id.btnRegister);

//        DB = new DbHelper(this);

//        tvlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

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
        autoCompleteProdi.setDropDownWidth(LinearLayout.LayoutParams.WRAP_CONTENT);

// Menampilkan dropdown saat AutoCompleteTextView pertama diklik
        autoCompleteProdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteProdi.showDropDown();
            }
        });
        
        AutoCompleteTextView autoCompleteJenisKelamin = findViewById(R.id.jeniskelamin);

        String[] kategoriItems = {"Laki-laki", "Perempuan"};

        ArrayAdapter<String> kategoriAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, kategoriItems);

        autoCompleteJenisKelamin.setAdapter(kategoriAdapter);

        autoCompleteJenisKelamin.setDropDownWidth(LinearLayout.LayoutParams.WRAP_CONTENT);

        autoCompleteJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteJenisKelamin.showDropDown();
            }
        });


    }

}

