package com.example.minggu7.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minggu7.CekMateriActivity;
import com.example.minggu7.KalenderActivity;
import com.example.minggu7.KehadiranActivity;
import com.example.minggu7.LoginActivity;
import com.example.minggu7.NotifikasiActivity;
import com.example.minggu7.R;
import com.example.minggu7.Register;
import com.example.minggu7.ViewAllSchedule;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BerandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerandaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BerandaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BerandaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BerandaFragment newInstance(String param1, String param2) {
        BerandaFragment fragment = new BerandaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        // Initialize the TextView
        TextView jadwal1 = view.findViewById(R.id.jadwal1);
        TextView jadwal2 = view.findViewById(R.id.jadwal2);
        TextView jadwal3 = view.findViewById(R.id.jadwal3);
        TextView allschedule = view.findViewById(R.id.allschedule);
        ImageView notifikasi = view.findViewById(R.id.notifikasi);
        ImageView kehadiran = view.findViewById(R.id.kehadiran);
        ImageView kalender = view.findViewById(R.id.kalender);
        ImageView materi = view.findViewById(R.id.materi);

        // Set OnClickListener on the TextView
        jadwal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the custom layout for the dialog
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View detailView = inflater.inflate(R.layout.activity_detail_jadwal, null);

                // Build the AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(detailView)
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Close the dialog
                                dialog.dismiss();
                            }
                        });

                // Show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        jadwal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the custom layout for the dialog
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View detailView = inflater.inflate(R.layout.activity_detail_jadwal, null);

                // Build the AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(detailView)
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Close the dialog
                                dialog.dismiss();
                            }
                        });

                // Show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        jadwal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the custom layout for the dialog
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View detailView = inflater.inflate(R.layout.activity_detail_jadwal, null);

                // Build the AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(detailView)
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Close the dialog
                                dialog.dismiss();
                            }
                        });

                // Show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        allschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), ViewAllSchedule.class);
                startActivity(intent);
            }
        });

        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), NotifikasiActivity.class);
                startActivity(intent);
            }
        });

        kehadiran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), KehadiranActivity.class);
                startActivity(intent);
            }
        });

        kalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), KalenderActivity.class);
                startActivity(intent);
            }
        });

        materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gunakan getActivity() untuk mendapatkan context dari Fragment
                Intent intent = new Intent(getActivity(), CekMateriActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}