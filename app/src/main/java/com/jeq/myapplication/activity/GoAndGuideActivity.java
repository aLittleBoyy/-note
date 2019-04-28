package com.jeq.myapplication.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import com.jeq.myapplication.fragment.GoFragment;
import com.jeq.myapplication.fragment.GuideFragment;
import com.jeq.myapplication.utils.SingleActivity;

public class GoAndGuideActivity extends SingleActivity {
    @Override
    protected Fragment onCreateFragment() {

        GoFragment goFragment = new GoFragment();
        GuideFragment guideFragment = new GuideFragment();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean key = sharedPreferences.getBoolean("key", true);
        if (key){
            sharedPreferences.edit().putBoolean("key", false).commit();
            return goFragment;
        }else {
            return guideFragment;
        }
    }

}
