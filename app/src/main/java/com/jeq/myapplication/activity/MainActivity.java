package com.jeq.myapplication.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeq.myapplication.fragment.MyDialogFragment;
import com.jeq.myapplication.R;
import com.jeq.myapplication.data.MyData;
import com.jeq.myapplication.data.SQLiteSchema;
import com.jeq.myapplication.utils.MyDbHelper;
import com.jeq.myapplication.utils.SQLiteDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener ,MyDialogFragment.MyListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "SQL";

    private Context mcontext;
    private List<MyData> data;
    private MyAdaptper adapter;
    private Button insert;
    private Button delete;
    private Button update;
    private Button select;
    private MyDbHelper helper;
    private SQLiteDatabase database;
    private MyData myData;
    private MyDialogFragment fragment;
    private FragmentManager manager;
    private SwipeRefreshLayout layout;
    private LinearLayout layout_search;
    private EditText search_text;
    private SwipeMenuRecyclerView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createDB();

    }

    private void createDB() {
        helper = new MyDbHelper(this);
        database = helper.getReadableDatabase();
    }

    private void initView() {

        layout = findViewById(R.id.swip);
        layout_search = findViewById(R.id.search_layout);
        list = findViewById(R.id.recyclerView_list);
        registerForContextMenu(list);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        select = findViewById(R.id.search_sql);
        search_text = findViewById(R.id.search_sql_text);


        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        select.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        list.addItemDecoration(new DividerItemDecoration(this, manager.getOrientation()));
        list.setLayoutManager(manager);
        layout.setOnRefreshListener(this);

        //创建侧滑菜单
        list.setItemViewSwipeEnabled(false);
        list.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem menuItem = new SwipeMenuItem(getApplicationContext());
                menuItem.setText(R.string.delete_item);
                menuItem.setBackground(R.mipmap.background);
                menuItem.setTextSize(12);
                menuItem.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                menuItem.setImage(R.mipmap.delete);
                rightMenu.addMenuItem(menuItem);
            }
        });

        list.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                int i = menuBridge.getPosition();
                Toast.makeText(getApplicationContext(), data.get(position).getName()+ position, Toast.LENGTH_SHORT).show();
                String name = data.get(position).getName();
                int age = data.get(position).getAge();
                SQLiteDao.delete(database, name);
                data.remove(position);
                adapter.notifyDataSetChanged();
                }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        upDateUI();
    }

    private void upDateUI() {

        data = new ArrayList<>();
        showData();


        //list.setAdapter(adapter);
        if (adapter == null){
            adapter = new MyAdaptper(data);
            Toast.makeText(this, "列表为空", Toast.LENGTH_SHORT).show();
            list.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }

        adapter.setRecyclerViewOnclick(new RecyclerOnItemClickListener() {
            @Override
            public void onClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("int", (Serializable) data);
                bundle.putInt("position", position);
                Log.d("data",data.toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                //Toast.makeText(getApplication(), "长" + position, Toast.LENGTH_LONG).show();
            }
        });
        Log.d(TAG, "upDateUI: ");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert:
                /**
                 * 此處加對話框，獲取對話框輸入的內容
                 */
                manager = getSupportFragmentManager();
                fragment = new MyDialogFragment();
                fragment.show(manager, "logd");
                break;

            case R.id.update:
                Log.d(TAG, "onClick: update");
                data.clear();
                showData();
                layout_search.setVisibility(View.GONE);
                search_text.setText("");
                adapter = new MyAdaptper(data);
                list.setAdapter(adapter);
                break;

            case R.id.search_sql:
                String edit_text_search = search_text.getText().toString();
                List<MyData> search_data = SQLiteDao.select(edit_text_search, database);
                Log.d(TAG, "onClick: " + search_data);
                //data.clear();
                adapter = new MyAdaptper(search_data);
                list.setAdapter(adapter);
                break;
                default:
        }

    }

    @Override
    public void secContent(String info, int info2) {

        Log.d(TAG, "secContent: " + info+info2)  ;
        //int i = Integer.valueOf(info2);
        MyData insert_data = SQLiteDao.insert(info,  info2, database);
        data.add(insert_data);
        adapter.notifyDataSetChanged();
        fragment.onStop();
    }


    private void showData() {
        String sql = "select*from "+ SQLiteSchema.Table.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(SQLiteSchema.Colmuns.NAME);
            String name = cursor.getString(index);
            int index1 = cursor.getColumnIndex(SQLiteSchema.Colmuns.AGE);
            int age = cursor.getInt(index1);

            myData = new MyData();
            myData.setName(name);
            myData.setAge(age);
            data.add(myData);
        }
    }

    //@Override
    public void onRefresh() {
         layout_search.setVisibility(View.VISIBLE);
        layout.setRefreshing(false);
    }

}
