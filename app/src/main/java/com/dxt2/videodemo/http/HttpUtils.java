package com.dxt2.videodemo.http;

import com.dxt2.videodemo.apiservice.HttpApiService;
import com.dxt2.videodemo.bean.VideoInfo;
import com.dxt2.videodemo.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;
    private Retrofit retrofit;

    public static HttpUtils getHttpUtils() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;

    }


    public HttpUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public void getVideoList(Subscriber<VideoInfo> subscriber,int page) {
        HttpApiService httpApiService = retrofit.create(HttpApiService.class);
        Observable<VideoInfo> videoInfoObservable = httpApiService.getVideoList(page);
        videoInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
