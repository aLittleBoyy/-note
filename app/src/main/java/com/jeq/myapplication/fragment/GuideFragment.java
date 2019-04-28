package com.jeq.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jeq.myapplication.R;
import com.jeq.myapplication.activity.MainActivity;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.ZoomIndicator;
import com.zhengsr.viewpagerlib.view.GlideViewPager;

import java.util.ArrayList;

public class GuideFragment extends Fragment {


    private GlideViewPager guide;

    private ArrayList<Integer> imageViews;
    int[] images = {R.mipmap.title1, R.mipmap.title2, R.mipmap.title3};
    private ZoomIndicator indicator;
    private Button jump;
    private View inflate;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.activity_guide, container, false);
        initView();
        return inflate;
    }

    private void initView() {

        imageViews = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            imageViews.add(images[i]);
        }
        guide = inflate.findViewById(R.id.guide);
        indicator = inflate.findViewById(R.id.indictor);
        jump = inflate.findViewById(R.id.jump);
        jump.getBackground().setAlpha(100);
        PageBean bean = new PageBean.Builder<Integer>()
                .setDataObjects(imageViews)
                .setIndicator(indicator)
                .setOpenView(jump)
                .builder();
        guide.setPageListener(bean, R.layout.image_layout, new PageHelperListener<Integer>() {
            @Override
            public void getItemView(View view, Integer o) {
                ImageView imageView = view.findViewById(R.id.icon);
                imageView.setImageResource(o);
            }
        });

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
