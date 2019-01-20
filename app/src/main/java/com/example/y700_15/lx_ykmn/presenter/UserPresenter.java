package com.example.y700_15.lx_ykmn.presenter;

import com.example.y700_15.lx_ykmn.bean.UserBean;
import com.example.y700_15.lx_ykmn.contract.UserContract;
import com.example.y700_15.lx_ykmn.model.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserPresenter extends UserContract.IPresenter {
    private UserModel listModel;
    private UserContract.IView iView;

    public UserPresenter(UserContract.IView iView) {
        listModel = new UserModel();
        this.iView = iView;
    }

    @Override
    public void geUser(HashMap<String, String> params) {
        listModel.getUser(params, new UserModel.ModelCallback() {
            @Override
            public void success(String result) {
                UserBean userBean = new Gson().fromJson(result,UserBean.class);

                UserBean.DataBean data = userBean.getData();
                iView.success(data);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }
}
