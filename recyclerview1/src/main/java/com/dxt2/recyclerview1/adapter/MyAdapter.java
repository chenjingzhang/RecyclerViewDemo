package com.dxt2.recyclerview1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxt2.recyclerview1.R;
import com.dxt2.recyclerview1.callback.onItemClickListener;
import com.dxt2.recyclerview1.callback.onItemLongClickListener;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8 0008.'
 * 1》写一个类继承RecyclerView.Adapter<VH>:VH必须自己定义
 * 2》在该类中写内部类ViewHolder继承RecyclerView.ViewHolder,并重写构造方法,查找findViewById()
 * 3》给步骤1补充ViewHolder内部类的对象
 * 4》重写RecyclerView.Adapter<VH>中的方法：onCreateViewHolder():创建item视图以及ViewHolder
 * onBindViewHolder():绑定数据到ViewHolder中的视图控件上
 * getItemCount():
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> list;
    private Context context;
    LayoutInflater inflater;


    //点击事件
    onItemClickListener mOnItemClickListener;

    //长按事件
    onItemLongClickListener mOnItemLongClickListener;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setmOnItemClickListener(onItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setmOnItemLongClickListener(onItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
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
                    mOnItemLongClickListener.setItemLongClickListener(position);
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
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
