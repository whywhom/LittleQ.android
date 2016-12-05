package littleq.mammoth.com.littleq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.SchoolAdapter;
import littleq.mammoth.com.littleq.interfaces.ILessonListener;
import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.ui.controller.NetController;
import littleq.mammoth.com.littleq.user.School;
import littleq.mammoth.com.littleq.utils.gson.SchoolInfo;
import littleq.mammoth.com.littleq.utils.gson.SchoolList;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.ToastAlone;
import littleq.mammoth.com.littleq.widget.CircleProgress;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

public class SchoolSearchActivity extends BaseActivity  implements ILessonListener {
    private static final int MSG_PROCESS_SCHOOL = 0;
    private static final int MSG_GET_SCHOOL_LIST = 1;
    private MainTopTitle title;
    private String titleName;
    private EditText etModify;
    private String origName;
    private ImageView ivClear;
    private MainTopTitle.Builder builder;
    private RelativeLayout rlModifyName;
    private SchoolAdapter schoolAdapter;
    private NetController netController;
    private ListView schoolListView;
//    private ArrayList arraySchool;
    private List<SchoolInfo> listSchool = new ArrayList<SchoolInfo>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PROCESS_SCHOOL:
                    notifySchoolInputChange("");
                    CircleProgress.getInstance().dismissCircleBar();
                    break;
                case MSG_GET_SCHOOL_LIST:
                    CircleProgress.getInstance().showCircleBar(SchoolSearchActivity.this,"正在读取学校列表……");
                    getSchoolInfo();
                    break;
            }
        }
    };

    @Override
    public void getIntentData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            titleName = intent.getStringExtra("title");
            origName = intent.getStringExtra("data");
        }
    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_search_school);
    }

    @Override
    public void init() {
        netController = new NetController(this);
        title = (MainTopTitle) findViewById(R.id.title);
        builder = new MainTopTitle.Builder(getString(R.string.search_school));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("modify", "");
                // 设置结果，并进行传送
                SchoolSearchActivity.this.setResult(UserInfoActivity.REQUEST_SCHOOL, mIntent);
                SchoolSearchActivity.this.finish();
            }
        });
        title.setBuilder(builder);
        schoolListView = (ListView) findViewById(R.id.lv_school_list);
        schoolAdapter = new SchoolAdapter(this, listSchool);
        schoolListView.setAdapter(schoolAdapter);
        rlModifyName = (RelativeLayout) findViewById(R.id.rl_modify_name);
        etModify = (EditText) findViewById(R.id.et_user_edit);
        etModify.setHint(getString(R.string.userinfo_school_edithint));
        etModify.setText(origName);
        etModify.addTextChangedListener(watcher);
        ivClear = (ImageView) findViewById(R.id.iv_clear);
        handler.sendEmptyMessage(MSG_GET_SCHOOL_LIST);
    }

    @Override
    public void setListener() {
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etModify.setText("");
            }
        });
        schoolAdapter.setListener(this);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    private void getSchoolInfo() {
        HashMap<String, Object> map = Teacher.getInstance().getTeacherMap();
        map.put(Constants.TIMESTAMP, Constants.getTimeStamp());
        netController.onGetTeacherGCLController(NetConstants.GET_SCHOOL_INFO, map
                , new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.optInt("code");
                            if (code == 200) {
                                Gson gson = new Gson();
                                SchoolList schoolList = gson.fromJson(result, SchoolList.class);
                                School.getInstance().setSchoolList(schoolList);
                                handler.sendEmptyMessage(MSG_PROCESS_SCHOOL);
                            } else {
                                String msg = jsonObject.optString("message");
                                ToastAlone.showShortToast(SchoolSearchActivity.this, msg);
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

    //EditText中输入内容监视
    //TextWatcher中重写的三个方法在EditText中每输入一个字符都执行一遍
    TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etModify.getText() != null) {
                String input_info = etModify.getText().toString();
                notifySchoolInputChange(input_info);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void notifySchoolInputChange(String nameFilter) {
        SchoolList schoolList = School.getInstance().getSchoolList();
        if(schoolList == null){
            return;
        }
        List<SchoolInfo> schoolInfoList = School.getInstance().getSchoolList().getData();
        if(schoolInfoList == null){
            return;
        }
        int len = schoolInfoList.size();
        if(nameFilter == null  || nameFilter.isEmpty()){
            listSchool.clear();
            for (int i = 0; i < len; i++) {
                listSchool.add(schoolInfoList.get(i));
            }
        }else {
            listSchool.clear();
            for (int i = 0; i < len; i++) {
                String schoolName = schoolInfoList.get(i).getScName();
                if (schoolName.indexOf(nameFilter) >= 0) {
                    listSchool.add(schoolInfoList.get(i));
                }
            }
        }
        schoolAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int i,Object obj) {
        if(obj != null){
            SchoolInfo schoolInfo = (SchoolInfo)obj;
            Intent mIntent = new Intent();
            mIntent.putExtra("modify", schoolInfo.getScName());
            // 设置结果，并进行传送
            SchoolSearchActivity.this.setResult(UserInfoActivity.REQUEST_SCHOOL, mIntent);
            SchoolSearchActivity.this.finish();
        }
    }
}
