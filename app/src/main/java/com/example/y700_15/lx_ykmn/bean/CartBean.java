package com.example.y700_15.lx_ykmn.bean;

import java.util.List;

public class CartBean {

    public String msg;
    public String code;
    public List<DataBean> data;

    public class DataBean{
        public boolean isChecked;//一级列表是否选中标志位

        public String sellerName;
        public String sellerid;
        public List<Product> list;

        public class Product {
            public boolean isProductChecked;//二级
            public String title;
            public String images;
            public double price;
            public String pid;
            public int pos;
            public int productNum =1;
        }
    }
}
