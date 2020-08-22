package com.example.reg_login.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reg_login.R;
import com.example.reg_login.response.User;
import com.example.reg_login.retrofit.ApiClient;
import com.example.reg_login.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etName,etusername,etuserpassword;
    private Button btn_reg,btn_log,btn_read,btn_text;
    public static ApiInterface apiInterface;
    private static final String TAG = "MainActivity";
    private static final String SHARED_PREF_NAME = "MYPREFS";
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";



    SharedPreferences preferences;
    String name ;
    String user_name;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        etName=findViewById(R.id.txt_name);
        etusername=findViewById(R.id.txt_user_name);
        etuserpassword=findViewById(R.id.txt_password);

        btn_reg=findViewById(R.id.btn_register);
        btn_log=findViewById(R.id.btn_login);
        btn_read=findViewById(R.id.btn_read);
        btn_text=findViewById(R.id.btn_read_t);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performReg();
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(i);
            }
        });

    btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main4Activity.class);
                startActivity(i);
            }
        });




        isLoggedIn();

    }

    public void performReg()
    {
        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        name =     etName.getText().toString();
        user_name =etusername.getText().toString();
        password = etuserpassword.getText().toString();

        if(name.isEmpty()){
            etName.setError("Name is required");
            etName.requestFocus();
            return;
        }

        if(user_name.isEmpty()){
            etusername.setError("User_Name is required");
            etusername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etuserpassword.setError("Password is required");
            etuserpassword.requestFocus();
            return;
        }

        if(password.length()<6){
            etuserpassword.setError("Password should be atleast 6 character long");
            etuserpassword.requestFocus();
            return;
        }


        Call<User> call = ApiClient
                .getInstance()
                .getApiInterface()
                .performReg(name, user_name, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getresult().equals("ok"))
                {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(KEY_NAME,name);
                    editor.putString(KEY_USERNAME,user_name);
                    editor.putString(KEY_PASSWORD,password);
                    editor.commit();
                    Intent displayScreen = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(displayScreen);
                }

                else if (response.body().getresult().equals("exist"))
                {
                    Toast.makeText(MainActivity.this, "exist", Toast.LENGTH_LONG).show();
                }

                else if (response.body().getresult().equals("error"))
                {

                }
                Log.d(TAG,"OnCreate: name:"+name);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        etName .setText("");
        etusername.setText("");
        etuserpassword.setText("");
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            Intent i = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(i);
            finish();
            return true;
        }
        return false;
    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPrefManager g=new SharedPrefManager(this);
//        String theSavedUserName=  g.readUserNameFromPreference();
//        if(! theSavedUserName.equals(""))
//        {
//
//            //   tvGoToSignUp.setVisibility(View.INVISIBLE);
//            Intent i=new Intent(this,Main2Activity.class);
//            startActivity(i);
//            MainActivity.this.finish();
//        }
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(this, Main2Activity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

}
