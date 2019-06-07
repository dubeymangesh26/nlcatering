package com.dotcom.nlcatering.CaterersActivity.CatererAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dotcom.nlcatering.CaterersActivity.CaterersEnquiries.CaterersEnquiryDetails;
import com.dotcom.nlcatering.CaterersActivity.CaterersOrders.CaterersOrderList;
import com.dotcom.nlcatering.CustomerActivity.CustomerBiddings.CustomerBiddingDetails;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CatererResponse.CatererEnquiryResponse;

import java.util.List;

public class CatererEnquiryListAdapter extends RecyclerView.Adapter<CatererEnquiryListAdapter.ViewHolder> {

    Context context;
    List<CatererEnquiryResponse.ResponseBean> list;

    public CatererEnquiryListAdapter(Context context, List<CatererEnquiryResponse.ResponseBean> responseBeanList) {
        this.context = context;
        this.list = responseBeanList;
    }

    @Override
    public CatererEnquiryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.foodtype, parent, false);
        return new CatererEnquiryListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CatererEnquiryListAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getMainMenuImg())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .centerCrop()
                .into(holder.img);

        String date[] = list.get(position).getOrder_Date().split("T");
        String  reverseDate[] = date[0].split("-");
        String actualDate = reverseDate[2]+"-"+reverseDate[1]+"-"+reverseDate[0];
        holder.date.setText(actualDate);
        holder.catname.setText(list.get(position).getType_catering());
        holder.foodmenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CaterersEnquiryDetails.class);
                intent.putExtra("typecatering", list.get(position).getType_catering());
                intent.putExtra("person", list.get(position).getNo_of_person());
                intent.putExtra("bidstatus", list.get(position).getBid_Status());
                intent.putExtra("date", list.get(position).getOrder_Date());
                intent.putExtra("time", list.get(position).getTime());
                intent.putExtra("place", list.get(position).getPlace_of_performance());
                intent.putExtra("description", list.get(position).getDescription());
                intent.putExtra("enquiryId", list.get(position).getEnquiry_Id());
                context.startActivity(intent);


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
        TextView date, catname;

        public ViewHolder(View itemView) {
            super(itemView);
            foodmenuLayout = itemView.findViewById(R.id.foodmenuLayout);
            img = itemView.findViewById(R.id.profile_image);
            catname = itemView.findViewById(R.id.wname);
            date = itemView.findViewById(R.id.date);
        }
    }
}
