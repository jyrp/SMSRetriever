package com.example.a15017135.smsretriever;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class WordFrag extends Fragment {

    EditText etWord;
    Button btnRetrieveWord;
    TextView tvWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        etWord = (EditText)view.findViewById(R.id.etWord);
        btnRetrieveWord = (Button)view.findViewById(R.id.btnRetrieveWord);
        tvWord = (TextView)view.findViewById(R.id.tvWord);

        return view;
    }

}
