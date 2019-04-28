package com.jeq.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeq.myapplication.adapter.MyAdaptper;
import com.jeq.myapplication.adapter.RecyclerOnItemClickListener;
import com.jeq.myapplication.fragment.MyDialogFragment;
import com.jeq.myapplication.R;
import com.jeq.myapplication.data.MyData;
import com.jeq.myapplication.data.SQLiteSchema;
import com.jeq.myapplication.inter.MyListener;
import com.jeq.myapplication.utils.DB;
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
public class MainActivity extends AppCompatActivity implements View.OnClickListener , MyListener, SwipeRefreshLayout.OnRefreshListener {

    /**
     * The constant TAG.
     */
    public static final String TAG = "SQL";


    private List<MyData> data;
    private MyAdaptper adapter;
    private Button insert;
    private Button update;
    private Button select;
    private SQLiteDatabase database;
    private MyData myData;
    private MyDialogFragment fragment;
    private FragmentManager manager;
    private SwipeRefreshLayout layout;
    private LinearLayout layout_search;
    private EditText search_text;
    private SwipeMenuRecyclerView list;
    private MyData descibe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createDB();

    }

    private void createDB() {
        database = DB.dbtabase(this);
    }

    private void initView() {

        layout = findViewById(R.id.swip);
        layout.setRefreshing(false);
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
        //list.addItemDecoration(new DividerItemDecoration(this, manager.getOrientation()));
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
        descibe = (MyData) getIntent().getSerializableExtra("des");
        Log.d(TAG, "MainActivity----onResume接收详情过来的数据: "+ descibe);
    }

    private void saveDetailed() {

        /**
         * 获取详情页描述
         */
     /*   Intent intent = getIntent();
        MyData descibe= (MyData) intent.getCharSequenceExtra("des");
*/


        //String descibe = intent.getStringExtra("des");
        Log.d(TAG, "upDateUI: " + descibe);

        //SQLiteDao.insert_detailed(descibe, database);
         /* int id = detailde_data.getId();
        int age = detailde_data.getAge();
        String name = detailde_data.getName();
        String describe = detailde_data.getDescribe();
        + id+age+name+describe*/
        // Log.d(TAG, "MainActivity----onResume接收详情过来的数据: "+ descibe.getDescribe());
    }


    private void upDateUI() {

        data = new ArrayList<>();
        showData();
        //saveDetailed();

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
                Log.d(TAG, "MainActivity----data:"+data.toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                //Toast.makeText(getApplication(), "长" + position, Toast.LENGTH_LONG).show();
            }
        });

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
                //data.clear();
                layout_search.setVisibility(View.GONE);
                search_text.setText("");
                Log.d(TAG, "onClick: "+data.toString());
                adapter = new MyAdaptper(data);
                list.setAdapter(adapter);
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
                break;

            case R.id.search_sql:
                String edit_text_search = search_text.getText().toString();
                final List<MyData> search_data = SQLiteDao.select(edit_text_search, database);
                Log.d(TAG, "onClick: " + search_data+edit_text_search);
                //data.clear();
                adapter = new MyAdaptper(search_data);
                list.setAdapter(adapter);
                adapter.setRecyclerViewOnclick(new RecyclerOnItemClickListener() {
                    @Override
                    public void onClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("int", (Serializable) search_data);
                        bundle.putInt("position", position);
                        Log.d("data",search_data.toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                        //Toast.makeText(getApplication(), "长" + position, Toast.LENGTH_LONG).show();
                    }
                });
                break;
                default:
        }

    }



   /* @Override
    public void secContent(String info, int info2) {

        Log.d(TAG, "secContent: " + info+info2)  ;
        //int i = Integer.valueOf(info2);
        MyData insert_data = SQLiteDao.insert(info,  info2, database);
        data.add(insert_data);
        adapter.notifyDataSetChanged();
        fragment.onStop();
    }

    @Override
    public void secContentOnly(String info) {

    }*/

    @Override
    public void setContentData(MyData datas) {
        String name = datas.getName();
        int age = datas.getAge();
        MyData insert_data = SQLiteDao.insert(name, age, database);
        data.add(insert_data);
        adapter.notifyDataSetChanged();
        fragment.onStop();
    }


    private void showData() {
        /*String sql = "select*from "+ SQLiteSchema.Table.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(SQLiteSchema.Colmuns.NAME);
            String name = cursor.getString(index);
            int index1 = cursor.getColumnIndex(SQLiteSchema.Colmuns.AGE);
            int age = cursor.getInt(index1);
            int id = cursor.getInt(cursor.getColumnIndex(SQLiteSchema.Colmuns.ID));
            myData = new MyData();
            myData.setId(id);
            myData.setName(name);
            myData.setAge(age);
            data.add(myData);
        }*/
        SQLiteDao.showDataList(database, data);
    }

    //@Override
    public void onRefresh() {
        layout_search.setVisibility(View.VISIBLE);
        layout.setRefreshing(false);
    }

}
