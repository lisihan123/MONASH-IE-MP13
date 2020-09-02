package com.example.savethebird;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(getActionBar() !=null)
//        {
//            getActionBar().hide();
//        }
//
//        if(getSupportActionBar() !=null){
//            getSupportActionBar().hide();
//        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
         //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        Amplify.DataStore.observe(Todo.class,
//                started -> Log.i("Tutorial", "Observation began."),
//                change -> Log.i("Tutorial", change.item().toString()),
//                failure -> Log.e("Tutorial", "Observation failed.", failure),
//                () -> Log.i("Tutorial", "Observation complete.")
//        );
//        navView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

    }




    public void replace(Fragment nextFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, nextFragment).commit();
    }



}


