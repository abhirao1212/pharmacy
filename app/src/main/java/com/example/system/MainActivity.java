package com.example.system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import Subcat1__with_recycler.SecondFragment;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;

    SharedPreferences sharedPreferences;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);

        sharedPreferences = getSharedPreferences("session",MODE_PRIVATE);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainFragment()).commit();



        //Steo1

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadfragment(new MainFragment());


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.profile) {


                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragement()).addToBackStack(null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);


                    Toast.makeText(MainActivity.this, "welcome to your profile ", Toast.LENGTH_SHORT).show();


                } else if (id == R.id.category) {




                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (id == R.id.Add_cart) {


                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddtocartFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);


                }else if (id == R.id.help) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new helpFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);




                } else if (id == R.id.Logout) {


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("key");
                    editor.apply();

                    Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(MainActivity.this, "Thanku for visit ", Toast.LENGTH_SHORT).show();


                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }




            private void loadfragment(Fragment fragment) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.container, fragment);
                ft.commit();
            }
        });
    }

    private void loadfragment(MainFragment fragment) {
    }

    //to back to any activity
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();



        }


        }
    }


