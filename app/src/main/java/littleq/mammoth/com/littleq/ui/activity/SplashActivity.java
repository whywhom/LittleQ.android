package littleq.mammoth.com.littleq.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.interfaces.LittleQService;
import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.net.NetUtils;
import littleq.mammoth.com.littleq.ui.controller.LoginController;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.FTP;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.utils.gson.JsonTeacher;
import littleq.mammoth.com.littleq.utils.gson.TeacherData;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 2016/11/2.
 */
public class SplashActivity extends Activity implements View.OnClickListener{

    private static String TAG = SplashActivity.class.getSimpleName();

    private LoginController loginController;

    private String loginName;
    private String loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        loginController = new LoginController(this);
        loginName = (String) SPUtils.get(SplashActivity.this, Constants.STP_USER_NAME, "");
        loginPassword = (String) SPUtils.get(SplashActivity.this, Constants.STP_PASSWORD, "");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        login();
    }


    private void login() {
        if(loginName.isEmpty() || loginPassword.isEmpty()){
            startLoginActivity();
        } else {
            onLogin();
        }
    }

    @Override
    public void onClick(View view) {

    }

    private void startLoginActivity() {
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish();
    }
    private void onLogin(){
        loginController.loginOn(NetConstants.LOGIN,loginName,loginPassword);
    }


    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(Constants.AVATOR_PATH);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }



}
