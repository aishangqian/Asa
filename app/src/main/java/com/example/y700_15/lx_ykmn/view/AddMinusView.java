package com.example.y700_15.lx_ykmn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.y700_15.lx_ykmn.R;

public class AddMinusView extends LinearLayout {
    private TextView addTv,minusTv;
    private EditText numTv;
    private int num = 1;

    public AddMinusView(Context context) {
        this(context,null);
    }

    public AddMinusView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.add_minus_layout,this,true);

        addTv = view.findViewById(R.id.add);
        minusTv = view.findViewById(R.id.minus);
        numTv = view.findViewById(R.id.et_num);
        numTv.setText("1");

        addTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                numTv.setText(num+"");

                if (addMinusCallback!=null){
                    addMinusCallback.numCallback(num);
                }
            }
        });
    }

    /**
     * 设置加减器数量
     * @param num
     */
    public void setNumTv(int num) {
        numTv.setText(num+"");
    }

    /**
     * 获取当前view的数量
     * @return
     */
    public int getNum() {
        return num;
    }

    private AddMinusCallback addMinusCallback;

    public void setAddMinusCallback(AddMinusCallback addMinusCallback) {
        this.addMinusCallback = addMinusCallback;
    }

    public interface AddMinusCallback{
        void numCallback(int num);
    }
}
