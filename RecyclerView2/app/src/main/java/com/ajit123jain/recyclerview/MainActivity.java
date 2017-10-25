package com.ajit123jain.recyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    private List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.image_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        data = fill_with_data();

        horizontalAdapter=new HorizontalAdapter(data, getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

    }

    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();

        data.add(new Data( R.drawable.imag1, "Image 1"));
        data.add(new Data( R.drawable.imag2, "Image 2"));
        data.add(new Data( R.drawable.imag3, "Image 3"));
        data.add(new Data( R.drawable.imag4, "Image 4"));
        data.add(new Data( R.drawable.imag5, "Image 5"));
        data.add(new Data( R.drawable.imag6, "Image 6"));
        data.add(new Data( R.drawable.imag7, "Image 7"));
        data.add(new Data( R.drawable.imag8, "Image 8"));
        data.add(new Data( R.drawable.imag9, "Image 9"));
        data.add(new Data( R.drawable.imag10, "Image 10"));
        data.add(new Data( R.drawable.imag11, "Image 11"));
        data.add(new Data( R.drawable.imag12, "Image 12"));
        data.add(new Data( R.drawable.imag13, "Image 13"));
        data.add(new Data( R.drawable.imag14, "Image 14"));
        data.add(new Data( R.drawable.imag15, "Image 15"));
        data.add(new Data( R.drawable.imag16, "Image 16"));
        data.add(new Data( R.drawable.imag17, "Image 17"));
        data.add(new Data( R.drawable.imag18, "Image 18"));


        return data;
    }




    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>
    {

        List<Data> horizontalList = Collections.EMPTY_LIST;
        Context context;

        public HorizontalAdapter(List<Data> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_image, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.imageView.setImageResource(horizontalList.get(position).imageId);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = horizontalList.get(position).txt.toString();
                    Toast.makeText(MainActivity.this, list, Toast.LENGTH_SHORT).show();
                }

            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView ;
            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView)itemView.findViewById(R.id.image);

            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){

            case R.id.main_settings_btn:

                // handle settings
                break;

            case R.id.main_Linear:

                // Setup the LinearLayoutManager
                initListDisplay();
                break;


            case R.id.main_grid:

                // Setup the GridLayoutManager
                initGridDisplay();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    // Display a list
    private void initListDisplay(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        horizontal_recycler_view.setLayoutManager(layoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }

    // Display the Grid
    private void initGridDisplay(){
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        horizontal_recycler_view.setLayoutManager(layoutManager);
    }
}
