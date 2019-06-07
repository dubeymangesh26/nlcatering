package com.dotcom.nlcatering.Response.CustomerResponse;

import java.util.List;

public class CustomerMainActivityResponse {


    /**
     * success : true
     * Messege : Success
     * status : 200
     * response : [{"Cat_Id":1,"Cat_Name":"Netherlands Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Netherlands_Food-1.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":2,"Cat_Name":"Surinamese Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Surinamese_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":3,"Cat_Name":"Turkish Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Turkish_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":4,"Cat_Name":"Moroccan Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Moroccan_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":5,"Cat_Name":"Different","Photo_Path":"http://worldindia.in/nlcateringappItalian_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null}]
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
         * Cat_Id : 1
         * Cat_Name : Netherlands Food
         * Photo_Path : http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Netherlands_Food-1.jpg
         * Status : 1
         * Sub_Id : 0
         * SubCat_photo : null
         * SubCat_Name : null
         */

        private int Cat_Id;
        private String Cat_Name;
        private String Photo_Path;
        private int Status;
        private int Sub_Id;
        private Object SubCat_photo;
        private Object SubCat_Name;

        public int getCat_Id() {
            return Cat_Id;
        }

        public void setCat_Id(int Cat_Id) {
            this.Cat_Id = Cat_Id;
        }

        public String getCat_Name() {
            return Cat_Name;
        }

        public void setCat_Name(String Cat_Name) {
            this.Cat_Name = Cat_Name;
        }

        public String getPhoto_Path() {
            return Photo_Path;
        }

        public void setPhoto_Path(String Photo_Path) {
            this.Photo_Path = Photo_Path;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getSub_Id() {
            return Sub_Id;
        }

        public void setSub_Id(int Sub_Id) {
            this.Sub_Id = Sub_Id;
        }

        public Object getSubCat_photo() {
            return SubCat_photo;
        }

        public void setSubCat_photo(Object SubCat_photo) {
            this.SubCat_photo = SubCat_photo;
        }

        public Object getSubCat_Name() {
            return SubCat_Name;
        }

        public void setSubCat_Name(Object SubCat_Name) {
            this.SubCat_Name = SubCat_Name;
        }
    }
}
