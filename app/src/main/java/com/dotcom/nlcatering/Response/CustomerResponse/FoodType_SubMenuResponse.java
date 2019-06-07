package com.dotcom.nlcatering.Response.CustomerResponse;

import java.util.List;

public class FoodType_SubMenuResponse {


    /**
     * success : true
     * Messege : Success
     * status : 200
     * response : [{"Sub_Id":2,"Cat_Id":2,"SubCat_photo":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/OLALEKAN ODUNTAN.jpg","SubCat_Name":"Surinamese item 1 ","CategoryName":"Surinamese"},{"Sub_Id":43,"Cat_Id":2,"SubCat_photo":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Surinaams-eten.jpg","SubCat_Name":"Surinaams-eten","CategoryName":"Surinamese"}]
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
         * Sub_Id : 2
         * Cat_Id : 2
         * SubCat_photo : http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/OLALEKAN ODUNTAN.jpg
         * SubCat_Name : Surinamese item 1
         * CategoryName : Surinamese
         */

        private int Sub_Id;
        private int Cat_Id;
        private String SubCat_photo;
        private String SubCat_Name;
        private String CategoryName;

        public int getSub_Id() {
            return Sub_Id;
        }

        public void setSub_Id(int Sub_Id) {
            this.Sub_Id = Sub_Id;
        }

        public int getCat_Id() {
            return Cat_Id;
        }

        public void setCat_Id(int Cat_Id) {
            this.Cat_Id = Cat_Id;
        }

        public String getSubCat_photo() {
            return SubCat_photo;
        }

        public void setSubCat_photo(String SubCat_photo) {
            this.SubCat_photo = SubCat_photo;
        }

        public String getSubCat_Name() {
            return SubCat_Name;
        }

        public void setSubCat_Name(String SubCat_Name) {
            this.SubCat_Name = SubCat_Name;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }
    }
}
