package com.example.y700_15.lx_ykmn.presenter;

import com.example.y700_15.lx_ykmn.bean.LeftBean;
import com.example.y700_15.lx_ykmn.contract.LeftContract;
import com.example.y700_15.lx_ykmn.model.LeftModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class LeftPresenter extends LeftContract.IPresenter {
    private LeftModel leftModel;
    private LeftContract.IView iView;

    public LeftPresenter(LeftContract.IView iView) {
        leftModel = new LeftModel();
        this.iView = iView;
    }

    @Override
    public void getLeft(HashMap<String, String> params) {
        leftModel.getLeft(params, new LeftModel.ModelCallback() {
            @Override
            public void success(String result) {
                LeftBean leftBean = new Gson().fromJson(result,LeftBean.class);
                List<LeftBean.DataBean> list = leftBean.getData();

                iView.success(list);
            }

            @Override
            public void failure(String msg) {
                iView.failure(msg);
            }
        });
    }
}
