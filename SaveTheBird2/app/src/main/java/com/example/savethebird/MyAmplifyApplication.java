package com.example.savethebird;

import android.app.Application;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyAmplifyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                        @Override
                        public void onResult(UserStateDetails userStateDetails) {
                            Log.i("INIT", "onResult: " + userStateDetails.getUserState());
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e("INIT", "Initialization error.", e);
                        }
                    }
            );

            Log.i("MyAmplifyApp", "Initialized Amplify");

            RestOptions options = RestOptions.builder()
                    .addPath("/todo")
                    .addBody("{\"name\":\"Mow the lawn\"}".getBytes())
                    .build();
//
//            Amplify.API.post(options,
//                    response -> Log.i("MyAmplifyApp", "POST " + response.getData().asString()),
//                    error -> Log.e("MyAmplifyApp", "POST failed", error)
//            );
//
//            RestOptions options = new RestOptions("/todo");

//            Amplify.API.get(options,
//                    restResponse -> Log.i("MyAmplifyApp", restResponse.toString()),
//                    apiFailure -> Log.e("MyAmplifyApp", apiFailure.getMessage(), apiFailure)
//            );
//
//
//
//            Amplify.Auth.fetchAuthSession(
//                    result -> Log.i("AmplifyQuickstart", result.toString()),
//                    error -> Log.e("AmplifyQuickstart", error.toString())
//            );


        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }
}