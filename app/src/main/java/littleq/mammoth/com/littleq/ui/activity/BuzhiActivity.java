package littleq.mammoth.com.littleq.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;

/**
 * Created by whywhom on 2016/9/29.
 */
public class BuzhiActivity extends BaseActivity implements View.OnClickListener{


    private TextView back_content,record_tips,time_esp,save_later_released,homework_released;
    private ImageView iv_back,record,take_photo;
    private RelativeLayout ll_title,record_layout;
    private LinearLayout media_layout,photos;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()) {
           case R.id.back_content:
           case R.id.ll_title:
           case R.id.iv_back:
               finish();
               break;
           case R.id.record:
               record_tips.setVisibility(View.GONE);
               media_layout.setVisibility(View.VISIBLE);
               record_layout.setVisibility(View.VISIBLE);
               break;
           case R.id.take_photo:
               media_layout.setVisibility(View.VISIBLE);
               photos.setVisibility(View.VISIBLE);
               break;
           case R.id.homework_released:
                finish();
               break;
           case R.id.save_later_released:
               finish();
               break;
       }
    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_homework_release);
    }

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void init() {
        ll_title = (RelativeLayout) findViewById(R.id.ll_title);
        record_layout = (RelativeLayout) findViewById(R.id.record_layout);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        back_content = (TextView) findViewById(R.id.back_content);
        record_tips = (TextView) findViewById(R.id.record_tips);
        time_esp = (TextView) findViewById(R.id.time_esp);
        save_later_released = (TextView) findViewById(R.id.save_later_released);
        homework_released = (TextView) findViewById(R.id.homework_released);
        record = (ImageView) findViewById(R.id.record);
        take_photo = (ImageView) findViewById(R.id.take_photo);
        media_layout = (LinearLayout) findViewById(R.id.media_layout);
        photos = (LinearLayout) findViewById(R.id.photos);
    }

    @Override
    public void setListener() {
        ll_title.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_title.setOnClickListener(this);
        record.setOnClickListener(this);
        take_photo.setOnClickListener(this);
        save_later_released.setOnClickListener(this);
        homework_released.setOnClickListener(this);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
}
