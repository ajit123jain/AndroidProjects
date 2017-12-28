package com.example.aditya.realm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aditya on 12/25/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<ProductRealModel> productRealModelList;
    Context context;

    public ProductAdapter(Context context1 , List<ProductRealModel> productRealModelList) {
        this.productRealModelList = productRealModelList;
        context = context1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProductRealModel productRealModel = productRealModelList.get(position);
        holder.productName.setText(productRealModel.getName());
        holder.productImage.setText(productRealModel.getImage());
    }


    @Override
    public int getItemCount() {
        return productRealModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productName,productImage;


        public MyViewHolder(View itemView)  {
            super(itemView);
             productName = itemView.findViewById(R.id.product_name);
             productImage = itemView.findViewById(R.id.product_image_url);
             itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = productRealModelList.get(getAdapterPosition()).getId();

        }
    }
}
