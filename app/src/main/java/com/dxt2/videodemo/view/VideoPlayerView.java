package com.dxt2.videodemo.view;

import com.dxt2.videodemo.bean.VideoInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public interface VideoPlayerView {
    void showProgress();

    void hideProgress();

    void loadFailed(Throwable throwable);

    void loadVideo(List<VideoInfo.ResultBean.VideoListByTimeBean> list);
}
