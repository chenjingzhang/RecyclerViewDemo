package com.dxt2.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dxt2.videodemo.adapter.VideoItemAdapter;
import com.dxt2.videodemo.bean.VideoInfo;
import com.dxt2.videodemo.event.BaceEvent;
import com.dxt2.videodemo.presenter.VideoPresenterImpl;
import com.dxt2.videodemo.view.VideoPlayerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainActivity extends AppCompatActivity implements VideoPlayerView {
    VideoPresenterImpl videoPresenter;
    RelativeLayout loading;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        videoPresenter = new VideoPresenterImpl(this);
        videoPresenter.getVideooData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loading = findViewById(R.id.loading);

    }

    //实现VideoPlayerView接口，重写以下4个方法
    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void loadFailed(Throwable throwable) {
        Toast.makeText(this, "请求失败，原因是:" + throwable, Toast.LENGTH_LONG).show();
    }

    //主要数据通过接口回调进行处理结果
    @Override
    public void loadVideo(List<VideoInfo.ResultBean.VideoListByTimeBean> list) {
        VideoItemAdapter adapter = new VideoItemAdapter(list, this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new BaceEvent());
    }
}
