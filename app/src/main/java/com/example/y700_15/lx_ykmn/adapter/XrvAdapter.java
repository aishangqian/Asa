package com.example.y700_15.lx_ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.bean.UserBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class XrvAdapter extends XRecyclerView.Adapter<XrvAdapter.ViewHolder> {
    private Context context;
    private UserBean.DataBean list;
    private int REQUEST_CODE;

    public XrvAdapter(Context context, UserBean.DataBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mian_fu,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final List<UserBean.DataBean.BannerBean> banner1 = list.getBanner();
        ArrayList<String> images = new ArrayList<>();
        for (int a=0; a<banner1.size(); a++){
            images.add(banner1.get(a).getIcon());
        }

        viewHolder.xBanner.setIsClipChildrenMode(true);
        viewHolder.xBanner.setData(banner1,null);
        viewHolder.xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(context).load(banner1.get(position).getIcon()).into((ImageView) view);
            }
        });

        viewHolder.xBanner.setPageTransformer(Transformer.Default);

        GridLayoutManager manager = new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        viewHolder.rv_jiu.setLayoutManager(manager);
        JiuAdapter adapter1 = new JiuAdapter(context,list);
        viewHolder.rv_jiu.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        viewHolder.rv_list.setLayoutManager(linearLayoutManager);
        ListAdapter adapter2 = new ListAdapter(context,list);
        viewHolder.rv_list.setAdapter(adapter2);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView rv_jiu;
        private final RecyclerView rv_list;
        private final XBanner xBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xBanner = itemView.findViewById(R.id.xbanner);
            rv_jiu = itemView.findViewById(R.id.rv_jiu);
            rv_list = itemView.findViewById(R.id.rv_list);

        }
    }
}
