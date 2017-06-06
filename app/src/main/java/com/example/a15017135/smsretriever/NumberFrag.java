package com.example.a15017135.smsretriever;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumberFrag extends Fragment {

    EditText etNum;
    Button btnRetrieveNum;
    TextView tvNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number, container, false);
        etNum = (EditText)view.findViewById(R.id.etNumber);
        btnRetrieveNum = (Button)view.findViewById(R.id.btnRetrieveNum);
        tvNum = (TextView)view.findViewById(R.id.tvNumber);

        return view;
    }

}
