package com.example.savethebird;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Distribution> list  = new ArrayList<>();
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

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
        super.onBackPressed();
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
                String latitude = buffer[26];
                String longitude = buffer[27];
                String ObservationDate = buffer[28];
                Distribution dt = new Distribution(longitude,latitude,ObservationDate);
                list.add(dt);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testApi(){
//        AWSMobileClient.getInstance()

    }
}