package com.example.aditya.realm;

import android.util.Log;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Aditya on 12/25/2017.
 */

public class RealmHelper {
    Realm realm;
    public RealmHelper(Realm realm1) {
       this.realm = realm1;
    }
    public void save(final ProductRealModel productRealModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(realm!=null){
                    Log.d("Realm Helper","Database Exist");
                    Number currentId = realm.where(ProductRealModel.class).max("id");
                    int nextId ;
                    if (currentId == null){
                        nextId = 1;
                    }
                    else {
                        nextId = currentId.intValue()+1;
                    }
                    productRealModel.setId(nextId);
                    ProductRealModel p = realm.copyToRealm(productRealModel);
                }
                else{
                    Log.d("Realm Helper","Database Not Exist");
                }
            }
        });
    }
    public List<ProductRealModel> getAllProducts(){
        List<ProductRealModel> list = realm.where(ProductRealModel.class).findAll();
        return  list;

    }
    public void update(final int id, final String name , final String image){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
              ProductRealModel productRealModel =  realm.where(ProductRealModel.class).equalTo("id",id).findFirst();
              productRealModel.setName(name);
              productRealModel.setImage(image);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
              Log.d("Realm Helper ","Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
               error.printStackTrace();
            }
        });
    }
}
