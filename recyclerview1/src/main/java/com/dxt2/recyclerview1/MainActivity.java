package com.dxt2.recyclerview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dxt2.recyclerview1.adapter.MyAdapter;
import com.dxt2.recyclerview1.callback.onItemClickListener;
import com.dxt2.recyclerview1.callback.onItemLongClickListener;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("========" + i + "========");
        }
        MyAdapter myAdapter = new MyAdapter(list, this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setmOnItemClickListener(new onItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Log.e("======","==position==="+position);
            }
        });

        myAdapter.setmOnItemLongClickListener(new onItemLongClickListener() {
            @Override
            public void setItemLongClickListener(int postion) {
                Log.e("======","==position==="+postion);
            }
        });
    }

    public GridLayoutManager GridLayoutManager1() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        return gridLayoutManager;
    }

    public LinearLayoutManager LinearLayoutManager1(){
        //替代ListView,构造LinearLayoutManager对象，默认垂直滑动，加载的数据顺序正向
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //参数3:false:正向加载数据，true反向加载数据
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,true);
        return linearLayoutManager;
    }

}
