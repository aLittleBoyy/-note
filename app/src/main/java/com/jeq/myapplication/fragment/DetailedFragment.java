package com.jeq.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import com.jeq.myapplication.R;
import com.jeq.myapplication.data.MyData;
import com.jeq.myapplication.inter.MyListener;
import com.jeq.myapplication.utils.DB;
import com.jeq.myapplication.utils.SQLiteDao;

import java.util.ArrayList;
import java.util.List;


public class DetailedFragment extends Fragment implements View.OnClickListener {

    private TextView textView;
    private View view;
    private EditText descibe;
    private Button save;
    public static final String TAG = "SQL";
    private Editable descibeText;
    private MyListener listener;
    private String trim;
    private MyData list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detailed, null, false);
        list = (MyData) getArguments().getSerializable("int");
        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (MyListener) getActivity();

    }

    private void initView() {
        textView = view.findViewById(R.id.textss);
        descibe = view.findViewById(R.id.descibe);
        save = view.findViewById(R.id.save_detil);
        List<MyData> data  = new ArrayList<>();
        SQLiteDao.showDataList(DB.dbtabase(getActivity()), data);

        /**
         * describe 描述文本框，先判空，如果为空提示输入
         * 不为空可编辑
         * 保存先传到activity, 再传到 MaainActivity 保存数据库
         */
      /*  descibe.setText(list.getDescribe().trim());*/
        descibeText = descibe.getText();
        isNull(descibeText);
        save.setOnClickListener(this);


        int id = list.getId();
        textView.setText(list.getName() + "&&" + id);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        trim = descibeText.toString().trim();
        if (id == R.id.save_detil){
            Log.d(TAG, "DetailedFragment----onClick: save" + descibeText.toString().trim());
           /* listener.secContentOnly(trim);
            listener.secContentData(list);*/
           list.setDescribe(trim);
           SQLiteDao.insert_detailed(list, DB.dbtabase(getActivity()));
           listener.setContentData(list);
    }

    }

    void isNull(Editable text){
        if (text.length() == 0){
            Toast.makeText(getContext(), "输入框为空，请输入", Toast.LENGTH_SHORT).show();
            descibe.setHint("请输入描述");
        }else {

        }
    }

    public static DetailedFragment newInstance(MyData data) {
        Bundle args = new Bundle();
        args.putSerializable("int", data);
        DetailedFragment fragment = new DetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
