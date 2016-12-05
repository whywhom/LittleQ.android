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
public class UserAccountBindingActivity extends BaseActivity{
    private static String TAG = UserAccountBindingActivity.class.getSimpleName();

    private MainTopTitle title;
    private Context mContext;

    private ImageView ivUserBindingWeixin;
    private ImageView ivUserBindingQQ;
    private ImageView ivUserBindingWeibo;
    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_account_binding);
    }

    @Override
    public void init() {
        mContext = this;
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.user_account_binding));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserAccountBindingActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        ivUserBindingWeixin = (ImageView)findViewById(R.id.iv_user_bnding_weixin);
        ivUserBindingQQ = (ImageView)findViewById(R.id.iv_user_bnding_qq);
        ivUserBindingWeibo = (ImageView)findViewById(R.id.iv_user_bnding_weibo);

        if("0".compareTo(""+ SPUtils.get(UserAccountBindingActivity.this, Constants.STP_BINDING_WEIXIN, Integer.valueOf(0))) == 0){
            ivUserBindingWeixin.setTag(Integer.valueOf(0));
            ivUserBindingWeixin.setImageResource(R.mipmap.off);
        } else{
            ivUserBindingWeixin.setTag(Integer.valueOf(1));
            ivUserBindingWeixin.setImageResource(R.mipmap.on);
        }

        if("0".compareTo(""+ SPUtils.get(UserAccountBindingActivity.this, Constants.STP_BINDING_QQ, Integer.valueOf(0))) == 0){
            ivUserBindingQQ.setTag(Integer.valueOf(0));
            ivUserBindingQQ.setImageResource(R.mipmap.off);
        } else{
            ivUserBindingQQ.setTag(Integer.valueOf(1));
            ivUserBindingQQ.setImageResource(R.mipmap.on);
        }

        if("0".compareTo(""+ SPUtils.get(UserAccountBindingActivity.this, Constants.STP_BINDING_WEIBO, Integer.valueOf(0))) == 0){
            ivUserBindingWeibo.setTag(Integer.valueOf(0));
            ivUserBindingWeibo.setImageResource(R.mipmap.off);
        } else{
            ivUserBindingWeibo.setTag(Integer.valueOf(1));
            ivUserBindingWeibo.setImageResource(R.mipmap.on);
        }
    }

    @Override
    public void setListener() {
        ivUserBindingWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(ivUserBindingWeixin, Constants.STP_BINDING_WEIXIN);
            }
        });
        ivUserBindingQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(ivUserBindingQQ, Constants.STP_BINDING_QQ);
            }
        });
        ivUserBindingWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(ivUserBindingWeibo, Constants.STP_BINDING_WEIBO);
            }
        });
    }
    private void updateData(ImageView iv, String strSP){
        if("0".compareTo(""+iv.getTag()) == 0){
            iv.setTag(Integer.valueOf(1));
            iv.setImageResource(R.mipmap.on);
            SPUtils.put(UserAccountBindingActivity.this, strSP, Integer.valueOf(1));
        } else{
            iv.setTag(Integer.valueOf(0));
            iv.setImageResource(R.mipmap.off);
            SPUtils.put(UserAccountBindingActivity.this, strSP, Integer.valueOf(0));
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
        LayoutInflater inflater = (LayoutInflater) UserAccountBindingActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_vew, null);
        Toast toast = new Toast(UserAccountBindingActivity.this);
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
