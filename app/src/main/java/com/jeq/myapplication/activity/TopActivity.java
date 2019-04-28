package com.jeq.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeq.myapplication.R;

/**
 * The type Top activity.
 */
public class TopActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SQL";
    private LinearLayout layout_look;
    private LinearLayout layout_plus;
    private LinearLayout layout_message;
    private LinearLayout layout_my;
    private ImageView image_look;
    private ImageView image_plus;
    private ImageView image_message;
    private ImageView image_my;
    private TextView text_look;
    private TextView text_plus;
    private TextView text_message;
    private TextView text_my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        initView();
    }
    private void initView() {

        //获取底部布局
        View id = findViewById(R.id.include);

        //获取底部按钮

        layout_look = id.findViewById(R.id.look);
        layout_plus = id.findViewById(R.id.plus);
        layout_message = id.findViewById(R.id.message);
        layout_my = id.findViewById(R.id.my);

        //底部按钮点击事件
        layout_look.setOnClickListener(this);
        layout_plus.setOnClickListener(this);
        layout_message.setOnClickListener(this);
        layout_my.setOnClickListener(this);

        //按钮图片

        image_look = layout_look.findViewById(R.id.image_look);
        image_plus = layout_plus.findViewById(R.id.image_plus);
        image_message = layout_message.findViewById(R.id.image_message);
        image_my = layout_my.findViewById(R.id.image_my);

        //按钮文字

        text_look = layout_look.findViewById(R.id.text_look);
        text_plus = layout_plus.findViewById(R.id.text_plus);
        text_message = layout_message.findViewById(R.id.text_message);
        text_my = layout_my.findViewById(R.id.text_my);

        //updateFragment(new HomeFragment());

        image_look.setImageResource(R.mipmap.lookselected);
        image_message.setImageResource(R.mipmap.messagenormal);
        image_my.setImageResource(R.mipmap.mynormal);


        text_look.setTextColor(Color.parseColor("#FF2D45"));
        text_message.setTextColor(Color.parseColor("#898989"));
        text_my.setTextColor(Color.parseColor("#898989"));

    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.look:
                //updateFragment(new HomeFragment());
                image_look.setImageResource(R.mipmap.lookselected);
                image_message.setImageResource(R.mipmap.messagenormal);
                image_my.setImageResource(R.mipmap.mynormal);

                text_look.setTextColor(Color.parseColor("#FF2D45"));
                text_message.setTextColor(Color.parseColor("#898989"));
                text_my.setTextColor(Color.parseColor("#898989"));
                break;

            case R.id.plus:
                Toast.makeText(getApplicationContext(), "发布", Toast.LENGTH_SHORT).show();
                break;
            case R.id.message:
                //updateFragment(new CheckOneFragment());
                image_look.setImageResource(R.mipmap.looknormal);
                image_message.setImageResource(R.mipmap.messageselected);
                image_my.setImageResource(R.mipmap.mynormal);

                text_look.setTextColor(Color.parseColor("#898989"));
                text_message.setTextColor(Color.parseColor("#FF2D45"));
                text_my.setTextColor(Color.parseColor("#898989"));

                break;

            case R.id.my:
                //updateFragment(new TestFragment().newInstance("我是" + id));
                image_look.setImageResource(R.mipmap.looknormal);
                image_message.setImageResource(R.mipmap.messagenormal);
                image_my.setImageResource(R.mipmap.myselected);

                text_look.setTextColor(Color.parseColor("#898989"));
                text_message.setTextColor(Color.parseColor("#898989"));
                text_my.setTextColor(Color.parseColor("#FF2D45"));
                break;

            default:
                Toast.makeText(getApplicationContext(), "点击失败", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * Update fragment int.
     *
     * @param fragment the fragment
     * @return the int
     */
    public int updateFragment(Fragment fragment){
        int i = getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragment).commit();
        return i;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
