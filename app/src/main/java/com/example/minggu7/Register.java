package com.example.minggu7;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private EditText username, email, telNo, dob, password, confirmPassword;
    private DbHelper DB;
    private TextView tvlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register); // pastikan Anda memiliki layout register.xml

//        username = findViewById(R.id.username);
//        email = findViewById(R.id.email);
//        telNo = findViewById(R.id.telNo);
//        dob = findViewById(R.id.dob);
//        password =findViewById(R.id.password);
//        confirmPassword =findViewById(R.id.repeatpassword);
//        tvlogin =findViewById(R.id.txtlogin);
//        Button daftar = findViewById(R.id.btnRegister);

        DB = new DbHelper(this);

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

//        daftar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String usernameTXT = username.getText().toString().trim();
//                String emailTXT = email.getText().toString().trim();
//                String telNoTXT = telNo.getText().toString().trim();
//                String dobTXT = dob.getText().toString().trim();
//                String passwordTXT = password.getText().toString().trim();
//                String repeatpasswordTXT = confirmPassword.getText().toString().trim();
//
//                if (usernameTXT.isEmpty() || emailTXT.isEmpty() || telNoTXT.isEmpty() || dobTXT.isEmpty() || passwordTXT.isEmpty() || repeatpasswordTXT.isEmpty()) {
//                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (!passwordTXT.equals(repeatpasswordTXT)) {
//                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Boolean checkInsertData = DB.insertUserData(usernameTXT, emailTXT, telNoTXT, dobTXT, passwordTXT);
//                if (checkInsertData == true) {
//                    Toast.makeText(Register.this, "New Entry Inserted", Toast.LENGTH_SHORT).show(); // show() eklenmiş
//
//                    Intent lol = new Intent(getApplicationContext(), Login.class);
//                    startActivity(lol);
//                    finish();
//                } else {
//                    Toast.makeText(Register.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show(); // show() eklenmiş
//                }
//            }
//        });
    }

}

