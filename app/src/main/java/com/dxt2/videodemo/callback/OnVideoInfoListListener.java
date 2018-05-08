package com.dxt2.videodemo.callback;

import com.dxt2.videodemo.bean.VideoInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public interface OnVideoInfoListListener {
    void onSuccessed(List<VideoInfo.ResultBean.VideoListByTimeBean> list);

    void onFailed(Throwable e);
}
