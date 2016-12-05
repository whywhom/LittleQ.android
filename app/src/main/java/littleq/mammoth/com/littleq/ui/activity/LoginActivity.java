package littleq.mammoth.com.littleq.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.ui.controller.LoginController;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * Created by User on 2016/11/2.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private static String TAG = LoginActivity.class.getSimpleName();

    private MainTopTitle title;
    private Button confirm;
    private EditText editName;
    private EditText editPwd;

    private LoginController loginController;



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
        loginController = new LoginController(this);
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
                    Toast.makeText(LoginActivity.this, getString(R.string.illegal_loginpwd), Toast.LENGTH_SHORT).show();
                    return;
                }
                onLogin();
                break;
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            LoginActivity.this.finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void onLogin(){
        loginController.loginOn(NetConstants.LOGIN,editName.getText().toString(),editPwd.getText().toString());
    }

}
