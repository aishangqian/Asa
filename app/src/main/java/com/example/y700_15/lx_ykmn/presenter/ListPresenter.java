package com.example.y700_15.lx_ykmn.presenter;

import com.example.y700_15.lx_ykmn.bean.ListBean;
import com.example.y700_15.lx_ykmn.contract.ListContract;
import com.example.y700_15.lx_ykmn.model.ListModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class ListPresenter extends ListContract.IPresenter {
    private ListModel listModel;
    private ListContract.IView iView;

    public ListPresenter(ListContract.IView iView) {
        listModel = new ListModel();
        this.iView = iView;
    }

    @Override
    public void getList(HashMap<String, String> params) {
        listModel.getList(params, new ListModel.ModelCallback() {
            @Override
            public void success(String result) {
                ListBean bean = new Gson().fromJson(result,ListBean.class);
                List<ListBean.DataBean> list = bean.getData();
                iView.success(list);
            }

            @Override
            public void failure(String msg) {
                iView.failure(msg);
            }
        });
    }
}
