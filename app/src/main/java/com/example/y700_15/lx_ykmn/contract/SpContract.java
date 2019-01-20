package com.example.y700_15.lx_ykmn.contract;

import com.example.y700_15.lx_ykmn.bean.SpBean;
import com.example.y700_15.lx_ykmn.model.SpModel;

import java.util.HashMap;
import java.util.List;

public interface SpContract {

    abstract class IPresenter{
        public abstract void getSp(HashMap<String,String> params);
    }

    interface IModel{
        void getSp(HashMap<String,String> params, SpModel.ModelCallback modelCallback);
    }

    interface IView{
        void success(List<SpBean.DataBean> list);
        void failure(String msg);
    }
}
