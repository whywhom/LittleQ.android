package littleq.mammoth.com.littleq.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import littleq.mammoth.com.littleq.ui.controller.NetController;

/**
 * Created by User on 2016/10/26.
 */
public abstract class BaseActivity extends FragmentActivity implements UiInitInterface{
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected Activity mActivity;
    private NetController netController;
    private static boolean bConnect = false;

    /**
     * 获取intent数据
     *
     * @param savedInstanceState
     */
   public abstract void getIntentData(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mActivity = this;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);
        netController = new NetController(this);
        bConnect = netController.isNetworkConnected(this);
        try {
            loadXml();
            getIntentData(savedInstanceState);
            init();
            setListener();
            setData();
            setOther();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static boolean isbConnect() {
        return bConnect;
    }
    /**
     * Created by wuhaoyong on 16/11/19.
     *
     * 在适当的地方注册Receiver，可以在程序中注册，在onCreate中调用如下函数即可：
     * IntentFilter intentFilter = new IntentFilter();
     * intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
     * registerReceiver(connectionReceiver, intentFilter);
     *
     * 在适当时取消注册Receiver，可以在程序中取消，在onDestroye中调用如下函数即可：
     * if (connectionReceiver != null) {
     *  unregisterReceiver(connectionReceiver);
     * }
     */
    private BroadcastReceiver connectionReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                Log.i(TAG, "unconnect");
                // unconnect network
                bConnect = false;
            }else {
                // connect network
                bConnect = true;
            }
        }
    };
}
