package com.example.minggu7.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.minggu7.DbHelper;
import com.example.minggu7.Login;
import com.example.minggu7.R;

public class ProfilFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private EditText username, email, telNo, dob;
    private Button editprofil, btnDelete;
    private DbHelper DB;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        // Inisialisasi EditText
        username = view.findViewById(R.id.etUsername);
        email = view.findViewById(R.id.etEmail);
        telNo = view.findViewById(R.id.etTelNo);
        dob = view.findViewById(R.id.etDob);
        editprofil = view.findViewById(R.id.editprofil);
        btnDelete = view.findViewById(R.id.btnDelete);


        // Inisialisasi database helper
        DB = new DbHelper(getContext());

        // Ambil data dari database dan tampilkan
        loadDataFromDatabase();


        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfil.class);
                startActivity(intent);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString().trim();

                Boolean checkDeleteData = DB.deleteUserData(usernameTXT);
                if (checkDeleteData) {
                    Toast.makeText(getActivity(), "Akun berhasil dihapus", Toast.LENGTH_SHORT).show(); // show() eklenmiş

                    Intent intent = new Intent(getActivity(), Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    private void loadDataFromDatabase() {
        Cursor res = DB.getData();  // Dapatkan data dari database

        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "Tidak ada data pengguna", Toast.LENGTH_SHORT).show();
            return;
        }

        // Menampilkan data di EditText
        if (res.moveToFirst()) {
            do {
                // Asumsikan Anda mendapatkan data dari baris pertama
                username.setText(res.getString(res.getColumnIndexOrThrow("username")));
                email.setText(res.getString(res.getColumnIndexOrThrow("email")));
                telNo.setText(res.getString(res.getColumnIndexOrThrow("telNo")));
                dob.setText(res.getString(res.getColumnIndexOrThrow("dob")));
            } while (res.moveToNext());
        }
        res.close();
    }
}

