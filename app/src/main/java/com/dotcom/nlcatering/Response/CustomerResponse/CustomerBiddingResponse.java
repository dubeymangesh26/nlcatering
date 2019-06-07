package com.dotcom.nlcatering.Response.CustomerResponse;

import java.util.List;

public class CustomerBiddingResponse {


    /**
     * success : true
     * Messege : Success
     * status : 200
     * response : [{"Bidding_Id":47,"Enquiry_Id":109,"Caterers_Id":2,"Rate":null,"Order_Type":"Surinamese","Qty":11,"Total_Amount":1200,"Description":"mangesh 31 jan","Created_By":null,"Created_Date":null,"Modified_By":null,"Modified_Date":null,"Company_Name":"Dot com","Address":null,"Telephone":"8635363353","MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg"}]
     */

    private boolean success;
    private String Messege;
    private int status;
    private List<ResponseBean> response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessege() {
        return Messege;
    }

    public void setMessege(String Messege) {
        this.Messege = Messege;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * Bidding_Id : 47
         * Enquiry_Id : 109
         * Caterers_Id : 2
         * Rate : null
         * Order_Type : Surinamese
         * Qty : 11
         * Total_Amount : 1200
         * Description : mangesh 31 jan
         * Created_By : null
         * Created_Date : null
         * Modified_By : null
         * Modified_Date : null
         * Company_Name : Dot com
         * Address : null
         * Telephone : 8635363353
         * MainMenuImg : http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg
         */

        private int Bidding_Id;
        private int Enquiry_Id;
        private int Caterers_Id;
        private Object Rate;
        private String Order_Type;
        private int Qty;
        private int Total_Amount;
        private String Description;
        private Object Created_By;
        private Object Created_Date;
        private Object Modified_By;
        private Object Modified_Date;
        private String Company_Name;
        private Object Address;
        private String Telephone;
        private String MainMenuImg;

        public int getBidding_Id() {
            return Bidding_Id;
        }

        public void setBidding_Id(int Bidding_Id) {
            this.Bidding_Id = Bidding_Id;
        }

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

        public Object getRate() {
            return Rate;
        }

        public void setRate(Object Rate) {
            this.Rate = Rate;
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

        public Object getCreated_By() {
            return Created_By;
        }

        public void setCreated_By(Object Created_By) {
            this.Created_By = Created_By;
        }

        public Object getCreated_Date() {
            return Created_Date;
        }

        public void setCreated_Date(Object Created_Date) {
            this.Created_Date = Created_Date;
        }

        public Object getModified_By() {
            return Modified_By;
        }

        public void setModified_By(Object Modified_By) {
            this.Modified_By = Modified_By;
        }

        public Object getModified_Date() {
            return Modified_Date;
        }

        public void setModified_Date(Object Modified_Date) {
            this.Modified_Date = Modified_Date;
        }

        public String getCompany_Name() {
            return Company_Name;
        }

        public void setCompany_Name(String Company_Name) {
            this.Company_Name = Company_Name;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public String getTelephone() {
            return Telephone;
        }

        public void setTelephone(String Telephone) {
            this.Telephone = Telephone;
        }

        public String getMainMenuImg() {
            return MainMenuImg;
        }

        public void setMainMenuImg(String MainMenuImg) {
            this.MainMenuImg = MainMenuImg;
        }
    }
}
