package com.dotcom.nlcatering.Request.CatererRequest;

public class CatererBidRequest {

    /**
     * Enquiry_Id : 110
     * Caterers_Id : 2
     * Order_Type : Turkish
     * Qty : 7
     * Total_Amount : 1500
     * Description : 109 EQ Order
     */

    private int Enquiry_Id;
    private int Caterers_Id;
    private String Order_Type;
    private int Qty;
    private int Total_Amount;
    private String Description;

    public int getEnquiry_Id() {
        return Enquiry_Id;
    }

    public void setEnquiry_Id(int Enquiry_Id) {
        this.Enquiry_Id = Enquiry_Id;
    }

    public int getCaterers_Id() {
        return Caterers_Id;
    }

    public void setCaterers_Id(int Caterers_Id) {
        this.Caterers_Id = Caterers_Id;
    }

    public String getOrder_Type() {
        return Order_Type;
    }

    public void setOrder_Type(String Order_Type) {
        this.Order_Type = Order_Type;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    public int getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(int Total_Amount) {
        this.Total_Amount = Total_Amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
