package com.example.aplikasiabsensi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import fragment.beranda_fragment;
import fragment.notification_fragment;
import fragment.profile_fragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        loadFragment(new beranda_fragment());
        BottomNavigationView navigationView= findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.fr_beranda);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @SuppressLint("NonConstrantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int selected = item.getItemId();
        Fragment fragment = null;
         if (selected==R.id.fr_beranda) {
            fragment = new beranda_fragment();
        } else if (selected==R.id.fr_pemberitahuan) {
            fragment = new notification_fragment();
        } else {
            fragment = new profile_fragment();
        }
        return  loadFragment(fragment);
    }
}