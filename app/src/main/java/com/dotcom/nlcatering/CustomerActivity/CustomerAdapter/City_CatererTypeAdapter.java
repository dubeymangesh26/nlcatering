package com.dotcom.nlcatering.CustomerActivity.CustomerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CatererResponse.CatererCityAndCatererTypeResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class City_CatererTypeAdapter {

   /* Context context;
    final Calendar myCalendar = Calendar.getInstance();
    ArrayList<String> stringList = new ArrayList<>();
    public static List<CatererCityAndCatererTypeResponse.ResponseBean.CityBean> arrSubCateogry;
    List<String> breedarray = new ArrayList<>();
    List<CatererCityAndCatererTypeResponse.ResponseBean.FoodMenuBean> cattype = new ArrayList<>();
    private String visitype = "";
    private String addonprice;


    public City_CatererTypeAdapter(Context context, List<CatererCityAndCatererTypeResponse.ResponseBean.CityBean> response, List<CatererCityAndCatererTypeResponse.ResponseBean.FoodMenuBean> CatererCityAndCatererTypeResponse) {
        this.context = context;
        this.arrSubCateogry = response;
        this.cattype = CatererCityAndCatererTypeResponse;

    }


    @Override
    public City_CatererTypeAdapter.ViewHOLDER onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_customer_registration, parent, false);
        ViewHOLDER viewHolder = new ViewHOLDER(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final City_CatererTypeAdapter.ViewHOLDER holder, final int position) {

        holder.city.setText(arrSubCateogry.get(position).getName());
        holder.catererType.setText(cattype.get(position).getCat_Name());

        if (breedarray.size() > 0) {
            breedarray.clear();
        }
        final ArrayAdapter<String> loadTypeArrayAdapter1 = new ArrayAdapter<>(context, R.layout.custom_spinner_item, breedarray);
        loadTypeArrayAdapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        holder.city.setThreshold(1);
        holder.city.setAdapter(loadTypeArrayAdapter1);
        holder.city.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (breedarray.size() > 0) {
                    breedarray.clear();
                }
                for (int i = 0; i < arrSubCateogry.get(position).getName().size(); i++) {
                    // breedarray.clear();
                    breedarray.add(arrSubCateogry.get(position).getName().get(i).getAddOnnName());
                    addonprice = String.valueOf(arrSubCateogry.get(position).getName().get(i).getAddOnAmount());
                }

                holder.city.showDropDown();
                return false;
            }
        });
        breedarray.clear();


    }



    public class ViewHOLDER extends RecyclerView.ViewHolder {
        //RecyclerView recyclerView;
        ImageView imageView;
        TextView packagename, servicesincludes, salonprice, homeprice;
        LinearLayout prod;
        Button button;
        RadioButton salon, home;
        AutoCompleteTextView city,catererType;
        RadioGroup salonorhomegrp;

        public ViewHOLDER(View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            catererType = itemView.findViewById(R.id.cateringType);


        }
    }

*/
}


