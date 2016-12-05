package littleq.mammoth.com.littleq.widget.TagView;

import android.util.Log;

/**
 * Created by kaede on 2016/4/11.
 */
class TagLogUtil {

    public static final String PREFIX = "[kaede]";

    public static void v(String TAG, String msg) {
        if (!TagConstants.DEBUG) return;
        Log.v(TAG, PREFIX + msg);
    }

    public static void d(String TAG, String msg) {
        if (!TagConstants.DEBUG) return;
        Log.d(TAG, PREFIX + msg);
    }

    public static void i(String TAG, String msg) {
        Log.i(TAG, PREFIX + msg);
    }

    public static void w(String TAG, String msg) {
        Log.w(TAG, PREFIX + msg);
    }

    public static void e(String TAG, String msg) {
        Log.e(TAG, PREFIX + msg);
    }
}
