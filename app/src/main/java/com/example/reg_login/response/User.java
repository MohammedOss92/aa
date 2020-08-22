package com.example.reg_login.response;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("result")
    private String result;
    @SerializedName("name")
    private String Name;
//    private String Users;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


    //    public String getUsers() {
//        return Users;
//    }
//
//    public void setUsers(String users) {
//        Users = users;
//    }



    public String getresult() {
        return result;
    }

    public String getName() {
        return Name;
    }
}
