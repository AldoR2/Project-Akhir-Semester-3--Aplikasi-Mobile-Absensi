package com.aldorayhanr.aplikasiabsensi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aldorayhanr.aplikasiabsensi.R;

public class ForgotPassword extends AppCompatActivity {

    private TextView tvLogin;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);

        tvLogin = findViewById(R.id.tvLogin);
        btnNext = findViewById(R.id.btnNext);

        if (tvLogin == null) {
            Log.e("ForgotPasswordActivity", "TextView tvLogin not found");
        }

        if (btnNext == null) {
            Log.e("ForgotPasswordActivity", "Button btnNext not found");
        }

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Otp.class);
                startActivity(intent);
            }
        });


    }
}
