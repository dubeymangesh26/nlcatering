package com.dotcom.nlcatering.CustomerActivity.CustomerAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerQuotation;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerLisrtEnquiryResponse;

import java.util.List;

public class CustomerEnquiryListAdapter extends RecyclerView.Adapter<CustomerEnquiryListAdapter.ViewHolder> {

    Context context;
    List<CustomerLisrtEnquiryResponse.ResponseBean> list;

    public CustomerEnquiryListAdapter(Context context, List<CustomerLisrtEnquiryResponse.ResponseBean> responseBeanList) {
        this.context = context;
        this.list = responseBeanList;
    }

    @Override
    public CustomerEnquiryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.foodtype, parent, false);
        return new CustomerEnquiryListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomerEnquiryListAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getMainMenuImg())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .centerCrop()
                .into(holder.img);
        holder.catname.setText(list.get(position).getType_catering());
        String date[] = list.get(position).getOrder_Date().split("T");
        String  reverseDate[] = date[0].split("-");
        String actualDate = reverseDate[2]+"-"+reverseDate[1]+"-"+reverseDate[0];
        holder.date.setText(actualDate);
        holder.foodmenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.details_dialog);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;

// set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.name);
                text.setText(list.get(position).getType_catering());

                TextView text2 = (TextView) dialog.findViewById(R.id.noOfPerson1);
                text2.setText(String.valueOf(list.get(position).getNo_of_person()));

                TextView text3 = (TextView) dialog.findViewById(R.id.date);
                String date[] = list.get(position).getOrder_Date().split("T");
                String  reverseDate[] = date[0].split("-");
                String actualDate = reverseDate[2]+"-"+reverseDate[1]+"-"+reverseDate[0];
                text3.setText(actualDate);


                TextView text4 = (TextView) dialog.findViewById(R.id.time);
                text4.setText(list.get(position).getTime());
                TextView text5 = (TextView) dialog.findViewById(R.id.place);
                text5.setText(list.get(position).getPlace_of_performance());
                TextView text6 = (TextView) dialog.findViewById(R.id.decription);
                text6.setText(list.get(position).getDescription());
                ImageView image = (ImageView) dialog.findViewById(R.id.profile_image);
                ImageView close = (ImageView) dialog.findViewById(R.id.close);
                Glide.with(context).load(list.get(position).getMainMenuImg())
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontTransform()
                        .centerCrop()
                        .into(image);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();



                    }
                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout foodmenuLayout;
        ImageView img;
        TextView date,catname;

        public ViewHolder(View itemView) {
            super(itemView);
            foodmenuLayout = itemView.findViewById(R.id.foodmenuLayout);
            img = itemView.findViewById(R.id.profile_image);
            catname = itemView.findViewById(R.id.wname);
            date = itemView.findViewById(R.id.date);

        }
    }

}
