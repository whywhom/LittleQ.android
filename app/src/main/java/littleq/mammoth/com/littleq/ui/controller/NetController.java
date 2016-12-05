package littleq.mammoth.com.littleq.ui.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.net.NetUtils;
import littleq.mammoth.com.littleq.ui.activity.MainActivity;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.FTP;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.utils.ToastAlone;
import littleq.mammoth.com.littleq.utils.gson.TeacherData;

/**
 * Created by User on 2016/11/28.
 */

public class NetController {

    private NetUtils netUtils ;
    private NetUtils.NetUtilsListener listener;
    private Activity context;

    public NetController(Activity context) {
        this.context = context;
    }

    public boolean onGetGradeAndClassController(String url
            , HashMap<String,Object> map
            , Callback.CommonCallback<String> callback) {
        netUtils = new NetUtils("onGetGradeAndClassController");
        netUtils.Post(url,map,callback);
        return true;
    }

    public boolean onGetLessonController( String url
            , HashMap<String,Object> map
            , Callback.CommonCallback<String> callback) {
        netUtils = new NetUtils("onGetLessonController");
        netUtils.Post(url, map, callback);
        return true;
    }

    public boolean onGetTeacherGCLController( String url
            , HashMap<String,Object> map
            , Callback.CommonCallback<String> callback) {
        netUtils = new NetUtils("onGetTeacherGCLController");
        netUtils.Post(url,map,callback);
        return true;
    }

    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 判断WIFI网络是否可用
     * @param context
     * @return
     */
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 判断MOBILE网络是否可用
     * @param context
     * @return
     */
    public boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息
      * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
}
