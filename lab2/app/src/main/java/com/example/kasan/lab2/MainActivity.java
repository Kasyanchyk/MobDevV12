package com.example.kasan.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kasan.lab2.R;

public class MainActivity extends AppCompatActivity implements ContentFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String link) {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
        }
    }
}


