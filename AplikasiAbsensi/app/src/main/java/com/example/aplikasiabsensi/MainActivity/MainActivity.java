package com.example.aplikasiabsensi.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.aplikasiabsensi.R;
import com.example.aplikasiabsensi.fragment.editProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.example.aplikasiabsensi.fragment.beranda_fragment;
import com.example.aplikasiabsensi.fragment.notification_fragment;
import com.example.aplikasiabsensi.fragment.profile_fragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//
//        setContenView(R.layout.fragment_profile_fragment);
//
//        ImageView btn= findViewById(R.id.foto_profil);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(profile_fragment.this, editProfile.class));
//            }
//        });

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