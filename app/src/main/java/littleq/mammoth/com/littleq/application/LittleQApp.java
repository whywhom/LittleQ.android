package littleq.mammoth.com.littleq.application;

import android.app.Application;

public class LittleQApp extends Application {
    public static boolean DEBUG = true;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

}
