package com.jeq.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeq.myapplication.R;
import com.jeq.myapplication.holder.MyHolder;
import com.jeq.myapplication.data.MyData;

import java.util.List;

/**
 * The type My adaper.
 */
public class MyAdaptper extends RecyclerView.Adapter<MyHolder> {


    /**
     * The Sdata.
     */
    List<MyData> sdata;
    private View view;
    private RecyclerOnItemClickListener mrecyclerOnItemClickListener;
    /**
     * Instantiates a new My adaptper.
     *
     * @param data the data
     */
    public MyAdaptper(List<MyData> data) {
        this.sdata = data;
    }

    public void setRecyclerViewOnclick(RecyclerOnItemClickListener recyclerOnItemClickListener){
            this.mrecyclerOnItemClickListener = recyclerOnItemClickListener;

    }
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        MyData data = sdata.get(i);
        myHolder.bind(data);
        /**
         * 条目点击
         */
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mrecyclerOnItemClickListener.onClickListener(myHolder, v, i);
            }
        });
        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mrecyclerOnItemClickListener.onLongClickListener(myHolder, v, i);
                return false;
            }
        });
   }

    @Override
    public int getItemCount() {
            return sdata.size();
    }

}
