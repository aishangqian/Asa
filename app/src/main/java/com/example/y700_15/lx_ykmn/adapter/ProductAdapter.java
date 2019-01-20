package com.example.y700_15.lx_ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.bean.CartBean;
import com.example.y700_15.lx_ykmn.callback.CartCallback;
import com.example.y700_15.lx_ykmn.view.AddMinusView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ProductAdapter extends XRecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.DataBean.Product> list;

    private CartCallback cartCallback;

    public void setCartCallback(CartCallback cartCallback) {
        this.cartCallback = cartCallback;
    }

    public ProductAdapter(Context context, List<CartBean.DataBean.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final CartBean.DataBean.Product product = list.get(i);

        viewHolder.checkBox.setChecked(product.isProductChecked);

        String[] imgs = product.images.split("\\|");
        if (imgs != null && imgs.length > 0){
            Glide.with(context).load(imgs[0]).into(viewHolder.iv);
        }

        //设置数量到edittext
        viewHolder.addMinusView.setNumTv(product.productNum);
        viewHolder.priceTv.setText("￥："+product.price);
        viewHolder.titleTv.setText(product.title);

        //加减器监听
        viewHolder.addMinusView.setAddMinusCallback(new AddMinusView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {

                //对当前商品数量动态改变
                product.productNum = num;
                //通知一级列表数量改变
                if (cartCallback != null) {
                    cartCallback.notifyNum();
                }
            }
        });

        //设置二级按钮选中状态
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("viewHolder.checkBox.isChecked():"+viewHolder.checkBox.isChecked());

                if (!viewHolder.checkBox.isChecked()){
                    //二级未选中
                    product.isProductChecked = false;

                    //一级未选中的回调
                    if (cartCallback != null){
                        cartCallback.notifyCartItem(false,product.pos);
                    }

                }else {
                    //二级已选中
                    product.isProductChecked = true;

                    //遍历所有数据
                    for (CartBean.DataBean.Product product1 : list){

                        //判断集合内商品的选中状态，如果未选中
                        if (!product1.isProductChecked){
                            if (cartCallback != null){
                                cartCallback.notifyCartItem(false,product1.pos);
                            }
                            break;//跳出循环
                        }

                        //如果都选中，设置一级为true
                        if (cartCallback != null){
                            cartCallback.notifyCartItem(true, product1.pos);
                        }


                    }
                }
            }
        });




    }




    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private CheckBox checkBox;
        private TextView titleTv,priceTv;
        private AddMinusView addMinusView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv_product);
            checkBox = itemView.findViewById(R.id.checkbox);
            titleTv = itemView.findViewById(R.id.title);
            addMinusView = itemView.findViewById(R.id.addminusView);
            priceTv = itemView.findViewById(R.id.price);
        }
    }
}
