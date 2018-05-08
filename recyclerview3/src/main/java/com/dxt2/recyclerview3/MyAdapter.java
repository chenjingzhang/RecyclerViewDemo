package com.dxt2.recyclerview3;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Info.DataBean.DataBeans> list;
    Context context;

    public MyAdapter(List<Info.DataBean.DataBeans> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Info.DataBean.DataBeans data = list.get(position);
        //获取作者的信息
        Info.DataBean.DataBeans.UsrBean usrBean = data.getUsr();
        holder.tv_author.setText(usrBean.getNickname());
        holder.tv_title.setText(data.getTitle());

        Picasso.with(context).load(data.getPic_min())
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        Palette p = Palette.from(source).generate();
                        Palette.Swatch s1  = p.getVibrantSwatch();
                        if (s1!=null){
                            int color = s1.getTitleTextColor();
                            holder.tv_title.setTextColor(color);
                        }
                        Palette.Swatch s2  = p.getMutedSwatch();
                        if (s2!=null){
                            int color = s2.getTitleTextColor();
                            holder.tv_title.setTextColor(color);
                        }
                        return source;
                    }

                    @Override
                    public String key() {
                        return data.getPic_min();
                    }
                })
                .into(holder.img_bg);
        Picasso.with(context).load(usrBean.getIcon()).into(holder.img_head);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_bg;
        CircleImageView img_head;
        TextView tv_author;
        TextView tv_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            img_bg = (ImageView) itemView.findViewById(R.id.img_bg);
            img_head = (CircleImageView) itemView.findViewById(R.id.img_author);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author_name);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }
}
