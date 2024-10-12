package com.aldorayhanr.aplikasiabsensi.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.aldorayhanr.aplikasiabsensi.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.aldorayhanr.aplikasiabsensi.fragment.BerandaFragment;
import com.aldorayhanr.aplikasiabsensi.fragment.NotifikasiFragment;
import com.aldorayhanr.aplikasiabsensi.fragment.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new BerandaFragment());
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.fr_beranda);


        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        if (sharedPreferences.getString("logged", "false").equals("false")) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

    }


        private boolean loadFragment (Fragment fragment){
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }
            return false;
        }



        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            int selected = item.getItemId();
            Fragment fragment = null;
            if (selected == R.id.fr_beranda) {
                fragment = new BerandaFragment();
            } else if (selected == R.id.fr_pemberitahuan) {
                fragment = new NotifikasiFragment();
            } else {
                fragment = new ProfilFragment();
            }

            return loadFragment(fragment);
        }
    }