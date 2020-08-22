package com.example.reg_login.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reg_login.R;
import com.example.reg_login.response.User;
import com.example.reg_login.response.Users;
import com.example.reg_login.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView RegText;
    private EditText UserName,UserPassword;
    private Button btn_login;
    private static final String SHARED_PREF_NAME = "my_shared_preff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RegText=findViewById(R.id.register_txt);
        UserName = findViewById(R.id.user_name);
        UserPassword = findViewById(R.id.user_pass);
        btn_login=findViewById(R.id.login_btn);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }

    private  void performLogin(){
        Users uu =new Users();
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String userName = UserName.getText().toString();
        String password = UserPassword.getText().toString();
//        Users u = new Users();
        Call<User> call = ApiClient
                .getInstance()
                .getApiInterface()
                .performaneUserLogin(userName,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getresult().equals("ok"))
                {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();



                    editor.putString("user_name",userName);
                    editor.putString("password",password);
                    editor.putString("name",null);
                    editor.commit();

                    Intent i=new Intent(getApplicationContext(),Main2Activity.class);

                    startActivity(i);


                }

                else if (response.body().getresult().equals("faild"))
                {
                    Toast.makeText(LoginActivity.this, "not Success", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        UserPassword.setText("");
        UserName.setText("");
    }


}
