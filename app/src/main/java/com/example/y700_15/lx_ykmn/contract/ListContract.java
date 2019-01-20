package com.example.y700_15.lx_ykmn.contract;

import com.example.y700_15.lx_ykmn.bean.ListBean;
import com.example.y700_15.lx_ykmn.model.ListModel;

import java.util.HashMap;
import java.util.List;

public interface ListContract {

    abstract class IPresenter{
        public abstract void getList(HashMap<String,String> params);
    }

    interface IModel{
        void getList(HashMap<String,String> params, ListModel.ModelCallback modelCallback);
    }

    interface IView{
        void success(List<ListBean.DataBean> list);
        void failure(String msg);
    }
}
