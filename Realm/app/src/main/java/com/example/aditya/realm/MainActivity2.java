package com.example.aditya.realm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aditya.realm.receiver.NetworkStateChangeReceiver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.example.aditya.realm.receiver.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity2 extends AppCompatActivity {
    Realm realm;
    Resources resources;
    static boolean flag = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resources = getResources();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(configuration);
        Button importbtn = (Button) findViewById(R.id.importbtn);
        Button delete = (Button)findViewById(R.id.itemDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                if (realm.where(Person.class).findAll().size()>0) {
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.where(Person.class).findFirst().deleteFromRealm();
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Log.d("Delete Success", "Now no of items : "+realm.where(Person.class).findAll().size());
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Log.d("Error ",realm.where(Person.class).findAll().size()+"");
                        }
                    });
                }
            }
            else
                {
                    Toast.makeText(getApplicationContext(), "Can't Delete flag value false ", Toast.LENGTH_SHORT).show();
                }}
        });
        importbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // RealmImporter realmImporter = new RealmImporter(getResources());
               // realmImporter.importFromJson();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Test","Called");
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";
                flag = isNetworkAvailable;
                Snackbar.make(findViewById(R.id.activity_main2), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Flag Value Change"+flag,Toast.LENGTH_LONG).show();

            }
        }, intentFilter);
      /*  realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                InputStream inputStream = getResources().openRawResource(R.raw.person);
                try {
                    //realm.createAllFromJson(Person.class, inputStream);
                    // transactionTime.setEnd(System.currentTimeMillis());
                    Person person = realm.createObject(Person.class);
                    person.setAge(20);

                    Name name = realm.createObject(Name.class);
                    name.setId(1);
                    name.setStr("Ajit");
                    person.setName(name);
                    Log.d("Inside", "try");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    realm.close();
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
         Log.d("Operation1","Successs");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        }); */
       Log.d("Size",realm.where(Person.class).findAll().size()+"");


   /*   realm.executeTransactionAsync(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {
               Contact bob = realm.createObject(Contact.class);
               bob.setName("Ajit");

               Email e = realm.createObject(Email.class);
               e.setActive(true);
               e.setAddress("Anwa");
               bob.setEmail(e);
           }
       }, new Realm.Transaction.OnSuccess() {
           @Override
           public void onSuccess() {
             Log.d("Opeartion","Success");
           }
       }, new Realm.Transaction.OnError() {
           @Override
           public void onError(Throwable error) {
               Log.d("Opeartion","Fail");
           }
       }); */
       /* realm.executeTransactionAsync(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {
               Person bob = realm.createObject(Person.class);
               bob.setAge(20);
               RealmList<Name> emailRealmList = new RealmList<>();
               Name name  = realm.createObject(Name.class);
               name.setStr("Ajit");
               name.setId(32);
               Name name1  = realm.createObject(Name.class);
               name1.setStr("Kranti");
               name1.setId(20);
               emailRealmList.add(name);
               emailRealmList.add(name1);

               Email email = realm.createObject(Email.class);
               email.setAddress("Kota");
               email.setActive(false);

               bob.setName(emailRealmList);
               bob.setEmail(email);
           }
       }, new Realm.Transaction.OnSuccess() {
           @Override
           public void onSuccess() {
             Log.d("Opeartion","Success");
           }
       }, new Realm.Transaction.OnError() {
           @Override
           public void onError(Throwable error) {
               Log.d("Opeartion","Fail");
           }
       });
       */

     /*  realm.executeTransactionAsync(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {
               Gson gson = new Gson();
               Log.d("Data",realm.where(Contact.class).findAll().size()+"");
               Contact c = realm.where(Contact.class).findFirst();

               realm.copyFromRealm(c);
               Log.d("Data in Json ",gson.toJson(realm.copyFromRealm(c)));
            realm.createObjectFromJson(Contact.class,gson.toJson(realm.copyFromRealm(c)));
           }
       }, new Realm.Transaction.OnSuccess() {
           @Override
           public void onSuccess() {
             Log.d("Operation","Success");
           }
       }, new Realm.Transaction.OnError() {
           @Override
           public void onError(Throwable error) {
             error.printStackTrace();
           }
       }); */

     /*
     if (realm.where(Person.class).findAll().size()>0) {
         realm.executeTransactionAsync(new Realm.Transaction() {
             @Override
             public void execute(Realm realm) {
                 realm.where(Person.class).findFirst().deleteFromRealm();
             }
         }, new Realm.Transaction.OnSuccess() {
             @Override
             public void onSuccess() {
                 Log.d("Delete Success", ""+realm.where(Person.class).findAll().size());
             }
         }, new Realm.Transaction.OnError() {
             @Override
             public void onError(Throwable error) {
                 Log.d("Error ",realm.where(Person.class).findAll().size()+"");
             }
         });



     }
//        realm.where(Person.class).findFirst().deleteFromRealm();
        Gson gson = new Gson();
        Log.d("Data",realm.where(Person.class).findAll().size()+"");
        List<Person> c = realm.where(Person.class).findAll();
        for (int i=0; i<c.size(); i++) {
            realm.copyFromRealm(c.get(i));
            Log.d("Data in Json ", gson.toJson(realm.copyFromRealm(c.get(i))));
            // Log.d("String", gson.toJson(realm.copyFromRealm(c)).toString());
        }
         */



         //  Log.d(""+i, c.get(i) + c.get(i).getEmail().getAddress()+c.get(i).getEmail().isActive());


     /*  realm.executeTransaction(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {
               InputStream inputStream = resources.openRawResource(R.raw.people);
               try {
                   realm.createAllFromJson(People.class, inputStream);
                   //transactionTime.setEnd(System.currentTimeMillis());
               } catch (Exception e) {
                   e.printStackTrace();
               } finally {
                   realm.close();
               }

           }
       }); */
      /*  realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                InputStream inputStream = resources.openRawResource(R.raw.people);
                try {
                    realm.createAllFromJson(People.class, inputStream);
                  //  transactionTime.setEnd(System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    realm.close();
                }
            }
        });*/



    }
}
