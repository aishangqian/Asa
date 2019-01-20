package com.example.y700_15.lx_ykmn.contract;

import com.example.y700_15.lx_ykmn.bean.UserBean;
import com.example.y700_15.lx_ykmn.model.UserModel;

import java.util.HashMap;

public interface UserContract {

    /**
     * p层
     */
    abstract class IPresenter{
        public abstract void geUser(HashMap<String,String> params);
    }

    /**
     * model层
     */
    interface IModel{
        void getUser(HashMap<String,String> params, UserModel.ModelCallback modelCallback);
    }

    interface IView{
        void success(UserBean.DataBean dataBean);
        void failure(String msg);
    }
}
