package com.jeq.myapplication.inter;


import com.jeq.myapplication.data.MyData;

/**
 * The interface My listener.
 * fragment 向 activity传值
 */
public interface MyListener{
    /**
     * Sec content.
     *
     * @param info  the info
     * @param info2 the info 2
     */
   /* public void secContent(String info, int info2);
    public void secContentOnly(String info);*/
    public void setContentData(MyData data);
}