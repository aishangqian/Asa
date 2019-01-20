package com.example.y700_15.lx_ykmn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.adapter.SpAdapter;
import com.example.y700_15.lx_ykmn.bean.SpBean;
import com.example.y700_15.lx_ykmn.contract.SpContract;
import com.example.y700_15.lx_ykmn.presenter.SpPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class FindmeFragment extends Fragment implements SpContract.IView {

    private SpPresenter spPresenter;

    private EditText editText;
    private ImageView imageView;
    private XRecyclerView xRecyclerView;
    private int mpage;
    private SpAdapter spAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.findme,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();

    }

    private void initView(View view){
        editText = view.findViewById(R.id.show_edit);
        imageView = view.findViewById(R.id.show_seach);
        xRecyclerView = view.findViewById(R.id.x_recycle);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(OrientationHelper.VERTICAL);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        spAdapter = new SpAdapter(getActivity());
        xRecyclerView.setAdapter(spAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mpage = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                getData();
            }
        });
    }

    private void initData(){

    }

    private void getData(){
        HashMap<String,String> map = new HashMap<>();
        map.put("page",String.valueOf(mpage));
        map.put("keywords",editText.getText().toString());
        map.put("sort",String.valueOf(0));
        spPresenter.getSp(map);
    }


    @Override
    public void success(List<SpBean.DataBean> list) {
        

    }

    @Override
    public void failure(String msg) {

    }
}
