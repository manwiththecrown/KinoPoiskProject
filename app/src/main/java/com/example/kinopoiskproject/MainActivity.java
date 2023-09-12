package com.example.kinopoiskproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new FirstFragment());

        BottomNavigationView btv = findViewById(R.id.bottomNavigation);
        btv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.buttonTF:
                        try {
                            com.example.kinopoiskproject.Page.page = 1;
                            setFragment(new FirstFragment());
                        } catch (Exception ignored) {

                        }
                        break;
                    case R.id.buttonOF:
                        try {
                            com.example.kinopoiskproject.Page.page = 2;
                            setFragment(new SecondFragment());
                        } catch (Exception ignored) {

                        }
                        break;
                }
                return true;
            }
        });
    }
    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutMain, fragment, null).commit();
    }

    private void closeFragment() {
        getSupportFragmentManager().beginTransaction()
                .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain))).commit();
    }



    public void nextPage(View view) {
        if(com.example.kinopoiskproject.Page.page == 1){
            com.example.kinopoiskproject.Page.numberPageFragment1++;
            closeFragment();
            setFragment(new FirstFragment());
        }
        else{
            com.example.kinopoiskproject.Page.numberPageFragment2++;
            closeFragment();
            setFragment(new SecondFragment());
        }

    }

    public void previousPage(View view) {
        int temp = com.example.kinopoiskproject.Page.numberPageFragment1;
        if(com.example.kinopoiskproject.Page.page == 1 & temp != 1)
        {
            closeFragment();
            setFragment(new FirstFragment());
            com.example.kinopoiskproject.Page.numberPageFragment1--;
        }
        else{
            closeFragment();
            setFragment(new SecondFragment());
            com.example.kinopoiskproject.Page.numberPageFragment2--;
        }
    }
}