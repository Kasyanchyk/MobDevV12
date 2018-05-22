package com.example.kasan.lab3;

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
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity {

    String[] data = {"Все","Тарілки", "Кружки", "Стакани", "Миски", "Кастрюлі"};
    int n=0;
    CheckBox[] ch = new CheckBox[8];

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS messages (mess TEXT)");

        super.onCreate(savedInstanceState);
        setContentView(com.example.kasan.lab3.R.layout.activity_main);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(com.example.kasan.lab3.R.id.spinner);
        spinner.setAdapter(adapter);

        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
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

        Button button = findViewById(com.example.kasan.lab3.R.id.button);
        Button button_get_data = findViewById(com.example.kasan.lab3.R.id.button_get_data);

        button_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //переходим с первой на вторую активность
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS messages (mess TEXT)");

                ch[0]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox);
                ch[1]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox2);
                ch[2]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox3);
                ch[3]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox4);
                ch[4]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox5);
                ch[5]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox6);
                ch[6]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox7);
                ch[7]=(CheckBox)findViewById(com.example.kasan.lab3.R.id.checkBox8);
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
                Toast.makeText(getApplicationContext(), "Вибрано: " + data[n]+firms+price, Toast.LENGTH_LONG).show();
                db.execSQL("INSERT INTO messages VALUES ('"+"Вибрано: " + data[n]+firms+price+ "');");
            }
        });
    }
}


