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
import com.jeq.myapplication.inter.MyListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity implements MyListener {


    public static final String TAG = "SQL";
    private ViewPager pager;
    private FragmentManager manager;
    private ArrayList<MyData> list;
    private int position;
    private MyData myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        list = (ArrayList<MyData>) getIntent().getSerializableExtra("int");
        position = getIntent().getExtras().getInt("position");
        myData = list.get(position);
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
                //Log.d(TAG, "getItem: ==========" +name);
                return fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        pager.setCurrentItem(position);
    }


   /* @Override
    public void secContent(String info, int info2) {

    }

    @Override
    public void secContentOnly(String info) {
        Log.d(TAG, "secContentOnly: " + info);
        Intent intent = new Intent(this, MainActivity.class);
        Intent descibe = intent.putExtra("descibe", info);
        startActivity(intent);
    }*/

    @Override
    public void setContentData(MyData data) {
        Log.d(TAG, "detailedActivity----secContentData: " + data.getDescribe()+data.getName()+data.getId());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("des", data);
        startActivity(intent);


      /*  //Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("des", (Serializable) data);
        intent.putExtras(bundle);*/

    }
}
