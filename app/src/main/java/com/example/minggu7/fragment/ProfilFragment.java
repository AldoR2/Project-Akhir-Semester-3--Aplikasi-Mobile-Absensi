package com.example.minggu7.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;
import com.example.minggu7.DbHelper;
import com.example.minggu7.EditProfile;
import com.example.minggu7.LoginActivity;
import com.example.minggu7.R;
import com.example.minggu7.ViewAllSchedule;
import com.example.minggu7.ViewProfile;

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

        // Initialize the Button
        Button edit_profile_button = view.findViewById(R.id.edit_profile_button);
        Button view_profile_button = view.findViewById(R.id.view_profile_button);
        Button logoutButton = view.findViewById(R.id.logout_button);


        edit_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });

        view_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), ViewProfile.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan dialog konfirmasi
                new AlertDialog.Builder(requireContext())
                        .setMessage("Yakin untuk logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Jika klik "Yes", arahkan ke LoginActivity
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Menghapus stack aktivitas
                                startActivity(intent);
                                requireActivity().finish(); // Menutup MainActivity
                            }
                        })
                        .setNegativeButton("No", null) // Jika klik "No", tidak ada aksi
                        .show();
            }
        });

        return view;
    }
}

