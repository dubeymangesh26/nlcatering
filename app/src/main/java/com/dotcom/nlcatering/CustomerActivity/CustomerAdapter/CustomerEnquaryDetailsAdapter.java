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
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerLisrtEnquiryResponse;

import java.util.List;

public class CustomerEnquaryDetailsAdapter extends RecyclerView.Adapter<CustomerEnquaryDetailsAdapter.ViewHolder> {

    Context context;
    List<CustomerLisrtEnquiryResponse.ResponseBean> list;

    public CustomerEnquaryDetailsAdapter(Context context, List<CustomerLisrtEnquiryResponse.ResponseBean> responseBeanList) {
        this.context = context;
        this.list = responseBeanList;
    }

    @Override
    public CustomerEnquaryDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.foodtype, parent, false);
        return new CustomerEnquaryDetailsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomerEnquaryDetailsAdapter.ViewHolder holder, final int position) {

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
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
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
