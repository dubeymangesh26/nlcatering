package com.dotcom.nlcatering.Response.CustomerResponse;

import java.util.List;

public class QuoatationCityCatererTypeResponse {


    /**
     * success : true
     * messege : success
     * status : 200
     * response : {"City":[{"Id":1,"Name":"Mumbai    "},{"Id":2,"Name":"Delhi     "},{"Id":3,"Name":"Bangalore "},{"Id":4,"Name":"Hyderabad "},{"Id":5,"Name":"Ahmedabad "},{"Id":6,"Name":"Chennai   "},{"Id":7,"Name":"Thane     "}],"FoodMenu":[{"Cat_Id":1,"Cat_Name":"Netherlands Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Netherlands_Food-1.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":2,"Cat_Name":"Surinamese Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Surinamese_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":3,"Cat_Name":"Turkish Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Turkish_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":4,"Cat_Name":"Moroccan Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Moroccan_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":5,"Cat_Name":"Different","Photo_Path":"http://worldindia.in/nlcateringappItalian_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null}],"SubFoodMenu":null}
     */

    private boolean success;
    private String messege;
    private int status;
    private ResponseBean response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * City : [{"Id":1,"Name":"Mumbai    "},{"Id":2,"Name":"Delhi     "},{"Id":3,"Name":"Bangalore "},{"Id":4,"Name":"Hyderabad "},{"Id":5,"Name":"Ahmedabad "},{"Id":6,"Name":"Chennai   "},{"Id":7,"Name":"Thane     "}]
         * FoodMenu : [{"Cat_Id":1,"Cat_Name":"Netherlands Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Netherlands_Food-1.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":2,"Cat_Name":"Surinamese Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Surinamese_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":3,"Cat_Name":"Turkish Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Turkish_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":4,"Cat_Name":"Moroccan Food","Photo_Path":"http://worldindia.in/nlcateringapp/Images/FoodMenu/SubMenu/Moroccan_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null},{"Cat_Id":5,"Cat_Name":"Different","Photo_Path":"http://worldindia.in/nlcateringappItalian_Food.jpg","Status":1,"Sub_Id":0,"SubCat_photo":null,"SubCat_Name":null}]
         * SubFoodMenu : null
         */

        private Object SubFoodMenu;
        private List<CityBean> City;
        private List<FoodMenuBean> FoodMenu;

        public Object getSubFoodMenu() {
            return SubFoodMenu;
        }

        public void setSubFoodMenu(Object SubFoodMenu) {
            this.SubFoodMenu = SubFoodMenu;
        }

        public List<CityBean> getCity() {
            return City;
        }

        public void setCity(List<CityBean> City) {
            this.City = City;
        }

        public List<FoodMenuBean> getFoodMenu() {
            return FoodMenu;
        }

        public void setFoodMenu(List<FoodMenuBean> FoodMenu) {
            this.FoodMenu = FoodMenu;
        }

        public static class CityBean {
            /**
             * Id : 1
             * Name : Mumbai
             */

            private int Id;
            private String Name;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }
        }

        public static class FoodMenuBean {
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
}
