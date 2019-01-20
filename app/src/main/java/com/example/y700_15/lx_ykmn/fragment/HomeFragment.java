package com.example.y700_15.lx_ykmn.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y700_15.lx_ykmn.MainActivity;
import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.adapter.XrvAdapter;
import com.example.y700_15.lx_ykmn.api.UserApi;
import com.example.y700_15.lx_ykmn.bean.UserBean;
import com.example.y700_15.lx_ykmn.contract.UserContract;
import com.example.y700_15.lx_ykmn.net.OkhttpCallback;
import com.example.y700_15.lx_ykmn.net.OkhttpUtils;
import com.example.y700_15.lx_ykmn.presenter.UserPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements UserContract.IView {
    private XRecyclerView xrv;
    private Button ss;
    private TextView sm;
    private EditText edit_query;
    private int REQUEST_CODE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(View view){
        xrv = view.findViewById(R.id.xrv);
        ss = view.findViewById(R.id.ss);
        sm = view.findViewById(R.id.sm);
        edit_query = view.findViewById(R.id.edit_query);
    }

    private void initData(){
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedPremission();
            }
        });

        UserPresenter presenter = new UserPresenter(this);
        presenter.geUser(new HashMap<String, String>());
    }

    //判断是否在6.0以上，6.0以上需要动态添加权限
    private void checkedPremission(){
        //第一步，判断系统版本是否为6.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //第二步：checkSelfPermission判断有没有此权限
            //第一个参数：上下文
            //第二个参数：我们想要判断的权限，此处为相机权限
            //PackageCompat.PERMISSION_GRANTED条件，权限有没有被授予
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                //如果没授予，则申请权限
                //第一个：上下文
                //第二个：要申请的权限数组，此处为相机权限
                //第三个：请求码，startActivityForResult一样
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},100);
            }
        }else {
            startActivity(new Intent(getActivity(),MainActivity.class));
        }

    }

    @Override
    public void success(UserBean.DataBean dataBean) {
        XrvAdapter adapter = new XrvAdapter(getActivity(),dataBean);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setAdapter(adapter);
    }

    @Override
    public void failure(String msg) {

    }
}
