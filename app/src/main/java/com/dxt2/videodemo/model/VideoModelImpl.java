package com.dxt2.videodemo.model;

import com.dxt2.videodemo.bean.VideoInfo;
import com.dxt2.videodemo.callback.OnVideoInfoListListener;
import com.dxt2.videodemo.http.HttpUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class VideoModelImpl implements VideoModel {
   private OnVideoInfoListListener onVideoInfoListListener;

    public VideoModelImpl(OnVideoInfoListListener onVideoInfoListListener) {
        this.onVideoInfoListListener = onVideoInfoListListener;
    }

    @Override
    public void getVideoData() {
        HttpUtils.getHttpUtils().getVideoList(new Subscriber<VideoInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               if(onVideoInfoListListener!=null){
                   onVideoInfoListListener.onFailed(e);
               }
            }

            @Override
            public void onNext(VideoInfo videoInfo) {
                    if(onVideoInfoListListener!=null){
                        //实际上调用的是VideoPresenter方法中的onSuccessed()
                        onVideoInfoListListener.onSuccessed(videoInfo.getResult().getVideo_list_by_time());
                    }
            }
        },1);
    }
}
