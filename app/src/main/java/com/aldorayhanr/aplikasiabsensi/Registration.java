package com.aldorayhanr.aplikasiabsensi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration extends AppCompatActivity {

    TextInputEditText textInputEditTextNim, textInputEditTextNama, textInputEditTextPassword, textInputEditTextProgram_Studi, textInputEditTextSemester, textInputEditTextEmail, textInputEditTextNo_Telp;
    Button buttonSubmit;
    String nim, nama, password, programstudi, semester, email, no_telp, selectedjenis_kelamin;
    TextView textViewError;
    ProgressBar progressBar;
    Spinner spinnerjenis_kelamin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textInputEditTextNim = findViewById(R.id.nim);
        textInputEditTextNama = findViewById(R.id.nama);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextProgram_Studi = findViewById(R.id.prodi);
        textInputEditTextSemester = findViewById(R.id.semester);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextNo_Telp = findViewById(R.id.no_telp);
        buttonSubmit = findViewById(R.id.submit);
        textViewError = findViewById(R.id.error);
        progressBar = findViewById(R.id.loading);
        spinnerjenis_kelamin = findViewById(R.id.jenis_kelamin);


        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerjenis_kelamin,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spinnerjenis_kelamin.setAdapter(adapter);

        spinnerjenis_kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item is selected. You can retrieve the selected item using
                selectedjenis_kelamin = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback.
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                nim = String.valueOf(textInputEditTextNim.getText());
                nama = String.valueOf(textInputEditTextNama.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                programstudi = String.valueOf(textInputEditTextProgram_Studi.getText());
                semester = String.valueOf(textInputEditTextSemester.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                no_telp = String.valueOf(textInputEditTextNo_Telp.getText());
                selectedjenis_kelamin = String.valueOf(spinnerjenis_kelamin.getSelectedItem());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.18.12/LoginRegister/login-registration-android/register.php";


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Registrations Sucessfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    textViewError.setText(response);
                                    textViewError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.VISIBLE);
                        textViewError.setText(error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);
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
    }
}

