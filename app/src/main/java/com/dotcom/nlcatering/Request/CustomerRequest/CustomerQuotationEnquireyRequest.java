package com.dotcom.nlcatering.Request.CustomerRequest;

public class CustomerQuotationEnquireyRequest {


    /**
     * Customer_Id : 32
     * Order_Date : 2019-02-02T13:15:12.346393+05:30
     * Type_catering : Surinamese
     * Time : 12:00Am
     * No_of_person : 12
     * Place_of_performance : hi
     * Keep_Worm : true
     * With_staff : true
     * Description : some
     * Created_By : 1
     * Created_Date : 2019-02-02T13:15:12.346393+05:30
     * Modified_By : 1
     * Modified_Date : 2019-02-02T13:15:12.346393+05:30
     */

    private int Customer_Id;
    private String Order_Date;
    private String Type_catering;
    private String Time;
    private int No_of_person;
    private String Place_of_performance;
    private boolean Keep_Worm;
    private boolean With_staff;
    private String Description;
    private int Created_By;
    private String Created_Date;
    private int Modified_By;
    private String Modified_Date;

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

    public int getModified_By() {
        return Modified_By;
    }

    public void setModified_By(int Modified_By) {
        this.Modified_By = Modified_By;
    }

    public String getModified_Date() {
        return Modified_Date;
    }

    public void setModified_Date(String Modified_Date) {
        this.Modified_Date = Modified_Date;
    }
}
