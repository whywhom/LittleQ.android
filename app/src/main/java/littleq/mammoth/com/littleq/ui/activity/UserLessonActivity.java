package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.LessonAdapter;
import littleq.mammoth.com.littleq.interfaces.ILessonListener;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.user.LessonInfo;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.widget.CircleImageView;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserLessonActivity extends BaseActivity implements ILessonListener {
    private static String TAG = UserLessonActivity.class.getSimpleName();

    private MainTopTitle title;
    private Context mContext;
    private CircleImageView avatorHead;
    private ImageView ivArrow;

    private ImageView ivLessonDirector;

    private TextView tvLogout;

    private ListView lvLesson;
    private LessonAdapter lessonAdapter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_lesson);
    }

    @Override
    public void init() {
        mContext = this;
        title = (MainTopTitle) findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.user_lesson_class));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLessonActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        lvLesson = (ListView) findViewById(R.id.lv_lesson);
        lessonAdapter = new LessonAdapter(this, LessonInfo.getInstance().getJsonLesson().getData());
        lvLesson.setAdapter(lessonAdapter);
        ivLessonDirector = (ImageView) findViewById(R.id.iv_lesson_director);

        if ("0".compareTo("" + SPUtils.get(UserLessonActivity.this, Constants.STP_IS_DIRECTOR, Integer.valueOf(0))) == 0) {
            ivLessonDirector.setTag(Integer.valueOf(0));
            ivLessonDirector.setImageResource(R.mipmap.off);
        } else {
            ivLessonDirector.setTag(Integer.valueOf(1));
            ivLessonDirector.setImageResource(R.mipmap.on);
        }
    }

    @Override
    public void setListener() {

        lessonAdapter.setListener(this);
        ivLessonDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(ivLessonDirector, Constants.STP_IS_DIRECTOR);
            }
        });
    }

    private void updateData(ImageView iv, String strSP) {
        if ("0".compareTo("" + iv.getTag()) == 0) {
            iv.setTag(Integer.valueOf(1));
            iv.setImageResource(R.mipmap.on);
            SPUtils.put(UserLessonActivity.this, strSP, Integer.valueOf(1));
        } else {
            iv.setTag(Integer.valueOf(0));
            iv.setImageResource(R.mipmap.off);
            SPUtils.put(UserLessonActivity.this, strSP, Integer.valueOf(0));
        }
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    @Override
    public void onItemClickListener(int i, Object obj) {
        String lessonName = LessonInfo.getInstance().getJsonLesson().getData().get(i).getCourseName();
        int lessonId = LessonInfo.getInstance().getJsonLesson().getData().get(i).getCourseId();
        Intent intent = new Intent(UserLessonActivity.this, UserClassActivity.class);
        intent.putExtra("title",lessonName);
        intent.putExtra("classid",lessonId);
        startActivity(intent);
    }
}
