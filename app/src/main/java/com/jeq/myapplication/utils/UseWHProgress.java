package com.jeq.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.jeq.myapplication.activity.MainActivity;

/**
 * 倒计时五秒，初次使用引导页
 */
public class UseWHProgress {

    public static com.jeq.myapplication.view.progressWH  progressWH;
    public FragmentActivity mContext;
    public int total = 5;
    public int current = 0;

    public UseWHProgress(com.jeq.myapplication.view.progressWH progress, Context context) {
        this.progressWH = progress;
        this.mContext = (FragmentActivity) context;
    }

    public void use(Context mContext){
        new Thread(new ProgressRunable()).start();


    }
    private class ProgressRunable implements Runnable {
        @Override
        public void run() {
            while (current<total){
                current+=1;
                progressWH.setProgress(current);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (current == total){
                    Intent intent = new Intent(mContext, MainActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.getApplicationContext().startActivity(intent);
                    Activity activity = (Activity) mContext;
                    activity.finish();

                }
            }


        }
    }
}
