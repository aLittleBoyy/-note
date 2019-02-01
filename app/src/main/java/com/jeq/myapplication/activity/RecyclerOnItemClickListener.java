package com.jeq.myapplication.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

interface RecyclerOnItemClickListener {
    void onClickListener(RecyclerView.ViewHolder holder, View view, int position);
    void onLongClickListener(RecyclerView.ViewHolder holder, View view, int position);
}
