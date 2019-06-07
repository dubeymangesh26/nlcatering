package com.dotcom.nlcatering.Response.CustomerResponse;

import java.util.List;

public class CustomerLisrtEnquiryResponse {


    /**
     * success : true
     * Messege : Success
     * status : 200
     * response : [{"Enquiry_Id":109,"Customer_Id":32,"Order_Date":"2019-02-14T00:00:00","Type_catering":"Surinamese","Time":"11:00 Am            ","No_of_person":11,"Place_of_performance":"bihar","Keep_Worm":true,"With_staff":true,"Description":"jtyj","Cust_Name":"Mangesh Kumar Dubey","Email":"dubeymangesh26@gmail.com","MobileNo":"9967771694","Address":"Andheri East Mumbai India","City":"1","PinCode":"400063","Created_By":null,"Created_Date":null,"Modified_By":null,"Modified_Date":null,"Bid_Status":null,"MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg"},{"Enquiry_Id":103,"Customer_Id":32,"Order_Date":"2019-01-31T00:00:00","Type_catering":"Surinamese","Time":"11:00 Am            ","No_of_person":11,"Place_of_performance":"grtfxghxgd","Keep_Worm":true,"With_staff":true,"Description":"gdxbnxfgn sdgdfhf dfghbdf ","Cust_Name":"Mangesh Kumar Dubey","Email":"dubeymangesh26@gmail.com","MobileNo":"9967771694","Address":"Andheri East Mumbai India","City":"1","PinCode":"400063","Created_By":null,"Created_Date":null,"Modified_By":null,"Modified_Date":null,"Bid_Status":null,"MainMenuImg":"http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg"}]
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
         * Enquiry_Id : 109
         * Customer_Id : 32
         * Order_Date : 2019-02-14T00:00:00
         * Type_catering : Surinamese
         * Time : 11:00 Am
         * No_of_person : 11
         * Place_of_performance : bihar
         * Keep_Worm : true
         * With_staff : true
         * Description : jtyj
         * Cust_Name : Mangesh Kumar Dubey
         * Email : dubeymangesh26@gmail.com
         * MobileNo : 9967771694
         * Address : Andheri East Mumbai India
         * City : 1
         * PinCode : 400063
         * Created_By : null
         * Created_Date : null
         * Modified_By : null
         * Modified_Date : null
         * Bid_Status : null
         * MainMenuImg : http://worldindia.in/nlcateringapp/Images/FoodMenu/Surinamese-cuisine.jpg
         */

        private int Enquiry_Id;
        private int Customer_Id;
        private String Order_Date;
        private String Type_catering;
        private String Time;
        private int No_of_person;
        private String Place_of_performance;
        private boolean Keep_Worm;
        private boolean With_staff;
        private String Description;
        private String Cust_Name;
        private String Email;
        private String MobileNo;
        private String Address;
        private String City;
        private String PinCode;
        private Object Created_By;
        private Object Created_Date;
        private Object Modified_By;
        private Object Modified_Date;
        private Object Bid_Status;
        private String MainMenuImg;

        public int getEnquiry_Id() {
            return Enquiry_Id;
        }

        public void setEnquiry_Id(int Enquiry_Id) {
            this.Enquiry_Id = Enquiry_Id;
        }

        public int getCustomer_Id() {
            return Customer_Id;
        }

        public void setCustomer_Id(int Customer_Id) {
            this.Customer_Id = Customer_Id;
        }

        public String getOrder_Date() {
            return Order_Date;
        }

        public void setOrder_Date(String Order_Date) {
            this.Order_Date = Order_Date;
        }

        public String getType_catering() {
            return Type_catering;
        }

        public void setType_catering(String Type_catering) {
            this.Type_catering = Type_catering;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public int getNo_of_person() {
            return No_of_person;
        }

        public void setNo_of_person(int No_of_person) {
            this.No_of_person = No_of_person;
        }

        public String getPlace_of_performance() {
            return Place_of_performance;
        }

        public void setPlace_of_performance(String Place_of_performance) {
            this.Place_of_performance = Place_of_performance;
        }

        public boolean isKeep_Worm() {
            return Keep_Worm;
        }

        public void setKeep_Worm(boolean Keep_Worm) {
            this.Keep_Worm = Keep_Worm;
        }

        public boolean isWith_staff() {
            return With_staff;
        }

        public void setWith_staff(boolean With_staff) {
            this.With_staff = With_staff;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getCust_Name() {
            return Cust_Name;
        }

        public void setCust_Name(String Cust_Name) {
            this.Cust_Name = Cust_Name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String PinCode) {
            this.PinCode = PinCode;
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

        public Object getBid_Status() {
            return Bid_Status;
        }

        public void setBid_Status(Object Bid_Status) {
            this.Bid_Status = Bid_Status;
        }

        public String getMainMenuImg() {
            return MainMenuImg;
        }

        public void setMainMenuImg(String MainMenuImg) {
            this.MainMenuImg = MainMenuImg;
        }
    }
}
