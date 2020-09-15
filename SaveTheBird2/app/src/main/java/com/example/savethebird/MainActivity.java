package com.example.savethebird;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    List<Distribution> list  = new ArrayList<>();



    Location location = null;
    int flag = 0;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
         //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        getMyLocation();
        double distance = compareDB(location);
        notifyLocation(4);

        buildTodo();

//        Amplify.DataStore.observe(Todo.class,
//                started -> Log.i("Tutorial", "Observation began."),
//                change -> Log.i("Tutorial", change.item().toString()),
//                failure -> Log.e("Tutorial", "Observation failed.", failure),
//                () -> Log.i("Tutorial", "Observation complete.")
//        );
//        navView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

    }

    @Override
    public void onResume(){
        super.onResume();

        Fragment myFragment = getSupportFragmentManager().findFragmentById(R.id.homelayout);
        if (myFragment != null && myFragment.isVisible()) {
//            getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            for(int i = 0; i < count; ++i) {
                fm.popBackStack();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        getSupportFragmentManager().popBackStackImmediate();

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void readCSV(){
        int i = 0;// 用于标记打印的条数
        try {
            File csv = new File("file:///android_asset/ebird_hooplo.csv"); // CSV文件路径
            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            String line = "";
            /**
             * 这里读取csv文件中的前10条数据
             * 如果要读取第10条到30条数据,只需定义i初始值为9,wile中i<10改为i>=9&&i<30即可,其他范围依次类推
             */
            while ((line = br.readLine()) != null ) { // 这里读取csv文件中的前10条数据
                i++;
//                System.out.println("第" + i + "行：" + line);// 输出每一行数据
                /**
                 *  csv格式每一列内容以逗号分隔,因此要取出想要的内容,以逗号为分割符分割字符串即可,
                 *  把分割结果存到到数组中,根据数组来取得相应值
                 */
                String buffer[] = line.split(",");// 以逗号分隔
                double latitude = Double.parseDouble(buffer[26]);
                double longitude =  Double.parseDouble(buffer[27]);
                String ObservationDate = buffer[28];
                Distribution dt = new Distribution(longitude,latitude,ObservationDate);
                list.add(dt);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMyLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        String provider;
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            // 当没有可用的位置提供器时,弹出Toast提示用户
            Toast.makeText(this, "No location provider to use",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        Log.e("location", provider);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            // 显示当前设备的位置信息
            showLocation(location);


        }
        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);

    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle
                extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
        @Override
        public void onLocationChanged(Location location) {
            // 更新当前设备的位置信息
            showLocation(location);

        }
    };


    private void showLocation(Location location) {
        String currentPosition = "latitude is " + location.getLatitude() + "\n"
                + "longitude is " + location.getLongitude();
        Log.e("location",currentPosition);
    }
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * 6378.137;
        Map<String, Object> map = new HashMap<String, Object>();
        BigDecimal decimal = new BigDecimal(distance);
        //结果保留2位小数
        distance = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        map.put("units", "km");
        map.put("distance", distance);

        return distance;
    }


    public void notifyLocation(double distance){

//        if(flag == 0){
            if(distance <=5){
                Toast.makeText(MainActivity.this, "Warning", Toast.LENGTH_LONG);
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("WARNING").setMessage("Hooded Plover are near you in 5km").setPositiveButton("UnderStand", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Ok","aok");
                        flag = 1;
                    }
                }).show();

            }
//        }
    }

    private double compareDB(Location location){
        readCSV();
        boolean finish = false;
        double distance = 0;
        if(finish){
            for(int i =0; i <list.size(); i++){
                 distance = getDistance(location.getLongitude(),location.getLatitude(),list.get(i).getLongitude(),list.get(i).getLatitude());
                if(distance<5){
                    break;
                }
            }
            finish = true;
        }
        return distance;

    }

     private void buildTodo(){
        Todo todo = Todo.builder()
                .name("My first dodo")
                .description("Db")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(todo),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );

     }
}