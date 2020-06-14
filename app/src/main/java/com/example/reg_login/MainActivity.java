package com.example.reg_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivitylistener,WelFragment.OnLogoutListener {

    public static PrefConfig prefConfig;
    public static  ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if (savedInstanceState!=null)
            {
                return;
            }

            if (prefConfig.readLoginstatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new WelFragment()).commit();
            }
            else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performReg() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WelFragment()).commit();

    }

    @Override
    public void logoutperformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
    }
}
