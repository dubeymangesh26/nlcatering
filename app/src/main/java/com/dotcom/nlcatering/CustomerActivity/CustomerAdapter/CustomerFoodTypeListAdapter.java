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
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerQuotation;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.FoodType_SubMenuResponse;

import java.util.List;

public class CustomerFoodTypeListAdapter extends RecyclerView.Adapter<CustomerFoodTypeListAdapter.ViewHolder> {

    Context context;
    List<FoodType_SubMenuResponse.ResponseBean> list;

    public CustomerFoodTypeListAdapter(Context context, List<FoodType_SubMenuResponse.ResponseBean> responseBeanList) {
        this.context = context;
        this.list = responseBeanList;
    }

    @Override
    public CustomerFoodTypeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.foodtype, parent, false);
        return new CustomerFoodTypeListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomerFoodTypeListAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getSubCat_photo())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .centerCrop()
                .into(holder.profile_image);

        holder.wname.setText(list.get(position).getSubCat_Name());
        holder.foodmenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(UserPreferenceUtils.checkLogin(context)){
                    Intent intent = new Intent(context, CustomerQuotation.class);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, CustomerLogin.class);
                    intent.putExtra(Constants.CAT_ID, list.get(position).getCat_Id());
                    context.startActivity(intent);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout foodmenuLayout;
        ImageView profile_image;
        TextView wname;
        String email;


        public ViewHolder(View itemView) {
            super(itemView);
            foodmenuLayout = itemView.findViewById(R.id.foodmenuLayout);
            profile_image = itemView.findViewById(R.id.profile_image);
            wname = itemView.findViewById(R.id.wname);
        }
    }



}
