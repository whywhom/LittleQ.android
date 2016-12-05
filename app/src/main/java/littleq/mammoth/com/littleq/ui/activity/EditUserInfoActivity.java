package littleq.mammoth.com.littleq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

public class EditUserInfoActivity extends BaseActivity {
    private MainTopTitle title;
    private String titleName;
    private EditText etModify;
    private String origName;
    private ImageView ivClear;
    private MainTopTitle.Builder builder;
    private RelativeLayout rlModifyName;

    @Override
    public void getIntentData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if(intent != null){
            titleName = intent.getStringExtra("title");
            origName = intent.getStringExtra("data");
        }
    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_info_edit);
    }

    @Override
    public void init() {
        title = (MainTopTitle)findViewById(R.id.title);
        builder = new MainTopTitle.Builder(titleName);
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.right(MainTopTitle.RIGHT_CHARACTOR).rightChar(getString(R.string.userinfo_edit_finish));
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("modify", "");
                // 设置结果，并进行传送
                EditUserInfoActivity.this.setResult(UserInfoActivity.REQUEST_NAME, mIntent);
                EditUserInfoActivity.this.finish();
            }
        });
        builder.rightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modifier = etModify.getText().toString();
                if(modifier == null || modifier.isEmpty()){
                    Toast.makeText(EditUserInfoActivity.this,getString(R.string.userinfo_name_edit),Toast.LENGTH_SHORT);
                    return;
                }
                Intent mIntent = new Intent();
                mIntent.putExtra("modify", modifier);
                // 设置结果，并进行传送
                if(titleName.equals(getString(R.string.modify_name))) {
                    EditUserInfoActivity.this.setResult(UserInfoActivity.REQUEST_NAME, mIntent);
                }else if(titleName.equals(getString(R.string.modify_mobile))){
                    EditUserInfoActivity.this.setResult(UserInfoActivity.REQUEST_MOBILE, mIntent);
                }
                EditUserInfoActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        rlModifyName = (RelativeLayout)findViewById(R.id.rl_modify_name);
        if(titleName.equals(getString(R.string.modify_name))) {
            etModify = (EditText) findViewById(R.id.et_user_edit);
            etModify.setHint(getString(R.string.userinfo_name_edit));
            etModify.setText(origName);
            ivClear = (ImageView) findViewById(R.id.iv_clear);
        } else if(titleName.equals(getString(R.string.modify_mobile))){
            etModify = (EditText) findViewById(R.id.et_user_edit);
            etModify.setHint(getString(R.string.userinfo_mobile_edit));
            etModify.setText(origName);
            ivClear = (ImageView) findViewById(R.id.iv_clear);
        }
    }

    @Override
    public void setListener() {
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etModify.setText("");
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
}
