package com.dxt2.videodemo.presenter;

import com.dxt2.videodemo.bean.VideoInfo;
import com.dxt2.videodemo.callback.OnVideoInfoListListener;
import com.dxt2.videodemo.model.VideoModel;
import com.dxt2.videodemo.model.VideoModelImpl;
import com.dxt2.videodemo.view.VideoPlayerView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class VideoPresenterImpl implements VideoPresenter, OnVideoInfoListListener {
    private VideoModel videoModel;
    private VideoPlayerView videoPlayerView;

    public VideoPresenterImpl(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
        videoModel = new VideoModelImpl(this);
    }

    //实现VideoPresenter ，重写getVideoData()方法
    @Override
    public void getVideooData() {
        videoModel.getVideoData();
    }

    //实现OnVideoInfoListListener接口，重写以下两个方法
    @Override
    public void onSuccessed(List<VideoInfo.ResultBean.VideoListByTimeBean> list) {
        videoPlayerView.hideProgress();
        //实际上调用的是MainActivity中的loadVideo
        videoPlayerView.loadVideo(list);
    }

    @Override
    public void onFailed(Throwable e) {
        videoPlayerView.hideProgress();
        videoPlayerView.loadFailed(e);
    }


}
