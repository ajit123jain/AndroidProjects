package com.example.aditya.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Aditya on 12/25/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm3.realm").schemaVersion(1).build();
        Realm.setDefaultConfiguration(config);
    }
}
