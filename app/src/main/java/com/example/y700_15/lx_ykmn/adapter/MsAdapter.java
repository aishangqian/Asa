package com.example.y700_15.lx_ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.bean.UserBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MsAdapter extends XRecyclerView.Adapter<MsAdapter.Myvh> {
    private Context context;
    private List<UserBean.DataBean.MiaoshaBean> list1;

    public MsAdapter(Context context, List<UserBean.DataBean> list) {
        this.context = context;
        this.list1 = (List<UserBean.DataBean.MiaoshaBean>) list.get(0).getMiaosha();
    }

    @NonNull
    @Override
    public Myvh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.ms_layout,viewGroup,false);

        return new Myvh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myvh myvh, int i) {

    }

    @Override
    public int getItemCount() {
        return list1==null?0:list1.size();
    }

    public class Myvh extends RecyclerView.ViewHolder {

        public Myvh(@NonNull View itemView) {
            super(itemView);
        }
    }
}
