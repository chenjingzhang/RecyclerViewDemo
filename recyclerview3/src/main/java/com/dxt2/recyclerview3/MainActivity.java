package com.dxt2.recyclerview3;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    String path = "http://api.u148.net/json/0/%d";
    int page =1;
    //默认空列表
    List<Info.DataBean.DataBeans> list  = new ArrayList<>();

    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout =  findViewById(R.id.swipe);
        //配置刷新组件(旋转的箭头)
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN,Color.RED,Color.BLUE);
        //配置旋转箭头的背景
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#00000000"));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //手垂直滑动执行
                page++;
                getData(page);
            }
        });
        //默认加空的列表
        myAdapter =new MyAdapter(list,this);
        recyclerView.setAdapter(myAdapter);
        //第一次请求
        getData(page);
    }
    public void getData(int page) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format(path, page);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                Info info = gson.fromJson(str, Info.class);
                final List<Info.DataBean.DataBeans> dataBeanses =  info.getData().getData();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        //添加数据(下拉刷新)
                        list.addAll(0,dataBeanses);
                        //整体刷新
                        myAdapter.notifyDataSetChanged();
                        //设置刷新完毕
                        // swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
}
