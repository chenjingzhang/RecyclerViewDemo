package com.dxt2.recyclerview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dxt2.recyclerview2.adapter.MyAdapter;
import com.dxt2.recyclerview2.callback.onItemClickListenter;
import com.dxt2.recyclerview2.callback.onItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置recyclerView填充的布局管理器
        recyclerView.setLayoutManager(GridLayoutManager1());
        //设置分割线,DividerItemDecoration.VERTICAL_LIST:横线，DividerItemDecoration.HORIZONTAL_LIST竖线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("========" + i + "========");
        }
        //加载空的列表
        final MyAdapter myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        //模拟网络得到数据进行刷新
        myAdapter.setList(list);
        //整体刷新
        myAdapter.notifyDataSetChanged();

        //点击事件
        myAdapter.setmOnItemClickListener(new onItemClickListenter() {
            @Override
            public void setOnItemClickListener(int position) {
                Log.e("======","==position==="+position);
                //插入数据
                myAdapter.addData(position,"哈哈哈");

            }
        });
        //长按事件
        myAdapter.setmOnItemLongClickListener(new onItemLongClickListener() {
            @Override
            public void setOnItemLongClickListener(int position) {
                Log.e("======","==长按事件==="+position);
                //删除数据
                myAdapter.removeData(position);
            }
        });

    }
    public LinearLayoutManager LinearLayoutManager1(){
        //替代ListView,构造LinearLayoutManager对象，默认垂直滑动，加载的数据顺序正向
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //参数3:false:正向加载数据，true反向加载数据
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,true);
        return linearLayoutManager;
    }

    public GridLayoutManager GridLayoutManager1(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        return  gridLayoutManager;
    }
}