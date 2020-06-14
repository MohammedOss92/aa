package com.example.reg_login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    private TextView RegText;
    private EditText UserName,UserPassword;
    private Button btn_login;
    OnLoginFormActivitylistener loginFormActivitylistener;

    public interface OnLoginFormActivitylistener
    {
        public void performReg();
        public void performLogin(String name);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_login, container, false);
        RegText=view.findViewById(R.id.register_txt);
        UserName = view.findViewById(R.id.user_name);
        UserPassword = view.findViewById(R.id.user_pass);
        btn_login=view.findViewById(R.id.login_btn);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });


        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivitylistener.performReg();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        loginFormActivitylistener = (OnLoginFormActivitylistener) activity;
    }

    private  void performLogin(){
        String userName = UserName.getText().toString();
        String Password = UserPassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performaneUserLogin(userName,Password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok"))
                {
                    loginFormActivitylistener.performLogin(response.body().getName());
                }

                else if (response.body().getResponse().equals("failed"))
                {
                    MainActivity.prefConfig.displayToast("Login Failed....");
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
