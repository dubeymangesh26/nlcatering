package com.dotcom.nlcatering.SingletonClass;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


import static android.content.ContentValues.TAG;

/**
 * Created by admin on 2/17/2017.
 */
public class SingletonClass {
    private boolean check;

    private static SingletonClass ourInstance = new SingletonClass();



    public static SingletonClass getInstance() {
        return ourInstance;
    }

    public static SingletonClass clear() {
        return ourInstance = new SingletonClass();
    }

    public ArrayList<Integer> getBrandIdList() {
        return brand;
    }

    public ArrayList<Integer> getCatidlist() {
        return catidlist;
    }

    public ArrayList<Integer> productid() {
        return PID;
    }

    public ArrayList<Integer> getBreedidlist() {
        return breedidlist;
    }

    public ArrayList<String> getPricename() {
        return pricename;
    }

    public ArrayList<String> getAgename() {
        return Agename;
    }


    public void setBrandIdList(ArrayList<Integer> brandIdList) {
        this.brand = brandIdList;
    }

    ArrayList<Integer> brand = new ArrayList<>();
    ArrayList<Integer> catidlist = new ArrayList<>();
    ArrayList<Integer> breedidlist = new ArrayList<>();
    ArrayList<String> pricename = new ArrayList<>();
    ArrayList<String> Agename = new ArrayList<>();
    ArrayList<Integer> PID = new ArrayList<>();


    public boolean isFilterApply() {
        return isFilterApply;
    }

    public void setFilterApply(boolean filterApply) {
        isFilterApply = filterApply;
    }

    private String Date;
    private String Time;
    private String Activityname;
    private String Centername;
    private String Message;
    private String SRID;
    private String USERID;
    private String FinalPrice;
    private String SavePrice;
    private String packagename;
    private String subcat;
    private int fav;
    private int POSITIONID;
    private boolean isIncart;
    private boolean newadd;
    boolean isFilterApply;
    private String Packageid;
    private String Salonorhome;
    private HashMap<String, String> Addonservices = new HashMap<>();
    private String Owneraddress;
    private String dogcatpackagename;
    private HashMap<String, String> addonservicesprice = new HashMap<>();
    private String salonorhomeradio;
    private String SRRRID;
    private String Price;
    ArrayList<String> timeasper = new ArrayList<>();

    //-----------------brand filter

    public boolean addcatId(int id) {
        Log.e(TAG, "brand size : " + catidlist.size());
        check = false;
        int a = catidlist.size();
        if (catidlist.size() > 0) {
            for (int i = 0; i < a; i++) {
                if (id == catidlist.get(i)) {
                    catidlist.set(i, id);
                    check = true;
                    return a == catidlist.size();
                }
            }
        }
        if (!check) {
            catidlist.add(id);
            check = false;
            return a < catidlist.size();
        }
        return false;
    }

    public void removecatId(int brand_id) {
        if (catidlist.size() > 0) {
            for (int i = 0; i < catidlist.size(); i++) {
                if (brand_id == catidlist.get(i)) {
                    catidlist.remove(i);
                }
            }
        }
    }

    public boolean addBrand(int id) {
        Log.e(TAG, "brand size : " + brand.size());
        check = false;
        int a = brand.size();
        if (brand.size() > 0) {
            for (int i = 0; i < a; i++) {
                if (id == brand.get(i)) {
                    brand.set(i, id);
                    check = true;
                    return a == brand.size();
                }
            }
        }
        if (!check) {
            brand.add(id);
            check = false;
            return a < brand.size();
        }
        return false;
    }

    public void removeBrand(int brand_id) {
        if (brand.size() > 0) {
            for (int i = 0; i < brand.size(); i++) {
                if (brand_id == brand.get(i)) {
                    brand.remove(i);
                }
            }
        }
    }

    public boolean addBreedId(int id) {
        Log.e(TAG, "brand size : " + breedidlist.size());
        check = false;
        int a = breedidlist.size();
        if (breedidlist.size() > 0) {
            for (int i = 0; i < a; i++) {
                if (id == breedidlist.get(i)) {
                    breedidlist.set(i, id);
                    check = true;
                    return a == breedidlist.size();
                }
            }
        }
        if (!check) {
            breedidlist.add(id);
            check = false;
            return a < breedidlist.size();
        }
        return false;
    }

    public void removeBreedId(int brand_id) {
        if (breedidlist.size() > 0) {
            for (int i = 0; i < breedidlist.size(); i++) {
                if (brand_id == breedidlist.get(i)) {
                    breedidlist.remove(i);
                }
            }
        }
    }


    public boolean addPricename(String name) {
        check = false;
        int a = pricename.size();
        if (pricename.size() > 0) {
            for (int i = 0; i < a; i++) {
                if (name == pricename.get(i)) {
                    pricename.set(i, name);
                    check = true;
                    return a == pricename.size();
                }
            }
        }
        if (!check) {
            pricename.add(name);
            check = false;
            return a < pricename.size();
        }
        return false;


    }

    public void removepricename(String name) {
        check = false;
        if (pricename.size() > 0) {
            for (int i = 0; i < pricename.size(); i++) {
                if (name == pricename.get(i)) {
                    pricename.remove(i);
                }
            }
        }
    }

    public boolean addAgename(String name) {
        check = false;
        int a = Agename.size();
        if (Agename.size() > 0) {
            for (int i = 0; i < a; i++) {
                if (name == Agename.get(i)) {
                    Agename.set(i, name);
                    check = true;
                    return a == Agename.size();
                }
            }
        }
        if (!check) {
            Agename.add(name);
            check = false;
            return a < Agename.size();
        }
        return false;


    }

    public void removeAgename(String name) {
        check = false;
        if (Agename.size() > 0) {
            for (int i = 0; i < Agename.size(); i++) {
                if (name == Agename.get(i)) {
                    Agename.remove(i);
                }
            }
        }
    }

    public boolean clearReport() {
        brand.clear();
        return brand.size() == 0;
    }

    public String getBrandFilter() {
        String str = null;
        for (int i = 0; i < brand.size(); i++) {
            if (i == 0)
                str = String.valueOf(brand.get(i));
            else
                str = str + "," + brand.get(i);
        }
        return str;
    }

    //---------------price filter
    int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    int max;

    public boolean clearPriceF() {
        setMax(5000);
        setMin(0);
        return true;
    }


    //---------------sort by price
    String sortBy = "";

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean clearSortBy() {
        sortBy = "0";
        return true;
    }


    private SingletonClass() {
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getActivityname() {
        return Activityname;
    }

    public void setActivityname(String activityname) {
        Activityname = activityname;
    }

    public String getSRID() {
        return SRID;
    }

    public void setSRID(String SRID) {
        this.SRID = SRID;
    }

    public String getCentername() {
        return Centername;
    }

    public void setCentername(String centername) {
        Centername = centername;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public boolean isIsIncart() {
        return isIncart;
    }

    public void setIsIncart(boolean isIncart) {
        this.isIncart = isIncart;
    }


    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getPOSITIONID() {
        return POSITIONID;
    }

    public void setPOSITIONID(int POSITIONID) {
        this.POSITIONID = POSITIONID;
    }


    public boolean productid(int id) {
        Log.e(TAG, "brand size : " + PID.size());
        int a = PID.size();
        if (PID.size() > 0) {
            for (int i = 0; i < a; i++) {
                if (id == PID.get(i)) {
                    PID.set(i, id);
                    return a == PID.size();
                }
            }
        }
        return false;
    }

    public String getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        FinalPrice = finalPrice;
    }

    public boolean isNewadd() {
        return newadd;
    }

    public void setNewadd(boolean newadd) {
        this.newadd = newadd;
    }

    public String getSavePrice() {
        return SavePrice;
    }

    public void setSavePrice(String savePrice) {
        SavePrice = savePrice;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getPackageid() {
        return Packageid;
    }

    public void setPackageid(String packageid) {
        Packageid = packageid;
    }

    public String getSalonorhome() {
        return Salonorhome;
    }

    public void setSalonorhome(String salonorhome) {
        Salonorhome = salonorhome;
    }

    public String getOwneraddress() {
        return Owneraddress;
    }

    public void setOwneraddress(String owneraddress) {
        Owneraddress = owneraddress;
    }



    public String getDogcatpackagename() {
        return dogcatpackagename;
    }

    public void setDogcatpackagename(String dogcatpackagename) {
        this.dogcatpackagename = dogcatpackagename;
    }


    public String getSalonorhomeradio() {
        return salonorhomeradio;
    }

    public void setSalonorhomeradio(String salonorhomeradio) {
        this.salonorhomeradio = salonorhomeradio;
    }

    public String getSRRRID() {
        return SRRRID;
    }

    public void setSRRRID(String SRRRID) {
        this.SRRRID = SRRRID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public HashMap<String, String> getAddonservices() {
        return Addonservices;
    }

    public void setAddonservices(String key, String value) {
        Addonservices.put(key, value);
    }


    public boolean removeAddonservices(String key) {
        if (Addonservices.containsKey(key)) {
            Addonservices.remove(key);
            return true;
        } else {

            return false;
        }

    }

    public HashMap<String, String> getAddonservicesprice() {
        return addonservicesprice;
    }

    public void setAddonservicesprice(String key, String value) {
        addonservicesprice.put(key, value);
    }


    public boolean removeAddonservicesprice(String key) {
        if (addonservicesprice.containsKey(key)) {
            addonservicesprice.remove(key);
            return true;
        } else {

            return false;
        }

    }
}
