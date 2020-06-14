package com.example.reg_login;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText Name,username,userpassword;
    private Button btn_reg;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Name=view.findViewById(R.id.txt_name);
        username=view.findViewById(R.id.txt_user_name);
        userpassword=view.findViewById(R.id.txt_password);
        btn_reg=view.findViewById(R.id.btn_register);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performReg();
            }
        });

        return view;

    }

    public void performReg()
    {
        String name =       Name.getText().toString();
        String user_name = username.getText().toString();
        String password = userpassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performReg(name,user_name,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registeration Success .....");
                }

                else if (response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("User already exist .....");
                }

                else if (response.body().getResponse().equals("error"))
                    {
                     MainActivity.prefConfig.displayToast("Something went wrong  .....");
                    }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Name .setText("");
        username.setText("");
        userpassword.setText("");
    }

}
