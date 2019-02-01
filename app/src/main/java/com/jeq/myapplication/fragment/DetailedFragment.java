package com.jeq.myapplication.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeq.myapplication.R;
import com.jeq.myapplication.data.MyData;

import java.util.ArrayList;

public class DetailedFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed, null, false);
        textView = view.findViewById(R.id.textss);
        MyData list = (MyData) getArguments().getSerializable("int");
        textView.setText(list.getName());
        return view;
    }


    public static DetailedFragment newInstance(MyData data) {
        Bundle args = new Bundle();
        args.putSerializable("int", data);
        DetailedFragment fragment = new DetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
