package com.example.savethebird;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Priority;
import com.amplifyframework.datastore.generated.model.Todo;
import com.example.savethebird.ui.FactDogFragment;
import com.example.savethebird.ui.FactKidFragment;
import com.example.savethebird.ui.FactNumberFragment;
import com.example.savethebird.ui.dashboard.DashboardFragment;
import com.example.savethebird.ui.factVehFragment;
import com.example.savethebird.ui.home.HomeFragment;
import com.example.savethebird.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
         //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Amplify.DataStore.observe(Todo.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );
//        navView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

    }


//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.navigation_home:
//                replace(new HomeFragment());
//                break;
//            case R.id.navigation_dashboard:
//                replace(new DashboardFragment());
//                break;
//            case R.id.navigation_notifications:
//                replace(new NotificationsFragment());
//                break;
//
//        }
//        return true;
//    }

    public void replace(Fragment nextFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, nextFragment).commit();
    }



}


