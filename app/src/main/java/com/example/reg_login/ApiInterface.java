package com.example.reg_login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("reg.php")
    Call<User> performReg(@Query("name") String Name,@Query("user_name") String UserName,@Query("user_password") String password);

    @GET("login.php")
    Call<User> performaneUserLogin(@Query("user_name") String UserName,@Query("user_password") String password);


}
