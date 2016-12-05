package littleq.mammoth.com.littleq.ui.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import littleq.mammoth.com.littleq.application.LittleQApp;
import littleq.mammoth.com.littleq.net.NetUtils;
import littleq.mammoth.com.littleq.ui.activity.MainActivity;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.FTP;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.utils.ToastAlone;
import littleq.mammoth.com.littleq.utils.gson.TeacherData;
import littleq.mammoth.com.littleq.widget.CircleProgress;
import littleq.mammoth.com.littleq.widget.ProgressDialog;

/**
 * Created by User on 2016/11/28.
 */

public class LoginController {

    private NetUtils netUtils ;
    private NetUtils.NetUtilsListener listener = new LoginListener();
    private Activity context;
    private String name;
    private String pwd;

    public LoginController(Activity context ,NetUtils.NetUtilsListener listener) {
        if(listener != null) {
            this.listener = listener;
        }
        this.context = context;
        netUtils = new NetUtils(this.listener);
    }


    public LoginController(Activity context) {
        this.context = context;
        netUtils = new NetUtils(this.listener);
    }

    public void loginOn(String url, String loginName, String loginPassword ) {
        CircleProgress.getInstance().showCircleBar(context,"请稍候……");
        name= loginName;
        pwd = loginPassword;
        HashMap<String,String> map = new HashMap<>();
        map.put(Constants.LOGIN_NAME, loginName);
        map.put(Constants.LOGIN_PWD, loginPassword);
        netUtils.Post(url,map);
    }


    private class LoginListener implements NetUtils.NetUtilsListener {

        @Override
        public void success(int code, String msg) {
            if(code == 200) {
                SPUtils.put(context, Constants.STP_USER_NAME, name);
                SPUtils.put(context, Constants.STP_PASSWORD, pwd);
                Gson gson = new Gson();
                TeacherData teacherData = gson.fromJson(msg,TeacherData.class);
                Teacher.getInstance().setJsonTeacher(teacherData);
                onGetAvator();
            }

        }

        @Override
        public void fail(int code, String msg) {
            ToastAlone.showShortToast(context,msg);
        }

        @Override
        public void cancel(String msg) {

        }
    }


    private void onGetAvator() {
        //startMainActivity();
        final String url = Teacher.getInstance().getJsonTeacher().getTHeadurl();
        if(url != null && !url.isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 下载
                    try {
                        //单文件下载
                        new FTP().downloadSingleFile(url, Constants.AVATOR_PATH+(Constants.AVATOR_NAME.startsWith("/") ? "" : "/"),
                                Constants.AVATOR_NAME,new FTP.DownLoadProgressListener(){

                                    @Override
                                    public void onDownLoadProgress(String currentStep, long downProcess, File file) {
                                        if(currentStep.equals(Constants.FTP_DOWN_SUCCESS)){
                                            Bitmap bitmap = Constants.getAvatorBitmap();
                                            Constants.setAvatorBitmap(context,bitmap);
                                            CircleProgress.getInstance().dismissCircleBar();
                                            startMainActivity();
                                        } else if(currentStep.equals(Constants.FTP_DOWN_LOADING)){

                                        } else if(currentStep.equals(Constants.FTP_CONNECT_FAIL)
                                                ||currentStep.equals(Constants.FTP_FILE_NOTEXISTS)
                                                ||currentStep.equals(Constants.FTP_DOWN_FAIL)){
                                            Constants.setAvatorBitmap(context,null);
                                            CircleProgress.getInstance().dismissCircleBar();
                                            startMainActivity();
                                        }
                                    }

                                });

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }).start();
        }else {
            Constants.setAvatorBitmap(context,null);
            startMainActivity();
        }
    }


    private void startMainActivity() {
        context.startActivity(new Intent(context,MainActivity.class));
        context.finish();
    }

}
