package com.example.y700_15.lx_ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.bean.SpBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpAdapter extends XRecyclerView.Adapter<SpAdapter.MyVh> {
    private Context context;
    private List<SpBean.DataBean> list;

    public SpAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<SpBean.DataBean> lists) {
        list.clear();
        if (lists != null){
            list.addAll(lists);
        }
        notifyDataSetChanged();
    }

    public void addList(List<SpBean.DataBean> lists) {

        if (lists != null){
            list.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sp_item,viewGroup,false);

        return new MyVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVh myVh, final int i) {


        SpBean.DataBean dataBean = list.get(i);

        myVh.title.setText(dataBean.title);
        myVh.price.setText(dataBean.price);

        String imgs = dataBean.images.split("\\|")[0].replace("https","http");
        Glide.with(context).load(imgs).into(myVh.image);

        myVh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (showCallBack!=null){
                    showCallBack.CallBack(list.get(i).pid);
                }
            }
        });

        myVh.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showClickCallBack!=null){
                    showClickCallBack.CallBack(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVh extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title,price;
        private Button button;
        private ConstraintLayout constraintLayout;

        public MyVh(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            button = itemView.findViewById(R.id.buts);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }

    private ShowCallBack showCallBack;

    public void setShowCallBack(ShowCallBack showCallBack) {
        this.showCallBack = showCallBack;
    }

    //定义接口
    public interface ShowCallBack{
        void CallBack(String pid);
    }

    //点击跳转
    private ShowClickCallBack showClickCallBack;

    public void setShowClickCallBack(ShowClickCallBack showClickCallBack) {
        this.showClickCallBack = showClickCallBack;
    }

    //定义接口
    public interface ShowClickCallBack{
        void CallBack(int position);
    }

}
