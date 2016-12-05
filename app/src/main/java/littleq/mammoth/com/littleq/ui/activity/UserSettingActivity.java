package littleq.mammoth.com.littleq.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.widget.CircleImageView;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserSettingActivity extends BaseActivity{
    private static String TAG = UserSettingActivity.class.getSimpleName();

    private MainTopTitle title;
    private Context mContext;
    private CircleImageView avatorHead;
    private ImageView ivArrow;
    private RelativeLayout rlUserAccountManage;

    private RelativeLayout rlUserAccountBinding;

    private RelativeLayout rlUserMsgSetting;

    private RelativeLayout rlUserAboutUs;

    private TextView tvLogout;

    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_setting);
    }

    @Override
    public void init() {
        mContext = this;
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.user_setting));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserSettingActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        registerLittleQReceiver();
        rlUserAccountManage = (RelativeLayout)findViewById(R.id.rl_user_account_manage);
        rlUserAccountBinding = (RelativeLayout)findViewById(R.id.rl_user_account_binding);
        rlUserMsgSetting = (RelativeLayout)findViewById(R.id.rl_user_msg_setting);
        rlUserAboutUs = (RelativeLayout)findViewById(R.id.rl_user_about_us);
        tvLogout = (TextView)findViewById(R.id.tv_logout);
    }

    private void registerLittleQReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.BR_BOOT);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    public void setListener() {
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注销
                SPUtils.put(UserSettingActivity.this, Constants.STP_USER_NAME, "");
                SPUtils.put(UserSettingActivity.this,Constants.STP_PASSWORD, "");
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                Intent intent = new Intent(Constants.BR_BOOT);
                localBroadcastManager.sendBroadcast(intent);
//                UserSettingActivity.this.finish();
            }
        });
        rlUserAccountManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //账户管理
                Intent intent = new Intent(UserSettingActivity.this , UserAccountManageActivity.class);
                startActivity(intent);
            }
        });
        rlUserAccountBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //账户绑定
                Intent intent = new Intent(UserSettingActivity.this , UserAccountBindingActivity.class);
                startActivity(intent);
            }
        });
        rlUserMsgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //消息通知
                Intent intent = new Intent(UserSettingActivity.this , UserAccountMsgActivity.class);
                startActivity(intent);
            }
        });
        rlUserAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关于我们
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constants.BR_BOOT)){
                UserSettingActivity.this.finish();
            }
        }
    }
}
