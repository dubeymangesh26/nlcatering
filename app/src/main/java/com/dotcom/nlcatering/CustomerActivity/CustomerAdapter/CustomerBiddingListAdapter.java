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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dotcom.nlcatering.CustomerActivity.CustomerBiddings.CustomerBiddingDetails;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerBiddingResponse;

import java.util.List;

public class CustomerBiddingListAdapter extends RecyclerView.Adapter<CustomerBiddingListAdapter.ViewHolder> {

    Context context;
    List<CustomerBiddingResponse.ResponseBean> list;

    public CustomerBiddingListAdapter(Context context, List<CustomerBiddingResponse.ResponseBean> responseBeanList) {
        this.context = context;
        this.list = responseBeanList;
    }

    @Override
    public CustomerBiddingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.biddinglistlatout, parent, false);
        return new CustomerBiddingListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomerBiddingListAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getMainMenuImg())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .centerCrop()
                .into(holder.custlogo);
        holder.company_name.setText(list.get(position).getCompany_Name());
        holder.amount.setText(String.valueOf(list.get(position).getTotal_Amount()));
        holder.biddingmenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.bidding_placeorderlayout);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;

// set the custom dialog components - text, image and button

                TextView text3 = (TextView) dialog.findViewById(R.id.company_name);
                text3.setText(list.get(position).getCompany_Name());

                TextView text = (TextView) dialog.findViewById(R.id.name);
                text.setText(list.get(position).getOrder_Type());

                TextView text2 = (TextView) dialog.findViewById(R.id.noOfPerson1);
                text2.setText(String.valueOf(list.get(position).getQty()));

                TextView text4 = (TextView) dialog.findViewById(R.id.totalAmount);
                text4.setText(String.valueOf(list.get(position).getTotal_Amount()));

                TextView text6 = (TextView) dialog.findViewById(R.id.decription);
                text6.setText(list.get(position).getDescription());
                Button placeOrder=(Button)dialog.findViewById(R.id.placeorder);
                ImageView profile_image = (ImageView) dialog.findViewById(R.id.profile_image);
                ImageView close = (ImageView) dialog.findViewById(R.id.close);
                Glide.with(context).load(list.get(position).getMainMenuImg())
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontTransform()
                        .centerCrop()
                        .into(profile_image);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();



                    }
                });

                placeOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, CustomerBiddingDetails.class);
                        intent.putExtra("typecatering",list.get(position).getOrder_Type());
                        intent.putExtra("person",list.get(position).getQty());
                        intent.putExtra("totalamount",list.get(position).getTotal_Amount());


                        context.startActivity(intent);
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

        LinearLayout biddingmenuLayout;
        ImageView custlogo;
        TextView amount,company_name;

        public ViewHolder(View itemView) {
            super(itemView);
            biddingmenuLayout = itemView.findViewById(R.id.biddingmenulayout);
            custlogo = itemView.findViewById(R.id.custlogo);
            company_name = itemView.findViewById(R.id.company_name);
            amount = itemView.findViewById(R.id.amount);

        }
    }
}
