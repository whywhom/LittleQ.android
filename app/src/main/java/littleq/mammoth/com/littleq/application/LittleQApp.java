package littleq.mammoth.com.littleq.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.xutils.x;

import io.rong.imkit.RongIM;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.widget.ProgressDialog;

public class LittleQApp extends Application {
    private static final String TAG = LittleQApp.class.getSimpleName();
    public static boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        initSharedPreferences();
        initCache();
        x.Ext.init(this);
        RongIM.init(this);//IM初始化
    }


    private void initCache() {
        if(Constants.checkSdCard()==true){
            Constants.createFileDir(Constants.FILEDIR);
            Constants.createFileDir(Constants.FILEDIR+"/"+Constants.FILEPHOTO);
            Constants.createFileDir(Constants.FILEDIR+"/"+Constants.FILEIMAGE);
            Constants.createFileDir(Constants.FILEDIR+"/"+Constants.FILECACHE);
            Constants.createFileDir(Constants.FILEDIR+"/"+Constants.FILEUSER+"/icon");
            Constants.createFileDir(Constants.FILEDIR+"/"+Constants.FILEUSER+"/"+Constants.FILETEMP);
            Constants.deleteDirFile(Constants.getSDRootPath()+Constants.FILEDIR
                    +"/"+Constants.FILEUSER
                    +"/"+Constants.FILETEMP);
            Constants.AVATOR_PATH = Constants.getSDRootPath()+
                    Constants.FILEDIR+"/"+Constants.FILEUSER;
            Constants.TEMP_PATH = Constants.getSDRootPath()+
                    Constants.FILEDIR+"/"+Constants.FILEUSER+"/"+Constants.FILETEMP;
            Constants.AVATOR_FULL_PATH = Constants.AVATOR_PATH+"/"+Constants.AVATOR_NAME;

            Log.d(TAG,"avator = "+Constants.AVATOR_PATH);
        }else{
            Log.e(TAG,"创建文件夹失败SD卡不可用");
        }
    }

    private void initSharedPreferences() {
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
