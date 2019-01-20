package application;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;

public class Madd extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UMConfigure.init(this, "5c33fad7f1f556edc3000ad4","Umeng", UMConfigure.DEVICE_TYPE_PHONE,null);
    }
}
