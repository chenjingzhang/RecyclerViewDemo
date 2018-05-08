package com.dxt2.recyclerview5pubu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    String[] imageUrls;
    Context context;
    LayoutInflater inflater;

    public MyAdapter(String[] imageUrls, Context context) {
        this.imageUrls = imageUrls;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }
    //2.再执行onCreateViewHolder。执行一屏幕item的创建，超过一屏幕复用
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }
    //4.onBindViewHolder()填充数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(imageUrls[position])
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .config(Bitmap.Config.RGB_565)
                .into(holder.imageView);
    }
    //1.得到集合数量
    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    //3.找控件
    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
