package com.example.aditya.realm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aditya.realm.receiver.NetworkStateChangeReceiver;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.example.aditya.realm.receiver.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity extends AppCompatActivity {
    EditText productName,productUrl,productUpdateName;
    Button productInsert,productShow,productUpdate;
    String mName,mImageUrl;
    Realm realm1;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<ProductRealModel> productRealModelList;
    static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productInsert = (Button)findViewById(R.id.product__insert);
        productName = (EditText)findViewById(R.id.product_name);
        productUpdateName = (EditText)findViewById(R.id.product_update_name);
        productUrl = (EditText)findViewById(R.id.product_image_url);
        productShow = (Button) findViewById(R.id.product__show);
        productUpdate = (Button)findViewById(R.id.product__update);
        recyclerView = (RecyclerView)findViewById(R.id.product_list);
        productRealModelList  = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Test","Called");
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";
                flag = isNetworkAvailable;
                Snackbar.make(findViewById(R.id.activity_main1), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Flag Value Change"+flag,Toast.LENGTH_LONG).show();

            }
        }, intentFilter);




       /* Realm realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        try {
            // ... Do something ...
        } finally {
            realm.close();
        }*/

       //setUp Realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm1 = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm1);

        productInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mName = productName.getText().toString();
                mImageUrl = productUrl.getText().toString();
                if (!mName.isEmpty() && !mImageUrl.isEmpty()){
                  ProductRealModel productRealModel = new ProductRealModel();
                  productRealModel.setImage(mImageUrl);
                  productRealModel.setName(mName);

                  realmHelper.save(productRealModel);

                  Toast.makeText(getApplicationContext(),"Product Insert Successfully",Toast.LENGTH_LONG).show();
                  productName.setText("");
                  productUrl.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Name and Url can not empty  ",Toast.LENGTH_LONG).show();
                }

            }
        });
        productShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               productRealModelList = realmHelper.getAllProducts();
               productAdapter = new ProductAdapter(getApplicationContext(),productRealModelList);
               recyclerView.setAdapter(productAdapter);
            }
        });
        productUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(productUpdateName.getText().toString());
                String name = productName.getText().toString();
                String  url = productUrl.getText().toString();

                realmHelper.update(id,name,url);

            }
        });
    }


}
