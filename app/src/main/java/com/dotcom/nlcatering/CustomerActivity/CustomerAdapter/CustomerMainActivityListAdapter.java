package com.dotcom.nlcatering.CustomerActivity.CustomerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.FoodType;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerMainActivityResponse;

import java.util.List;

public class CustomerMainActivityListAdapter extends RecyclerView.Adapter<CustomerMainActivityListAdapter.ViewHolder> {

    Context context;
    List<CustomerMainActivityResponse.ResponseBean> list;

    public CustomerMainActivityListAdapter(Context context, List<CustomerMainActivityResponse.ResponseBean> responseBeanList) {
        this.context = context;
        this.list = responseBeanList;
    }

    @Override
    public CustomerMainActivityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new CustomerMainActivityListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomerMainActivityListAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getPhoto_Path())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .centerCrop()
                .into(holder.img);
        holder.name.setText(list.get(position).getCat_Name());
        holder.menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, FoodType.class);
                intent.putExtra(Constants.CAT_ID, list.get(position).getCat_Id());
                intent.putExtra("mainFoodName", list.get(position).getCat_Name());
                context.startActivity(intent);


                /*intent.putExtra("CaterersType", list.get(position).getCat_Id());
                intent.putExtra(Constants.MENU_IDENTIFY, "CaterersType");
                context.startActivity(intent);*/
                //context.startActivity(new Intent(context, FoodType.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout menuLayout;
        ImageView img;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            menuLayout = itemView.findViewById(R.id.menuLayout);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}
