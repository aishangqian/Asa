package com.example.y700_15.lx_ykmn.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.y700_15.lx_ykmn.R;


@SuppressLint("AppCompatCustomView")
public class WaterGroupNeameLayout extends TextView {
    public WaterGroupNeameLayout(Context context) {
        super(context);
    }

    public WaterGroupNeameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WeekLayout);
        int color = array.getColor(R.styleable.WeekLayout_textColor, Color.BLUE);
        setTextColor(color);
        array.recycle();
    }
}
