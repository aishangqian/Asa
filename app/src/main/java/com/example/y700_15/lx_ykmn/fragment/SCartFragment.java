package com.example.y700_15.lx_ykmn.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.adapter.CartAdapter;
import com.example.y700_15.lx_ykmn.bean.CartBean;
import com.example.y700_15.lx_ykmn.callback.CartUICallback;
import com.example.y700_15.lx_ykmn.contract.CartContract;
import com.example.y700_15.lx_ykmn.presenter.CartPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SCartFragment extends Fragment implements CartContract.IView,CartUICallback,XRecyclerView.LoadingListener {
    private XRecyclerView xRecyclerView;
    private CheckBox checkBox;

    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private List<CartBean.DataBean> carts;
    private int page = 1;//页码
    private HashMap<String,String> params;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.scart,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }


    private void requestData(){
        params = new HashMap<>();
        params.put("uid","71");
        params.put("page",page+"");

        cartPresenter.getCarts(params);
    }


    private void initView(View view){
        xRecyclerView = view.findViewById(R.id.rv);

        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkBox = view.findViewById(R.id.checkbox);

        //设置全选反选按钮
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    //全选按钮选中

                    for (CartBean.DataBean dataBean : carts){
                        dataBean.isChecked = true;

                        for (CartBean.DataBean.Product product : dataBean.list){
                            product.isProductChecked = true;
                        }
                    }
                }else {
                    //未选中

                    for (CartBean.DataBean dataBean : carts){
                        dataBean.isChecked = false;

                        for (CartBean.DataBean.Product product : dataBean.list){
                            product.isProductChecked = false;
                        }
                    }
                }

                //通知刷新适配器
                cartAdapter.notifyDataSetChanged();

                getTotalPrice();

            }
        });

    }

    /**
     * 获取总价
     */
    private void getTotalPrice(){
        double totalprice = 0;
        //遍历所有商品计算总价
        for (CartBean.DataBean dataBean : cartAdapter.getList()){
            //得到最新的数据的
            for (CartBean.DataBean.Product product : dataBean.list){

                if (product.isProductChecked){
                    System.out.println("num:"+product.productNum);
                    totalprice += product.price*product.productNum;
                }
            }
        }
        //设置总价
        checkBox.setText("￥："+ totalprice);
    }

    private void initData(){
        cartPresenter = new CartPresenter(this);
        carts = new ArrayList<>();

        requestData();
    }

    @Override
    public void success(List<CartBean.DataBean> list) {

        if (list!=null){

            carts = list;

            for (CartBean.DataBean dataBean : carts){
                for (CartBean.DataBean.Product product : dataBean.list){
                    product.productNum = 1;
                }
            }

            if (page == 1){
                //刷新
                xRecyclerView.refreshComplete();
                cartAdapter = new CartAdapter(getActivity(),carts);
                cartAdapter.setCartUICallback(this);
                xRecyclerView.setAdapter(cartAdapter);
            }else {
                //上拉
                if (cartAdapter==null){
                    cartAdapter = new CartAdapter(getActivity(),carts);
                    cartAdapter.setCartUICallback(this);
                    xRecyclerView.setAdapter(cartAdapter);

                }else {
                    cartAdapter.addData(list);
                }
                xRecyclerView.loadMoreComplete();
            }
        }
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void notifyCart() {
        getTotalPrice();
    }

    @Override
    public void onRefresh() {
        page = 1;
        requestData();
    }

    @Override
    public void onLoadMore() {
        page++;
        requestData();
    }
}
