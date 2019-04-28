package com.jeq.myapplication.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeq.myapplication.R;
import com.jeq.myapplication.data.MyData;

/**
 * The type My holder.
 */
public class MyHolder extends RecyclerView.ViewHolder  {


    private final TextView name;
    private final TextView age;
    private MyData hdata;

    /**
     * Instantiates a new My holder.
     *
     * @param itemView the item view
     */
    public MyHolder(@NonNull final View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        age = itemView.findViewById(R.id.age);

    }

    public void bind(MyData data){
        hdata = data;
       /* Log.d(TAG, "bind: "+ hdata.getName());
        Log.d(TAG, "bind: " + hdata.getAge());*/
        name.setText(hdata.getName());
        //int age = hdata.getAge();
        /**
         * int 转 String
         */
        age.setText(String.valueOf(hdata.getAge()));
    }


}
