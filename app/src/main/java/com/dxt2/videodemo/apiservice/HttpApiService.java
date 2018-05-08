package com.dxt2.videodemo.apiservice;
import com.dxt2.videodemo.bean.VideoInfo;
import com.dxt2.videodemo.constants.Constants;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
/**
 * Created by Administrator on 2018/5/7 0007.
 */
public interface HttpApiService {
    @GET(Constants.PATH)
    Observable<VideoInfo> getVideoList(@Query("page") int page);
}
