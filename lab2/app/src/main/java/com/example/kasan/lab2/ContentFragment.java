package com.example.kasan.lab2;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

import com.example.kasan.lab2.R;


public class ContentFragment extends Fragment {
    String[] data = {"Все","Тарілки", "Кружки", "Стакани", "Миски", "Кастрюлі"};
    int n=0;

    CheckBox[] ch = new CheckBox[8];


    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ch[0]=view.findViewById(R.id.checkBox);
        ch[1]=view.findViewById(R.id.checkBox2);
        ch[2]=view.findViewById(R.id.checkBox3);
        ch[3]=view.findViewById(R.id.checkBox4);
        ch[4]=view.findViewById(R.id.checkBox5);
        ch[5]=view.findViewById(R.id.checkBox6);
        ch[6]=view.findViewById(R.id.checkBox7);
        ch[7]=view.findViewById(R.id.checkBox8);

        Button button = view.findViewById(R.id.button);
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                n=position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String price="\nЦіна: ";
                String firms="\nВирообник: ";

                if(!(ch[0].isChecked()||ch[1].isChecked()||ch[2].isChecked()||ch[3].isChecked()))
                    firms="";
                if(!(ch[4].isChecked()||ch[5].isChecked()||ch[6].isChecked()||ch[7].isChecked()))
                    price="";

                for(int i=0;i<ch.length;i++)
                {
                    if(ch[i].isChecked()) {
                        if(i<4)
                            firms += ch[i].getText() + "; ";
                        else
                            price+=ch[i].getText()+"; ";
                    }
                }
                updateDetail("Вибрано: " + data[n]+firms+price);
                //Toast.makeText(getContext(), "Вибрано: " + data[n]+firms+price, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    public OnFragmentInteractionListener mListener;
    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }
    public void updateDetail(String curDate) {
        // генерируем некоторые данные

        // Посылаем данные Activity
        mListener.onFragmentInteraction(curDate);
    }
    @Override
    public void onAttach(Activity MainActivity) {
        super.onAttach(MainActivity);
        mListener = (OnFragmentInteractionListener) MainActivity;}
}
