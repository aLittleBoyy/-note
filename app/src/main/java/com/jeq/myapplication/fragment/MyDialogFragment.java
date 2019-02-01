package com.jeq.myapplication.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jeq.myapplication.R;

import static com.jeq.myapplication.activity.MainActivity.TAG;

public class MyDialogFragment extends DialogFragment implements View.OnClickListener {

    private View view;
    private AlertDialog dialog;
    private Button save;
    private Button cancel;
    private EditText getName;
    private EditText getAge;
    public static final String KEY = "key";
    private String edit_age;
    private String edit_name;
    private MyListener listener;
    private int i;


    public interface MyListener{
        public void secContent(String info, int info2);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        view = inflater.inflate(R.layout.edit_dialog, null);
        dialog = new AlertDialog.Builder(getActivity())
                 .setView(view).create();

        initView();
                return dialog;
    }

    private void initView() {
        save = view.findViewById(R.id.save);
        cancel = view.findViewById(R.id.cancel);
        getName = view.findViewById(R.id.getname);
        getAge = view.findViewById(R.id.getage);

        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (MyListener) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.save:
                //Toast.makeText(getActivity(), "dianji", Toast.LENGTH_SHORT).show();
                //edit_name = String.valueOf(getName.getText());
                Editable edit_name = getName.getText();
                edit_age = getAge.getText().toString();
                Log.d(TAG, "onClick: " +edit_name.toString().trim().length());


                if (edit_name.length() == 0 || edit_age.length() == 0){
                    Toast.makeText(getActivity(), "沒有輸入內容", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    i = Integer.parseInt(edit_age);
                    listener.secContent(edit_name.toString(), i);
                }

                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
        }


    }
}
