package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.widget.MainTopTitle;
import littleq.mammoth.com.littleq.widget.ModifyPwdDialog;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserResetPwdActivity extends BaseActivity{
    private static String TAG = UserResetPwdActivity.class.getSimpleName();

    private MainTopTitle title;
    private Context mContext;

    private TextView tvUserChangeMobile;
    private TextView tvUserModifyPwd;
    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_account_manage);
    }

    @Override
    public void init() {
        mContext = this;
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.user_reset_pwd));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserResetPwdActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        tvUserChangeMobile = (TextView)findViewById(R.id.tv_change_account);
        tvUserModifyPwd = (TextView)findViewById(R.id.tv_user_modify_pwd);
    }

    @Override
    public void setListener() {
        tvUserChangeMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvUserModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改密码
                ModifyPwdDialog.Builder builder = new ModifyPwdDialog.Builder(UserResetPwdActivity.this);
                builder.setPositiveButton(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //设置你的操作事项
                        showToast();
                    }
                });

                builder.setNegativeButton(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    private void showToast(){
        // 创建一个Toast提示信息
        LayoutInflater inflater = (LayoutInflater) UserResetPwdActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_vew, null);
        Toast toast = new Toast(UserResetPwdActivity.this);
        toast.setView(layout);
        // 设置Toast的显示位置
        toast.setGravity(Gravity.CENTER, 0, 0);
        // 创建一个ImageView
        ImageView image = (ImageView) layout.findViewById(R.id.iv_tip);
        image.setImageResource(R.mipmap.check_mark);
        // 创建一个LinearLayout容器
        TextView textView = (TextView) layout.findViewById(R.id.tv_tip);
        textView.setText(getString(R.string.user_pwd_modify_success));
        // 设置Toast的显示时间
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
