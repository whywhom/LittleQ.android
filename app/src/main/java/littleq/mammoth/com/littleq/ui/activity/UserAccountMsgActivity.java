package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserAccountMsgActivity extends BaseActivity{
    private static String TAG = UserAccountMsgActivity.class.getSimpleName();

    private MainTopTitle title;
    private Context mContext;

    private ImageView ivUserMsg;
    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_account_msg);
    }

    @Override
    public void init() {
        mContext = this;
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.user_msg));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserAccountMsgActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        ivUserMsg = (ImageView)findViewById(R.id.iv_user_msg);

        if("0".compareTo(""+ SPUtils.get(UserAccountMsgActivity.this, Constants.STP_MSG_SWITCH, Integer.valueOf(0))) == 0){
            ivUserMsg.setTag(Integer.valueOf(0));
            ivUserMsg.setImageResource(R.mipmap.off);
        } else{
            ivUserMsg.setTag(Integer.valueOf(1));
            ivUserMsg.setImageResource(R.mipmap.on);
        }
    }

    @Override
    public void setListener() {
        ivUserMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(ivUserMsg, Constants.STP_MSG_SWITCH);
            }
        });
    }
    private void updateData(ImageView iv, String strSP){
        if("0".compareTo(""+iv.getTag()) == 0){
            iv.setTag(Integer.valueOf(1));
            iv.setImageResource(R.mipmap.on);
            SPUtils.put(UserAccountMsgActivity.this, strSP, Integer.valueOf(1));
        } else{
            iv.setTag(Integer.valueOf(0));
            iv.setImageResource(R.mipmap.off);
            SPUtils.put(UserAccountMsgActivity.this, strSP, Integer.valueOf(0));
        }
    }
    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    private void showToast(){
        // 创建一个Toast提示信息
        LayoutInflater inflater = (LayoutInflater) UserAccountMsgActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_vew, null);
        Toast toast = new Toast(UserAccountMsgActivity.this);
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
