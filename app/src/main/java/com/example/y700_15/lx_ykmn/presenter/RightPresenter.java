package com.example.y700_15.lx_ykmn.presenter;

import com.example.y700_15.lx_ykmn.bean.RightBean;
import com.example.y700_15.lx_ykmn.contract.RightContract;
import com.example.y700_15.lx_ykmn.model.RightModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class RightPresenter extends RightContract.IPresenter {

    private RightModel rightModel;
    private RightContract.IView iView;

    public RightPresenter(RightContract.IView iView) {
        rightModel = new RightModel();
        this.iView = iView;
    }

    @Override
    public void getRight(HashMap<String, String> params) {
        rightModel.getRight(params, new RightModel.ModelCallback() {
            @Override
            public void success(String result) {
                RightBean rightBean = new Gson().fromJson(result,RightBean.class);
                List<RightBean.DataBean> list = rightBean.getData();

                iView.success_right(list);
            }

            @Override
            public void failure(String msg) {
                iView.failure(msg);
            }
        });
    }
}
