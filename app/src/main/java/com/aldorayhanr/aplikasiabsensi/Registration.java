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

    TextInputEditText textInputEditTextNim, textInputEditTextNama, textInputEditTextPassword;
    Button buttonSubmit;
    String nim, nama, password, selectedProgramStudi, selectedGolongan, selectedKelas;
    TextView textViewError;
    ProgressBar progressBar;
    Spinner spinnerprogram_studi, spinnergolongan, spinnerkelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textInputEditTextNim = findViewById(R.id.nim);
        textInputEditTextNama = findViewById(R.id.nama);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonSubmit = findViewById(R.id.submit);
        textViewError = findViewById(R.id.error);
        progressBar = findViewById(R.id.loading);
        spinnerprogram_studi = findViewById(R.id.prodi_spinner);
        spinnergolongan = findViewById(R.id.golongan_spinner);
        spinnerkelas = findViewById(R.id.kelas_spinner);

        getDataKelas();

    }

//    >>>>>>KODE UNTUK SPINNER KODE_KELAS<<<<<<

    private void getDataKelas() {
        String url ="http://192.168.18.12/LoginRegister/login-registration-android/register.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            final List<String> kelasList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                kelasList.add(obj.getString("kode_kelas"));
                            }

                            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(Registration.this,
                                    android.R.layout.simple_spinner_item, kelasList);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerkelas.setAdapter(spinnerAdapter);

                            spinnerkelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    selectedKelas = kelasList.get(position);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // Tidak ada item yang dipilih
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Registration.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE); // Ubah jadi GONE, bukan VISIBLE
                textViewError.setText(error.getLocalizedMessage());
                textViewError.setVisibility(View.VISIBLE);
            }
        });
        queue.add(stringRequest);

//        >>>>>>END KODE UNTUK SPINNER KODE_KELAS<<<<<<


        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.prodi_spinner,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spinnerprogram_studi.setAdapter(adapter);

        spinnerprogram_studi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view,
        int pos, long id) {
            // An item is selected. You can retrieve the selected item using
            selectedProgramStudi = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback.
        }
        });

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                    this,
                    R.array.golongan_spinner,
                    android.R.layout.simple_spinner_item
            );
// Specify the layout to use when the list of choices appears.
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spinnergolongan.setAdapter(adapter2);

                spinnergolongan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        // An item is selected. You can retrieve the selected item using
                        selectedGolongan = parent.getItemAtPosition(pos).toString();
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
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.18.12/LoginRegister/login-registration-android/register.php";


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.equals("success")){
                                    Toast.makeText(getApplicationContext(), "Registrations Sucessfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
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
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("nim", nim);
                        paramV.put("nama", nama);
                        paramV.put("password", password);
                        paramV.put("program_studi", selectedProgramStudi);
                        paramV.put("golongan", selectedGolongan);
                        paramV.put("kode_kelas", selectedKelas);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });




    }
}