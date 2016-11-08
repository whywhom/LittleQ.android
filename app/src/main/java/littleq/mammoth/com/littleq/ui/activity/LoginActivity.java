package littleq.mammoth.com.littleq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.interfaces.LittleQService;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.widget.MainTopTitle;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 2016/11/2.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private static String TAG = LoginActivity.class.getSimpleName();
    private MainTopTitle title;
    private Button confirm;
    private EditText editName;
    private EditText editPwd;
    private static String TIMESTAMP = "requeststamp";
    private static String LOGIN_NAME = "t_loginname";
    private static String LOGIN_PWD = "t_loginpwd";

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void init() {
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.login));
        builder.bg(R.color.login_main_top_bg).titleColor(R.color.login_main_top_title_color);
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.drawable.login_back_bg).leftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setBuilder(builder);
        confirm = (Button) findViewById(R.id.confirm);
        editName = (EditText)findViewById(R.id.name);
        editPwd = (EditText)findViewById(R.id.pwd);
    }

    @Override
    public void setListener() {
        confirm.setOnClickListener(this);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                String name = editName.getText().toString();
                if(!Constants.isLoginName(name)){
                    Toast.makeText(LoginActivity.this, getString(R.string.illegal_loginname), Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = editPwd.getText().toString();
                if(!Constants.isLoginPwd(pwd)){
                    Toast.makeText(LoginActivity.this, getString(R.string.illegal_loginname), Toast.LENGTH_SHORT).show();
                    return;
                }
                onLogin();
                break;
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    private void onLogin(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LITTLEQ_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LittleQService apiService = retrofit.create(LittleQService.class);
        Map<String, String> map = new HashMap<>();
        map.put(TIMESTAMP, Constants.getTimeStamp());
        map.put(LOGIN_NAME, editName.getText().toString());
        map.put(LOGIN_PWD, editPwd.getText().toString());
        Call<ResponseBody> call = apiService.getSLittleQResponse("1000",map);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    String msg = response.message();
                    String header = response.headers().toString();
                    String body = response.body().toString();
                    int code = response.code();
                    Log.d(TAG, msg);
                    if(msg.equals("OK")) {
                        startMainActivity();
                    }
                } else{
                    String msg = response.message();
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }

}
