package com.example.y700_15.lx_ykmn.presenter;

import com.example.y700_15.lx_ykmn.bean.CartBean;
import com.example.y700_15.lx_ykmn.contract.CartContract;
import com.example.y700_15.lx_ykmn.model.CartModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class CartPresenter extends CartContract.IPresenter {
    private CartModel cartModel;
    private CartContract.IView iView;

    public CartPresenter(CartContract.IView iView) {
        cartModel = new CartModel();
        this.iView = iView;
    }

    @Override
    public void getCarts(HashMap<String, String> params) {
        cartModel.getCarts(params, new CartModel.ModelCallback() {
            @Override
            public void success(String result) {
                CartBean cartBean = new Gson().fromJson(result,CartBean.class);
                List<CartBean.DataBean> list = cartBean.data;

                iView.success(list);
            }

            @Override
            public void failure(String msg) {
                iView.failure(msg);
            }
        });
    }
}
