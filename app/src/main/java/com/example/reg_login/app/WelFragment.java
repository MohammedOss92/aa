package com.example.reg_login.app;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reg_login.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelFragment extends Fragment {

    private TextView textView;
    private Button btn_logout;
    OnLogoutListener logoutListener;
    public interface OnLogoutListener
    {
        public void logoutperformed();

    }

    public WelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wel, container, false);

        textView=view.findViewById(R.id.txt_name_info);
//        textView.setText("Welcome "+MainActivity.prefConfig.readName());
        btn_logout=view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutListener.logoutperformed();
            }
        });

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;
    }
}
