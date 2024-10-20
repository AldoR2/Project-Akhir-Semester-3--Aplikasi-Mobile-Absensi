package com.example.minggu7;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AutoCompleteTextView autoCompleteJenisKelamin = findViewById(R.id.JenisKelamin);

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


        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke halaman sebelumnya
                finish();  // Menutup activity dan kembali ke activity sebelumnya
            }
        });

//        Button btnTanggalLahir = findViewById(R.id.btnTanggalLahir);
//
//        btnTanggalLahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        EditProfile.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
//                                // Tampilkan tanggal yang dipilih pada Button
//                                btnTanggalLahir.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
//                            }
//                        }, year, month, day);
//                datePickerDialog.show();
//            }
//        });

    }
}