package com.dotcom.nlcatering.Response.CatererResponse;

import java.util.List;

public class CatererOrderListResponse {


    /**
     * success : true
     * Messege : Success
     * status : 200
     * response : [{"Order_Id":36,"Customer_Id":1,"Bidding_Id":57,"Cust_Name":"Swapnali Thakur","Address":"thane","City":2,"Pincode":"400606","Order_Date":"2019-02-16T00:00:00","Order_Type":"Surinamese","No_Of_person":10,"Amount":1500,"Payment_Status":"Success","Transaction_date":"2019-02-01T12:13:43.98","Transaction_id":"tr_3jcnqTDbGV","Created_By":1,"Created_Date":"2019-02-01T12:13:43.337","Modified_By":null,"Modified_Date":null,"Company_Name":"8828274070","PhoneNo":null,"compAddress":null,"MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg","Time":"10:00 PM            "},{"Order_Id":35,"Customer_Id":1,"Bidding_Id":35,"Cust_Name":"Swapnali Thakur","Address":"thane","City":2,"Pincode":"400606","Order_Date":"2019-01-24T00:00:00","Order_Type":"Surinamese","No_Of_person":20,"Amount":2200,"Payment_Status":"Success","Transaction_date":"2019-02-23T11:42:00.44","Transaction_id":"tr_CzFeVHUq7K","Created_By":1,"Created_Date":"2019-01-23T11:42:00.44","Modified_By":null,"Modified_Date":null,"Company_Name":"8828274070","PhoneNo":null,"compAddress":null,"MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg","Time":"12:00 Pm            "},{"Order_Id":34,"Customer_Id":1,"Bidding_Id":34,"Cust_Name":"Swapnali Thakur","Address":"thane","City":2,"Pincode":"400606","Order_Date":"2019-01-25T00:00:00","Order_Type":"Surinamese","No_Of_person":10,"Amount":2100,"Payment_Status":"Success","Transaction_date":"2019-01-23T11:37:29.127","Transaction_id":"tr_j8T8kBAmRe","Created_By":1,"Created_Date":"2019-01-23T11:37:28.033","Modified_By":null,"Modified_Date":null,"Company_Name":"8828274070","PhoneNo":null,"compAddress":null,"MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg","Time":"11:00 Pm            "}]
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
         * Order_Id : 36
         * Customer_Id : 1
         * Bidding_Id : 57
         * Cust_Name : Swapnali Thakur
         * Address : thane
         * City : 2
         * Pincode : 400606
         * Order_Date : 2019-02-16T00:00:00
         * Order_Type : Surinamese
         * No_Of_person : 10
         * Amount : 1500
         * Payment_Status : Success
         * Transaction_date : 2019-02-01T12:13:43.98
         * Transaction_id : tr_3jcnqTDbGV
         * Created_By : 1
         * Created_Date : 2019-02-01T12:13:43.337
         * Modified_By : null
         * Modified_Date : null
         * Company_Name : 8828274070
         * PhoneNo : null
         * compAddress : null
         * MainMenuImg : http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg
         * Time : 10:00 PM
         */

        private int Order_Id;
        private int Customer_Id;
        private int Bidding_Id;
        private String Cust_Name;
        private String Address;
        private int City;
        private String Pincode;
        private String Order_Date;
        private String Order_Type;
        private int No_Of_person;
        private int Amount;
        private String Payment_Status;
        private String Transaction_date;
        private String Transaction_id;
        private int Created_By;
        private String Created_Date;
        private Object Modified_By;
        private Object Modified_Date;
        private String Company_Name;
        private Object PhoneNo;
        private Object compAddress;
        private String MainMenuImg;
        private String Time;

        public int getOrder_Id() {
            return Order_Id;
        }

        public void setOrder_Id(int Order_Id) {
            this.Order_Id = Order_Id;
        }

        public int getCustomer_Id() {
            return Customer_Id;
        }

        public void setCustomer_Id(int Customer_Id) {
            this.Customer_Id = Customer_Id;
        }

        public int getBidding_Id() {
            return Bidding_Id;
        }

        public void setBidding_Id(int Bidding_Id) {
            this.Bidding_Id = Bidding_Id;
        }

        public String getCust_Name() {
            return Cust_Name;
        }

        public void setCust_Name(String Cust_Name) {
            this.Cust_Name = Cust_Name;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public int getCity() {
            return City;
        }

        public void setCity(int City) {
            this.City = City;
        }

        public String getPincode() {
            return Pincode;
        }

        public void setPincode(String Pincode) {
            this.Pincode = Pincode;
        }

        public String getOrder_Date() {
            return Order_Date;
        }

        public void setOrder_Date(String Order_Date) {
            this.Order_Date = Order_Date;
        }

        public String getOrder_Type() {
            return Order_Type;
        }

        public void setOrder_Type(String Order_Type) {
            this.Order_Type = Order_Type;
        }

        public int getNo_Of_person() {
            return No_Of_person;
        }

        public void setNo_Of_person(int No_Of_person) {
            this.No_Of_person = No_Of_person;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public String getPayment_Status() {
            return Payment_Status;
        }

        public void setPayment_Status(String Payment_Status) {
            this.Payment_Status = Payment_Status;
        }

        public String getTransaction_date() {
            return Transaction_date;
        }

        public void setTransaction_date(String Transaction_date) {
            this.Transaction_date = Transaction_date;
        }

        public String getTransaction_id() {
            return Transaction_id;
        }

        public void setTransaction_id(String Transaction_id) {
            this.Transaction_id = Transaction_id;
        }

        public int getCreated_By() {
            return Created_By;
        }

        public void setCreated_By(int Created_By) {
            this.Created_By = Created_By;
        }

        public String getCreated_Date() {
            return Created_Date;
        }

        public void setCreated_Date(String Created_Date) {
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

        public Object getPhoneNo() {
            return PhoneNo;
        }

        public void setPhoneNo(Object PhoneNo) {
            this.PhoneNo = PhoneNo;
        }

        public Object getCompAddress() {
            return compAddress;
        }

        public void setCompAddress(Object compAddress) {
            this.compAddress = compAddress;
        }

        public String getMainMenuImg() {
            return MainMenuImg;
        }

        public void setMainMenuImg(String MainMenuImg) {
            this.MainMenuImg = MainMenuImg;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }
    }
}
