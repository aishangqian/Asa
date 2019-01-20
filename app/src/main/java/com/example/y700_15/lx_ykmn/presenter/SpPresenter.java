package com.example.y700_15.lx_ykmn.presenter;

import com.example.y700_15.lx_ykmn.bean.SpBean;
import com.example.y700_15.lx_ykmn.contract.SpContract;
import com.example.y700_15.lx_ykmn.model.SpModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class SpPresenter extends SpContract.IPresenter {
    private SpModel spModel;
    private SpContract.IView iView;

    public SpPresenter(SpContract.IView iView) {
        spModel = new SpModel();
        this.iView = iView;
    }

    @Override
    public void getSp(HashMap<String, String> params) {
        spModel.getSp(params, new SpModel.ModelCallback() {
            @Override
            public void success(String result) {
                SpBean spBean = new Gson().fromJson(result,SpBean.class);
                List<SpBean.DataBean> list = spBean.data;

                iView.success(list);
            }

            @Override
            public void failure(String msg) {
                iView.failure(msg);
            }
        });
    }
}
