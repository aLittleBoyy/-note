package com.jeq.myapplication.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jeq.myapplication.R;
import com.jeq.myapplication.activity.MainActivity;


public class GoFragment extends Fragment {

    private View inflate;
    public static com.jeq.myapplication.view.progressWH progressWH;
    public int total = 5;
    public int current = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.activity_go, container, false);
        initView();
        return inflate;
    }

    private void initView() {
        progressWH = inflate.findViewById(R.id.progress_WH);
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
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }
            }
        }
    }

}
