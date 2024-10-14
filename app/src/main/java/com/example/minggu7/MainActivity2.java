package com.example.minggu7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.minggu7.fragment.BerandaFragment;
import com.example.minggu7.fragment.NotifikasiFragment;
import com.example.minggu7.fragment.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        loadFragment(new BerandaFragment());
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.fr_beranda);


        sharedPreferences = getSharedPreferences(Login.SHARED_PREF_NAME, MODE_PRIVATE);

        if (!sharedPreferences.getBoolean("masuk", false)) {
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
        } else if (selected == R.id.fr_notifikasi) {
            fragment = new NotifikasiFragment();
        } else if (selected == R.id.fr_profil) {
            fragment = new ProfilFragment();
        }
        return loadFragment(fragment);
    }

}