package com.dotcom.nlcatering.Response.CustomerResponse;

import java.util.List;

public class CustomerOrderListResponse {

    /**
     * success : true
     * Messege : Success
     * status : 200
     * response : [{"Order_Id":40,"Customer_Id":32,"Bidding_Id":null,"Cust_Name":null,"Address":"Andheri East Mumbai India","City":null,"Pincode":null,"Order_Date":"2019-02-14T00:00:00","Order_Type":"Surinamese","No_Of_person":11,"Amount":1500,"Payment_Status":"Success","Transaction_date":null,"Transaction_id":null,"Created_By":null,"Created_Date":null,"Modified_By":null,"Modified_Date":null,"Company_Name":"Dp","PhoneNo":"8828274070","compAddress":"ds","MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg","Time":"11:00 Am            "},{"Order_Id":41,"Customer_Id":32,"Bidding_Id":null,"Cust_Name":null,"Address":"Andheri East Mumbai India","City":null,"Pincode":null,"Order_Date":"2019-02-14T00:00:00","Order_Type":"Surinamese","No_Of_person":11,"Amount":14700,"Payment_Status":"Success","Transaction_date":null,"Transaction_id":null,"Created_By":null,"Created_Date":null,"Modified_By":null,"Modified_Date":null,"Company_Name":"Dubey Fast Food","PhoneNo":"8275237846","compAddress":"Varanasi","MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg","Time":"11:00 Am            "}]
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
         * Order_Id : 40
         * Customer_Id : 32
         * Bidding_Id : null
         * Cust_Name : null
         * Address : Andheri East Mumbai India
         * City : null
         * Pincode : null
         * Order_Date : 2019-02-14T00:00:00
         * Order_Type : Surinamese
         * No_Of_person : 11
         * Amount : 1500
         * Payment_Status : Success
         * Transaction_date : null
         * Transaction_id : null
         * Created_By : null
         * Created_Date : null
         * Modified_By : null
         * Modified_Date : null
         * Company_Name : Dp
         * PhoneNo : 8828274070
         * compAddress : ds
         * MainMenuImg : http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg
         * Time : 11:00 Am
         */

        private int Order_Id;
        private int Customer_Id;
        private Object Bidding_Id;
        private Object Cust_Name;
        private String Address;
        private Object City;
        private Object Pincode;
        private String Order_Date;
        private String Order_Type;
        private int No_Of_person;
        private int Amount;
        private String Payment_Status;
        private Object Transaction_date;
        private Object Transaction_id;
        private Object Created_By;
        private Object Created_Date;
        private Object Modified_By;
        private Object Modified_Date;
        private String Company_Name;
        private String PhoneNo;
        private String compAddress;
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

        public Object getBidding_Id() {
            return Bidding_Id;
        }

        public void setBidding_Id(Object Bidding_Id) {
            this.Bidding_Id = Bidding_Id;
        }

        public Object getCust_Name() {
            return Cust_Name;
        }

        public void setCust_Name(Object Cust_Name) {
            this.Cust_Name = Cust_Name;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public Object getCity() {
            return City;
        }

        public void setCity(Object City) {
            this.City = City;
        }

        public Object getPincode() {
            return Pincode;
        }

        public void setPincode(Object Pincode) {
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

        public Object getTransaction_date() {
            return Transaction_date;
        }

        public void setTransaction_date(Object Transaction_date) {
            this.Transaction_date = Transaction_date;
        }

        public Object getTransaction_id() {
            return Transaction_id;
        }

        public void setTransaction_id(Object Transaction_id) {
            this.Transaction_id = Transaction_id;
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

        public String getPhoneNo() {
            return PhoneNo;
        }

        public void setPhoneNo(String PhoneNo) {
            this.PhoneNo = PhoneNo;
        }

        public String getCompAddress() {
            return compAddress;
        }

        public void setCompAddress(String compAddress) {
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
