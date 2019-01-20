package com.example.y700_15.lx_ykmn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {

    private ProgressUpdateListener listener;

    public interface ProgressUpdateListener{
        void onProgressUpdated(int progress);
    }

    public void setListener(ProgressUpdateListener listener) {
        this.listener = listener;
    }

    private int progress = 0;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
        if (listener != null){
            listener.onProgressUpdated(progress);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        //绘图范围
        int react = Math.min(width,height);


        //半径
        int radius = react / 2;

        //绘制最外层的大圆
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(radius,radius,radius,paint);

        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
        //画扇形
        canvas.drawArc(10, 10, react-10, react-10, -90, progress*360/100, true, paint);

        //画内层的小圆
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(radius, radius, radius-20,paint);

        //画进度文字
        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);

        Rect rect = new Rect();
        paint.getTextBounds(progress + "", 0, String.valueOf(progress).length(), rect);

        int textWidth = rect.width();
        int textHeight = rect.height();

        canvas.drawText(progress + "", radius-textWidth/2, radius-textHeight/2, paint);

    }
}
