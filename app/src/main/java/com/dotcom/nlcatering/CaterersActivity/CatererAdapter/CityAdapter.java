package com.dotcom.nlcatering.CaterersActivity.CatererAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.dotcom.nlcatering.Response.CatererResponse.CatererCityAndCatererTypeResponse;

import java.util.List;

public class CityAdapter {
    private List<CatererCityAndCatererTypeResponse.ResponseBean.CityBean> statuses;
    private Context context;
    public Resources res;
    CatererCityAndCatererTypeResponse currRowVal = null;
    LayoutInflater inflater;

   /* public CityAdapter(Context context,
                         int textViewResourceId, ArrayList<CatererCityAndCatererTypeResponse.ResponseBean.CityBean> statuses,
                         Resources resLocal) {
        super(context, textViewResourceId, statuses);
        this.context = context;
        this.statuses = statuses;
        this.res = resLocal;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.biddinglistlatout.custom_spinner_dropdown_item, parent, false);
        currRowVal = null;
        currRowVal = (CatererCityAndCatererTypeResponse) statuses.get(position);
        TextView label = (TextView) row.findViewById(R.id.city);
        if (position == 0) {
            label.setText("Please select status");
        } else {
            label.setText(currRowVal.getStatus());
        }

        return row;
    }*/
}