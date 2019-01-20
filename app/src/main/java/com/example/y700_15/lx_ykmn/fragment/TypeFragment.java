package com.example.y700_15.lx_ykmn.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.adapter.LeftAdapter;
import com.example.y700_15.lx_ykmn.adapter.RightAdapter;
import com.example.y700_15.lx_ykmn.bean.LeftBean;
import com.example.y700_15.lx_ykmn.bean.RightBean;
import com.example.y700_15.lx_ykmn.contract.LeftContract;
import com.example.y700_15.lx_ykmn.contract.RightContract;
import com.example.y700_15.lx_ykmn.presenter.LeftPresenter;
import com.example.y700_15.lx_ykmn.presenter.RightPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TypeFragment extends Fragment implements LeftContract.IView,RightContract.IView {
    private RecyclerView rv_left;
    private RecyclerView rv_right;
    private HashMap<String,String> params;
    private LeftPresenter leftPresenter;
    private List<LeftBean.DataBean> list;
    private String cid = "1";
    private RightPresenter rightPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.type,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(View view){
        rv_left = view.findViewById(R.id.rv_left);
        rv_right = view.findViewById(R.id.rv_right);
    }

    private void initData(){

        leftPresenter = new LeftPresenter(this);
        leftPresenter.getLeft(new HashMap<String, String>());

        rightPresenter = new RightPresenter(this);
        getRight();
    }

    private void getRight(){
        list = new ArrayList<>();
        params = new HashMap<>();
        params.put("cid",cid);
        rightPresenter.getRight(params);
    }

    @Override
    public void success(List<LeftBean.DataBean> list) {
        LeftAdapter adapter1=new LeftAdapter(getActivity(),list);
        adapter1.setListener(new LeftAdapter.OnRecycleViewClickListener() {
            @Override
            public void onItemClickListener(View view, int position, String cid) {
                params= new HashMap<>();
                params.put("cid",cid);
                rightPresenter.getRight(params);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
        rv_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_left.setAdapter(adapter1);


    }
    @Override
    public void success_right(List<RightBean.DataBean> list) {
        RightAdapter rightAdapter = new RightAdapter(getActivity(),list);
        rv_right.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_right.setAdapter(rightAdapter);
    }

    @Override
    public void failure(String msg) {

    }
}
