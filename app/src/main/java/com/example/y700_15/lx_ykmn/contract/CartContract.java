package com.example.y700_15.lx_ykmn.contract;

import com.example.y700_15.lx_ykmn.bean.CartBean;
import com.example.y700_15.lx_ykmn.model.CartModel;

import java.util.HashMap;
import java.util.List;

public interface CartContract {

    abstract class IPresenter{
        public abstract void getCarts(HashMap<String,String> params);
    }

    interface IModel{
        void getCarts(HashMap<String,String> params, CartModel.ModelCallback modelCallback);
    }

    interface IView{
        void success(List<CartBean.DataBean> list);
        void failure(String msg);
    }
}
