package com.example.reg_login.retrofit;

import com.example.reg_login.response.Data;
import com.example.reg_login.response.User;
import com.example.reg_login.response.DataResponse;
import com.example.reg_login.response.Users;
import com.example.reg_login.response.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("reg.php")
    Call<User> performReg
            (@Query("name") String Name,
             @Query("user_name") String UserName,
             @Query("user_password") String password);

    @GET("login.php")
    Call<User> performaneUserLogin(
            @Query("user_name") String UserName,
           @Query("user_password") String password);

    /*RecyclerView*/
    @GET("read_single.php/{user_name}")
    Call<DataResponse> read_user(
            @Query("user_name") String UserName

    );

    /*TextView*/
    @GET("read_single.php/{user_name}")
    Call<Data> read_use(
            @Query("user_name") String UserName

    );


    /*RecyclerView*/
    @GET("read.php")
    Call<UsersResponse> read();

    /*TextView*/
    @GET("read.php")
    Call<Users> reads();




}
