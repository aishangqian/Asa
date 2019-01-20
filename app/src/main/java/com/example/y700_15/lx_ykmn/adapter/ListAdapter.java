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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private UserBean.DataBean list;

    public ListAdapter(Context context, UserBean.DataBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mian_zi_list,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        UserBean.DataBean.TuijianBean.ListBeanX listBeanX = list.getTuijian().getList().get(i);

        viewHolder.price.setText("￥："+listBeanX.getPrice());
        String[] imgs = listBeanX.getImages().split("\\|");
        if (imgs != null && imgs.length > 0){
            Glide.with(context).load(imgs[0]).into(viewHolder.image);
        }


    }

    @Override
    public int getItemCount() {
        return list.getTuijian().getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);

        }
    }
}
