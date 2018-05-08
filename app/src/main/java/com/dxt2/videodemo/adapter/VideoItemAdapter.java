package com.dxt2.videodemo.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.SeekBar;

import com.dxt2.videodemo.R;
import com.dxt2.videodemo.bean.VideoInfo;
import com.dxt2.videodemo.event.BaceEvent;
import com.dxt2.videodemo.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemViewHolder> implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, SeekBar.OnSeekBarChangeListener {
    private List<VideoInfo.ResultBean.VideoListByTimeBean> videos;
    private Context context;
    private LayoutInflater inflater;
    private MediaPlayer mediaPlayer;
    //true表示更新进度，false不更新进度
    private boolean isflag = false;

    private VideoItemViewHolder lastHolder;

    public VideoItemAdapter(List<VideoInfo.ResultBean.VideoListByTimeBean> videos, Context context) {
        this.videos = videos;
        this.context = context;
        inflater = LayoutInflater.from(context);
        mediaPlayer = new MediaPlayer();
        EventBus.getDefault().register(this);
    }

    @Override
    public VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent,false);
        VideoItemViewHolder holder = new VideoItemViewHolder(view);
        holder.imageView.setOnClickListener(this);
        holder.imageView.setTag(holder);
        holder.surfaceView.setOnClickListener(this);
        holder.surfaceView.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoItemViewHolder holder, int position) {
        resetHolder(holder);
        VideoInfo.ResultBean.VideoListByTimeBean videosBeans = videos.get(position);
        //先设置用户也就是视频发布者的信息
        VideoInfo.ResultBean.VideoListByTimeBean.UserInfoBean userInfoBean = videosBeans.getUser_info();
        Picasso.with(context)
                .load(userInfoBean.getAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img_user_head);
        holder.tv_user_name.setText(userInfoBean.getUsername());
        holder.tv_create_time.setText(videosBeans.getCreate_time_desc());

        //设置视频的海报图片信息
        String path = videosBeans.getThumb_img();
        Picasso.with(context)
                .load(path)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
        //设置视频的标题
        holder.video_title.setText(videosBeans.getTitle());
    }




    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public void onViewDetachedFromWindow(VideoItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (lastHolder != null && lastHolder.equals(holder)) {
            mediaPlayer.reset();
            lastHolder = null;
            isflag = false;
            resetHolder(holder);
        }
    }



    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        isflag = true;
        String totalTime = TimeUtils.generateTime((long) mp.getDuration());
        lastHolder.seekBar.setMax(mp.getDuration());
        lastHolder.tv_duration.setText(totalTime);
        lastHolder.imageView.setVisibility(View.GONE);
        lastHolder.item_progress.setVisibility(View.GONE);

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new Runnable() {
            @Override
            public void run() {
                while (isflag == true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lastHolder.seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (lastHolder != null) {
            isflag = false;
            lastHolder.imageView.setVisibility(View.VISIBLE);
            lastHolder.img_video.setImageResource(R.drawable.ic_play);
            lastHolder.img_video.setVisibility(View.VISIBLE);
            lastHolder.item_progress.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        isflag = false;
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    //onClickListener
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.surfaceView:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    isflag = false;
                    mediaPlayer.pause();
                    lastHolder.img_video.setImageResource(R.drawable.ic_pause);
                    lastHolder.img_video.setVisibility(View.VISIBLE);
                } else if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    isflag = true;
                    mediaPlayer.start();
                    lastHolder.img_video.setImageResource(R.drawable.ic_play);
                    lastHolder.img_video.setVisibility(View.GONE);
                }
                break;
            case R.id.imageView:
                VideoItemViewHolder tag = (VideoItemViewHolder) view.getTag();
                play(tag);
                break;
        }
    }

    public void play(VideoItemViewHolder holder) {
        if (lastHolder != null) {
            resetHolder(lastHolder);
        }
        List<VideoInfo.ResultBean.VideoListByTimeBean.SegsBean> segsBeen = videos.get(holder.getAdapterPosition()).getSegs();
        String videoPath=null;
//        if (segsBeen.size()>1){
//            //高清
//             videoPath = segsBeen.get(1).getUrl();
//        }else{
        //以防某些视频只有一个地址，取第一个地址标清的
        videoPath = segsBeen.get(0).getUrl();
//        }
//        //标清
//        String videoPath = videos.get(tag.getAdapterPosition()).getSegs().get(0).getUrl();


//       //超清
//     String videoPath = videos.get(tag.getAdapterPosition()).getSegs().get(2).getUrl();
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videoPath);
            mediaPlayer.setDisplay(holder.surfaceView.getHolder());
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setScreenOnWhilePlaying(true);
            holder.img_video.setVisibility(View.GONE);
            holder.item_progress.setVisibility(View.VISIBLE);
            holder.seekBar.setOnSeekBarChangeListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastHolder = holder;
    }


    //OnSeekBarChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mediaPlayer.seekTo(progress);
        }
        if (lastHolder != null) {
            lastHolder.tv_time.setText(TimeUtils.generateTime(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isflag = false;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isflag = true;
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        lastHolder.img_video.setVisibility(View.GONE);
    }

    //重置到未播放的状态
    private void resetHolder(VideoItemViewHolder holder) {
        holder.imageView.setVisibility(View.VISIBLE);
        holder.img_video.setImageResource(R.drawable.ic_play);
        holder.img_video.setVisibility(View.VISIBLE);
        holder.item_progress.setVisibility(View.GONE);
        holder.seekBar.setMax(0);
        holder.seekBar.setProgress(0);
        holder.tv_duration.setText("00:00");
        holder.tv_time.setText("00:00");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBackEvent(BaceEvent event) {
        //后退事件，若视频播放未停止，在此处结束
        if (lastHolder != null && mediaPlayer.isPlaying()) {
            lastHolder = null;
            isflag = false;
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        EventBus.getDefault().unregister(this);
    }


}
