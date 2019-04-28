package com.jeq.myapplication.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.jeq.myapplication.R;

public abstract class SingleActivity extends AppCompatActivity {

    protected abstract Fragment onCreateFragment();
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_guide_base);

        FragmentManager manager = getSupportFragmentManager();
        fragment = manager.findFragmentById(R.id.frag);

        if (fragment == null){
            fragment = onCreateFragment();
            manager.beginTransaction().add(R.id.frag, fragment).commit();
        }
    }


}
