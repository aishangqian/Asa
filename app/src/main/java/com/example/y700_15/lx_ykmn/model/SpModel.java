package com.example.y700_15.lx_ykmn.model;

import android.os.Handler;

import com.example.y700_15.lx_ykmn.api.UserApi;
import com.example.y700_15.lx_ykmn.contract.SpContract;
import com.example.y700_15.lx_ykmn.net.OkhttpCallback;
import com.example.y700_15.lx_ykmn.net.OkhttpUtils;

import java.util.HashMap;

public class SpModel implements SpContract.IModel {

    Handler handler = new Handler();

    @Override
    public void getSp(HashMap<String, String> params, final ModelCallback modelCallback) {
        OkhttpUtils.getmInstance().doPost(UserApi.LEIBIAO_API, params, new OkhttpCallback() {
            @Override
            public void success(final String result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        modelCallback.success(result);
                    }
                });
            }

            @Override
            public void failure(String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        modelCallback.failure("网络异常");
                    }
                });
            }
        });
    }

    public interface ModelCallback{
        void success(String result);
        void failure(String msg);
    }
}
