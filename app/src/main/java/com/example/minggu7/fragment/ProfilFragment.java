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
import com.example.minggu7.LoginActivity;
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
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        return view;
    }
}

