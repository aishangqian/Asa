package com.example.y700_15.lx_ykmn.contract;

import com.example.y700_15.lx_ykmn.bean.RightBean;
import com.example.y700_15.lx_ykmn.model.RightModel;

import java.util.HashMap;
import java.util.List;

public interface RightContract {

    abstract class IPresenter{
        public abstract void getRight(HashMap<String,String> params);
    }

    interface IModel{
        void getRight(HashMap<String,String> params, RightModel.ModelCallback modelCallback);
    }

    interface IView{
        void success_right(List<RightBean.DataBean> list);
        void failure(String msg);
    }
}
