package com.example.y700_15.lx_ykmn.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.y700_15.lx_ykmn.ProgressView;
import com.example.y700_15.lx_ykmn.R;

public class Main3Activity extends AppCompatActivity {
    private Button bth;
    private ProgressView progressView;
    private static final int FLAG = 0x123;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case FLAG:
                    int progress = progressView.getProgress();
                    progress+=10;
                    if (progress>100){
                        progress=0;
                    }

                    progressView.setProgress(progress);
                    handler.sendEmptyMessageDelayed(FLAG,1000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bth = findViewById(R.id.but);
        progressView = findViewById(R.id.progressView);

        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessageDelayed(FLAG,1000);
            }
        });


        progressView.setListener(new ProgressView.ProgressUpdateListener() {
            @Override
            public void onProgressUpdated(int progress) {
                if (progress>=100){
                    startActivity(new Intent(Main3Activity.this,Main2Activity.class));
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(FLAG);
    }
}
