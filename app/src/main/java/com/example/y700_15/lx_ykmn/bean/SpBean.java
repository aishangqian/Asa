package com.example.y700_15.lx_ykmn.bean;

import java.util.List;

public class SpBean {

    public String msg;
    public String code;
    public String page;
    public List<DataBean> data;

    public class DataBean{
        public String detailUrl;
        public String images;
        public String pid;
        public String price;
        public String subhead;
        public String title;
    }
}
