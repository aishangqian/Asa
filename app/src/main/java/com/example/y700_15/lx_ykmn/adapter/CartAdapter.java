package com.example.y700_15.lx_ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.bean.CartBean;
import com.example.y700_15.lx_ykmn.callback.CartCallback;
import com.example.y700_15.lx_ykmn.callback.CartUICallback;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.ViewHolder> implements CartCallback {
    private Context context;
    private List<CartBean.DataBean> list;


    private CartUICallback cartUICallback;

    public void setCartUICallback(CartUICallback cartUICallback) {
        this.cartUICallback = cartUICallback;
    }

    public CartAdapter(Context context, List<CartBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public List<CartBean.DataBean> getList() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final CartBean.DataBean dataBean = list.get(i);
        viewHolder.name.setText(dataBean.sellerName);

        viewHolder.checkBox.setChecked(dataBean.isChecked);

        for (CartBean.DataBean.Product product : dataBean.list){
            product.pos = i;
        }

        //二级适配器
        ProductAdapter productAdapter = new ProductAdapter(context,dataBean.list);
        productAdapter.setCartCallback(this);//设置二级适配器回调接口

        viewHolder.xRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.xRecyclerView.setAdapter(productAdapter);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBean.isChecked = viewHolder.checkBox.isChecked();

                //设置二级对象选中状态
                for (CartBean.DataBean.Product product : dataBean.list){
                    product.isProductChecked = dataBean.isChecked;
                }

                //通知刷新
                notifyDataSetChanged();
                //选择状态改变后，通知首页价格联动
                if (cartUICallback!=null){
                    cartUICallback.notifyCart();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    /**
     * 二级列表选中状态的监听，通知一级列表刷新数据
     * @param isChecked
     * @param position
     */
    @Override
    public void notifyCartItem(boolean isChecked, int position) {
        //设置一级列表的选中状态
        list.get(position).isChecked = isChecked;
        notifyDataSetChanged();

        //选中状态改变后，通知主页联动价格
        if (cartUICallback!=null){
            cartUICallback.notifyCart();
        }
    }

    /**
     * 数量改变后，通知首页价格联动
     */
    @Override
    public void notifyNum() {
        if (cartUICallback!=null){
            cartUICallback.notifyCart();
        }
    }

    /**
     * 数量改变后，通知首页价格联动
     * @param lists
     */
    public void addData(List<CartBean.DataBean> lists){

        if (lists!=null&&list!=null){
            list.addAll(lists);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private XRecyclerView xRecyclerView;
        private CheckBox checkBox;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xRecyclerView = itemView.findViewById(R.id.rv);
            checkBox = itemView.findViewById(R.id.checkbox);
            name = itemView.findViewById(R.id.name);
        }
    }
}
