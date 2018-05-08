package com.dxt2.recyclerview4;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxt2.recyclerview4.bean.Hotxx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotViewHolder>{
    Context context;

    //空列表，默认第一次加载
    List<Hotxx.DataBeanX.DataBean> dataBeenList = new ArrayList<>();
    LayoutInflater inflater;

    public HotAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDataBeenList(List<Hotxx.DataBeanX.DataBean> dataBeenList) {
        this.dataBeenList.addAll(dataBeenList);
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cardview, null);
        return new HotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        String title = dataBeenList.get(position).getTitle();
        holder.textView.setText(title);
//        final String url = dataBeenList.get(position).getPic_url();
        String url  = dataBeenList.get(position).getPic_mid();
        Picasso.with(context).load(url)
                //在下载过程中显示的图
                .placeholder(R.mipmap.ic_launcher)
                //下载失败显示的图
                .error(android.R.drawable.ic_menu_edit)
                .config(Bitmap.Config.RGB_565)
                //显示图片
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataBeenList.size();
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public HotViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
