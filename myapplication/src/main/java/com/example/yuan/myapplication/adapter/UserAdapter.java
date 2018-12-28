package com.example.yuan.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.myapplication.R;
import com.example.yuan.myapplication.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private boolean ishoww;
    private Context mContext;
    private List<UserBean.DataBean> mDatas;

    public UserAdapter(boolean ishoww, Context mContext) {
        this.ishoww = ishoww;
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<UserBean.DataBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
    public void addmDatas(List<UserBean.DataBean> datas) {

        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        if (ishoww)
        {
            View view=LayoutInflater.from(mContext).inflate(R.layout.linear_item,viewGroup,false);
            holder=new ViewHolder(view);
        }
        else
        {
            View view=LayoutInflater.from(mContext).inflate(R.layout.grid_item,viewGroup,false);
            holder=new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder= (ViewHolder) viewHolder;
        final UserBean.DataBean dataBean = mDatas.get(i);
        String[] split = dataBean.getImages().split("\\|");
        Glide.with(mContext).load(split[0]).into(holder.imageView);

        holder.title.setText(mDatas.get(i).getTitle());
        holder.price.setText(mDatas.get(i).getPrice() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click!=null)
                {
                    click.onSuccess(mDatas.get(i).getPid());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView title;
        private final TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.linearimage);
            title = itemView.findViewById(R.id.linearname);
            price = itemView.findViewById(R.id.linearprice);

        }
    }
    Click click;

    public void setClick(Click mClick) {
        this.click = mClick;
    }

    public interface Click{
        void onSuccess(int position);
    }

}
