package com.dxt2.recyclerview2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxt2.recyclerview2.R;
import com.dxt2.recyclerview2.callback.onItemClickListenter;
import com.dxt2.recyclerview2.callback.onItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<String> list =new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public MyAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<String> list){this.list = list;}

    //点击事件
    onItemClickListenter mOnItemClickListener;
    //长按事件
    onItemLongClickListener mOnItemLongClickListener;
    //动态添加数据
    public void addData(int pos,String data){
        //在指定的pos位置之前插入数据
        list.add(pos,data);
        //局部刷新
        notifyItemInserted(pos);
    }
    //动态移除数据
    public void removeData(int pos){
        list.remove(pos);
        //局部刷新
        notifyItemRemoved(pos);
    }

    public void setmOnItemClickListener(onItemClickListenter mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(onItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(list.get(position));
        //itemView是RecyclerView.ViewHolder类中声明处理的
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.setOnItemClickListener(position);
                }
            }
        });

        //设置长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener!=null){
                    mOnItemLongClickListener.setOnItemLongClickListener(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
       TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

    }
}
