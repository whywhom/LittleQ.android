package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserInfoActivity extends BaseActivity{
    private MainTopTitle title;
    private Context mContext;
    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_userinfo);
    }

    @Override
    public void init() {
        mContext = this;
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.title_activity_userinfo));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserInfoActivity.this.finish();
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
}
