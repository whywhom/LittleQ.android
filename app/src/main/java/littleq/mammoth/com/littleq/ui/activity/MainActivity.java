package littleq.mammoth.com.littleq.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.SectionsPagerAdapter;
import littleq.mammoth.com.littleq.interfaces.LittleQGetGradeInfo;
import littleq.mammoth.com.littleq.interfaces.LittleQGetLessonInfo;
import littleq.mammoth.com.littleq.interfaces.LittleQGetTeacherGCL;
import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.net.NetUtils;
import littleq.mammoth.com.littleq.presenter.MainPresenter;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.ui.controller.NetController;
import littleq.mammoth.com.littleq.user.GCLInfo;
import littleq.mammoth.com.littleq.user.GradeClassInfo;
import littleq.mammoth.com.littleq.user.LessonInfo;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.ToastAlone;
import littleq.mammoth.com.littleq.utils.gson.GCLForTeacher;
import littleq.mammoth.com.littleq.utils.gson.GradeInfo;
import littleq.mammoth.com.littleq.utils.gson.JsonLesson;
import littleq.mammoth.com.littleq.view.IMainView;
import littleq.mammoth.com.littleq.widget.CircleProgress;
import littleq.mammoth.com.littleq.widget.ProgressDialog;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements IMainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    public final static int MSG_GET_GRADE = 0;
    public final static int MSG_GET_LESSON = 1;
    public final static int MSG_GET_TEACHER_GCL = 2;//根据老师ID获取班级与课程设置信息
    public final static int MSG_GETINFO_OK = 3;//与服务器交互完成

    private static int currentFregmentIndex = 0;
    private int toolbar_res[][] = null;
    private RelativeLayout toolbar_rl[] = null;
    private ImageView toolbar_iv[] = null;
    private MainPresenter mainPresenter;
    private long mExitTime = 0;
    private NetController netController;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link SectionsPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private HashMap<String, String> map;
    private GetGCLListener getGCLListener = new GetGCLListener();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_GRADE:
                    CircleProgress.getInstance().showCircleBar(MainActivity.this,"欢迎光临小Q班级");
                    onGetGradeAndClass();
                    break;
                case MSG_GET_LESSON:
                    onGetLesson();
                    break;
                case MSG_GET_TEACHER_GCL:
                    onGetTeacherGCL();
                    break;
                case MSG_GETINFO_OK:
                    CircleProgress.getInstance().dismissCircleBar();
                    break;
            }
        }
    };

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_main);
    }


    @Override
    public void init() {
        mainPresenter = new MainPresenter(this);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setCurrentTitle(currentFregmentIndex);
//        setSupportActionBar(toolbar);
        initViewPager();
        initToolbar();
        initToolbarListener();
        registerLittleQReceiver();
        netController = new NetController(this);
        handler.sendEmptyMessage(MSG_GET_GRADE);
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    private void initViewPager() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainPresenter.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        //工具栏资源图标
        toolbar_res = new int[4][2];
        toolbar_res[0][0] = R.mipmap.tab_zy_s;
        toolbar_res[0][1] = R.mipmap.tab_zy_n;

        toolbar_res[1][0] = R.mipmap.tab_xzt_s;
        toolbar_res[1][1] = R.mipmap.tab_xzt_n;

        toolbar_res[2][0] = R.mipmap.tab_czs_s;
        toolbar_res[2][1] = R.mipmap.tab_czs_n;

        toolbar_res[3][0] = R.mipmap.tab_wo_s;
        toolbar_res[3][1] = R.mipmap.tab_wo_n;

        toolbar_rl = new RelativeLayout[4];
        toolbar_rl[0] = (RelativeLayout) findViewById(R.id.rl_homework);
        toolbar_rl[1] = (RelativeLayout) findViewById(R.id.rl_note);
        toolbar_rl[2] = (RelativeLayout) findViewById(R.id.rl_growup);
        toolbar_rl[3] = (RelativeLayout) findViewById(R.id.rl_user);

        toolbar_iv = new ImageView[4];
        toolbar_iv[0] = (ImageView) findViewById(R.id.iv_homework);
        toolbar_iv[1] = (ImageView) findViewById(R.id.iv_note);
        toolbar_iv[2] = (ImageView) findViewById(R.id.iv_growup);
        toolbar_iv[3] = (ImageView) findViewById(R.id.iv_user);
        mainPresenter.initToolbar(currentFregmentIndex);
    }

    private void initToolbarListener() {
        toolbar_rl[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(0);
            }
        });
        toolbar_rl[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(1);
            }
        });
        toolbar_rl[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(2);
            }
        });
        toolbar_rl[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(3);
            }
        });
        toolbar_iv[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(0);
            }
        });
        toolbar_iv[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(1);
            }
        });
        toolbar_iv[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(2);
            }
        });
        toolbar_iv[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.initToolbarListener(3);
            }
        });
    }

    @Override
    public void setToolbar(int id) {
        for (int i = 0; i < toolbar_iv.length; i++) {
            toolbar_iv[i].setImageResource(toolbar_res[i][1]);
        }
        toolbar_iv[id].setImageResource(toolbar_res[id][0]);
        currentFregmentIndex = id;
    }

    @Override
    public void setCurrentItem(int pos) {
        mViewPager.setCurrentItem(pos, true);
    }

    @Override
    public void setCurrentTitle(int pos) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, getString(R.string.action_exit), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onGetGradeAndClass() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.TIMESTAMP, Constants.getTimeStamp());
        netController.onGetGradeAndClassController(NetConstants.GRADE_CLASS_INFO, map
                , new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 200) {
                        Gson gson = new Gson();
                        GradeInfo gradeInfo = gson.fromJson(result, GradeInfo.class);
                        GradeClassInfo.getInstance().setGradeInfo(gradeInfo);
                        handler.sendEmptyMessage(MSG_GET_LESSON);
                    } else {
                        String msg = jsonObject.optString("message");
                        ToastAlone.showShortToast(MainActivity.this, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void onGetLesson() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.TIMESTAMP, Constants.getTimeStamp());
        map.put(Constants.T_ID, String.valueOf(Teacher.getInstance().getJsonTeacher().getTId()));
        netController.onGetLessonController(NetConstants.LESSON_FOR_TEACHER, map
                , new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 200) {
                        Gson gson = new Gson();
                        JsonLesson jsonLesson = gson.fromJson(result, JsonLesson.class);
                        LessonInfo.getInstance().setJsonLesson(jsonLesson);
                        handler.sendEmptyMessage(MSG_GET_TEACHER_GCL);
                    } else {
                        String msg = jsonObject.optString("message");
                        ToastAlone.showShortToast(MainActivity.this, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void onGetTeacherGCL() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.TIMESTAMP, Constants.getTimeStamp());
        map.put(Constants.T_ID, String.valueOf(Teacher.getInstance().getJsonTeacher().getTId()));
        netController.onGetTeacherGCLController(NetConstants.GET_GRADE_CLASS_LESSON_FOR_TEACHER, map
                , new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 200) {
                        Gson gson = new Gson();
                        GCLForTeacher gclForTeacher = gson.fromJson(result, GCLForTeacher.class);
                        GCLInfo.getInstance().setGclForTeacher(gclForTeacher);
                        handler.sendEmptyMessage(MSG_GETINFO_OK);
                    } else {
                        String msg = jsonObject.optString("message");
                        ToastAlone.showShortToast(MainActivity.this, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.BR_BOOT)) {
                MainActivity.this.finish();
            }
        }
    }

    private class GetGCLListener implements NetUtils.NetUtilsListener {

        @Override
        public void success(int code, String msg) {
            if (code == 200) {
                Gson gson = new Gson();
                GCLForTeacher gclForTeacher = gson.fromJson(msg, GCLForTeacher.class);
                GCLInfo.getInstance().setGclForTeacher(gclForTeacher);
            } else {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void fail(int code, String msg) {
            ToastAlone.showShortToast(MainActivity.this, msg);
        }

        @Override
        public void cancel(String msg) {

        }
    }
}
