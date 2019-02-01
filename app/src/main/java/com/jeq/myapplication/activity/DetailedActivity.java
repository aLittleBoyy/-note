package com.jeq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jeq.myapplication.R;
import com.jeq.myapplication.data.MyData;
import com.jeq.myapplication.fragment.DetailedFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {


    public static final String TAG = "SQL";
    private ViewPager pager;
    private FragmentManager manager;
    private ArrayList<MyData> list;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        list = (ArrayList<MyData>) getIntent().getSerializableExtra("int");
        position = getIntent().getExtras().getInt("position");
        initView();
    }

    private void initView() {
        pager = findViewById(R.id.pager);
        manager = getSupportFragmentManager();
        pager.setAdapter(new FragmentStatePagerAdapter(manager) {

            @Override
            public Fragment getItem(int i) {

                String name = list.get(i).getName();
                DetailedFragment fragment = DetailedFragment.newInstance(list.get(i));
                Log.d(TAG, "getItem: ==========" +name);
                return fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        pager.setCurrentItem(position);
    }


}
