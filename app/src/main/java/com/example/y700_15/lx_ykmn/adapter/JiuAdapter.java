package com.example.y700_15.lx_ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.bean.UserBean;

public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.ViewHolder> {
    private Context context;
    private UserBean.DataBean list;

    public JiuAdapter(Context context, UserBean.DataBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_zi,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        UserBean.DataBean.FenleiBean fenleiBean = list.getFenlei().get(i);
        viewHolder.name.setText(fenleiBean.getName());

        Glide.with(context).load(fenleiBean.getIcon()).into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return list.getMiaosha().getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
