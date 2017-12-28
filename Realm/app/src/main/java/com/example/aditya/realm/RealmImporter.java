package com.example.aditya.realm;

import android.content.res.Resources;
import android.util.Log;

import java.io.InputStream;

import io.realm.Realm;

/**
 * Created by Aditya on 12/26/2017.
 */

public class RealmImporter {

    Resources resources;
    TransactionTime transactionTime;

    public RealmImporter(Resources resources) {
        this.resources = resources;
    }

    public void importFromJson() {
        Realm realm = Realm.getDefaultInstance();

        //transaction timer
        transactionTime = new TransactionTime(System.currentTimeMillis());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                InputStream inputStream = resources.openRawResource(R.raw.person);
                try {
                    realm.createAllFromJson(Person.class, inputStream);
                    transactionTime.setEnd(System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    realm.close();
                }
            }
        });
        Log.d("Realm", "createAllFromJson Task completed in " + transactionTime.getDuration() + "ms");
    }
}
