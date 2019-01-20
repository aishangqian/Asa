package com.example.y700_15.lx_ykmn.contract;

import com.example.y700_15.lx_ykmn.bean.LeftBean;
import com.example.y700_15.lx_ykmn.model.LeftModel;

import java.util.HashMap;
import java.util.List;

public interface LeftContract {

    abstract class IPresenter{
        public abstract void getLeft(HashMap<String,String> params);
    }

    interface IModel{
        void getLeft(HashMap<String,String> params, LeftModel.ModelCallback modelCallback);
    }

    interface IView{
        void success(List<LeftBean.DataBean> list);
        void failure(String msg);
    }
}
