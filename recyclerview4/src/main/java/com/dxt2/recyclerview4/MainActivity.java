package com.dxt2.recyclerview4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dxt2.recyclerview4.bean.Hotxx;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*需求：
1.下载网络数据(json)okhttp:post
2.gson解析(Gson,实体类GsonFormat)
3.加载数据recyclerView(图文混排)*/
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HotAdapter hotAdapter;
    int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //布局管理器
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        //给recyclerView设置布局管理器
        recyclerView.setLayoutManager(manager);
        //一进入加载空列表
        hotAdapter = new HotAdapter(this);
        recyclerView.setAdapter(hotAdapter);

        //默认加载第一页
        getData(page);
    }

    public void getData(int page) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.u148.net/json/0/1")
                .build();
        Call call = client.newCall(request);

        //3.执行任务Call(异步执行)
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.e("====", "===onResponse==" + str);
                Gson gson = new Gson();
                Hotxx hotxx = gson.fromJson(str, Hotxx.class);
                final List<Hotxx.DataBeanX.DataBean> list = hotxx.getData().getData();
//                recyclerView.post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        //得到结果进行刷新数据列表
//                        hotAdapter.setDataBeenList(list);
//                        hotAdapter.notifyDataSetChanged();
//
//                    }
//                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hotAdapter.setDataBeenList(list);
                        hotAdapter.notifyDataSetChanged();

                    }
                });
            }
        });

    }


}
