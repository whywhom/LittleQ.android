package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.interfaces.LittleQPutTeacherGCL;
import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.ui.controller.NetController;
import littleq.mammoth.com.littleq.user.GCLInfo;
import littleq.mammoth.com.littleq.user.GradeClassInfo;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.ToastAlone;
import littleq.mammoth.com.littleq.utils.gson.ClassInfoList;
import littleq.mammoth.com.littleq.utils.gson.Grade;
import littleq.mammoth.com.littleq.utils.gson.GCLForTeacher;
import littleq.mammoth.com.littleq.widget.CircleImageView;
import littleq.mammoth.com.littleq.widget.MainTopTitle;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserClassActivity extends BaseActivity {
    private static final int MSG_GET_GCL = 0;
    private static String TAG = UserClassActivity.class.getSimpleName();

    private MainTopTitle title;
    private Context mContext;
    private CircleImageView avatorHead;
    private ImageView ivArrow;

    private GradeInner[] gradeInnerClases;
    private LinearLayout llAllGrade;
    private LayoutInflater inflater;

    private ImageView ivLessonDirector;

    private TextView tvLogout;

    private String[] gradeArray;

    private NetController netController;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private Intent intent;
    private String className;
    private int classId;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch(msg.what){
                case MSG_GET_GCL:
                    onGetTeacherGCL();
                    break;
            }
        }
    };

    @Override
    public void getIntentData(Bundle savedInstanceState) {
        intent = getIntent();
        if(intent != null){
            className = intent.getStringExtra("title");
            classId = intent.getIntExtra("classid",0);
            if(className == null || classId == 0){
                Toast.makeText(UserClassActivity.this,"获取信息错误",Toast.LENGTH_SHORT).show();
                UserClassActivity.this.finish();
                return;
            }
        }
    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_class);
    }

    @Override
    public void init() {
        mContext = this;
        netController = new NetController(this);
        title = (MainTopTitle) findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(className);
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.right(MainTopTitle.RIGHT_CHARACTOR).rightChar(getString(R.string.user_class_title_save));
        builder.leftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserClassActivity.this.finish();
            }
        });
        builder.rightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save
                saveClassInfoForTeacher();
            }
        });
        title.setBuilder(builder);
        initLayout();
        ivLessonDirector = (ImageView) findViewById(R.id.iv_lesson_director);

    }

    private void initLayout() {
        llAllGrade = (LinearLayout) findViewById(R.id.ll_all_grade);
        inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        List<Grade> gradeList = GradeClassInfo.getInstance().getGradeInfo().getData();
        int gradeLen = gradeList.size();
        gradeInnerClases = new GradeInner[gradeLen];
        for(int i=0;i<gradeLen;i++){
            GradeInner gradeInnerChild = new GradeInner();
            List<ClassInfoList> classInfoList = gradeList.get(i).getClassInfoList();

            gradeInnerChild.view = inflater.inflate(R.layout.grade_item, null);
            gradeInnerChild.expend = false;
            gradeInnerChild.tvUserGradeItem = (TextView) gradeInnerChild.view.findViewById(R.id.tv_user_grade_item);
            gradeInnerChild.llClassItem = (LinearLayout) gradeInnerChild.view.findViewById(R.id.ll_class_item);
            gradeInnerChild.ivUserGradeItemMore = (ImageView) gradeInnerChild.view.findViewById(R.id.iv_user_grade_item_more);
            gradeInnerChild.rlUserGrade = (RelativeLayout) gradeInnerChild.view.findViewById(R.id.rl_user_grade);
            gradeInnerChild.tvUserGradeItem.setText(gradeList.get(i).getGName());
            llAllGrade.addView(gradeInnerChild.view);
            gradeInnerClases[i] = gradeInnerChild;
            int classLen = classInfoList.size();
            gradeInnerChild.classInnerArray = new ClassInner[classLen];
            gradeInnerChild.llClassItem = (LinearLayout) gradeInnerChild.view.findViewById(R.id.ll_class_item);
            gradeInnerChild.llClassItem.removeAllViews();
            for(int j=0;j<classLen;j++){
                gradeInnerChild.classInnerArray[j] = new ClassInner();
                LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                gradeInnerChild.classInnerArray[j].llClass = new LinearLayout(this);
                linearLp.setMargins(5, 5, 5, 5);
                gradeInnerChild.classInnerArray[j].llClass.setOrientation(LinearLayout.VERTICAL);
                gradeInnerChild.classInnerArray[j].llClass.setTag(i);
                gradeInnerChild.llClassItem.addView(gradeInnerChild.classInnerArray[j].llClass, linearLp);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                gradeInnerChild.classInnerArray[j].textView = new TextView(this);
                if(gradeInnerChild.classInnerArray[j].bSelected == false){
                    gradeInnerChild.classInnerArray[j].textView.setBackgroundResource(R.drawable.bg_banjixuanze_n);
                    gradeInnerChild.classInnerArray[j].textView.setTextColor(getResources().getColor(R.color.text_color_default));
                } else{
                    gradeInnerChild.classInnerArray[j].textView.setBackgroundResource(R.drawable.bg_banjixuanze_h);
                    gradeInnerChild.classInnerArray[j].textView.setTextColor(getResources().getColor(R.color.white));
                }
                gradeInnerChild.classInnerArray[j].textView.setText(classInfoList.get(j).getCNameClass());
                gradeInnerChild.classInnerArray[j].textView.setPadding(15, 15, 15, 15);
                gradeInnerChild.classInnerArray[j].textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                gradeInnerChild.classInnerArray[j].llClass.addView(gradeInnerChild.classInnerArray[j].textView, lp);
                gradeInnerChild.classInnerArray[j].llClass.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });

            }
            initGrade(i);
        }
        initClass();
    }
    private void initClass() {
        int gLen = gradeInnerClases.length;
        for(int i=0;i<gLen;i++) {
            List<List<Integer>> gclInfo = GCLInfo.getInstance().getGclForTeacher().getJsonStr();
            int m = gclInfo.size();
            for (int k = 0; k < m; k++) {
                List<Integer> gclInfo2 = gclInfo.get(k);
                if (gclInfo2.get(2) == classId) {
                    if (gclInfo2.get(0) == i + 1) {
                        int j = gclInfo2.get(1) - 1;
                        gradeInnerClases[i].classInnerArray[j].bSelected = true;
                        gradeInnerClases[i].classInnerArray[j].textView.setBackgroundResource(R.drawable.bg_banjixuanze_h);
                        gradeInnerClases[i].classInnerArray[j].textView.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        }
    }
    private void initGrade(int i) {
        //1
        if(gradeInnerClases[i].expend){
            gradeInnerClases[i].ivUserGradeItemMore.setImageResource(R.mipmap.disclosure_up);
            gradeInnerClases[i].llClassItem.setVisibility(View.VISIBLE);
        } else{
            gradeInnerClases[i].ivUserGradeItemMore.setImageResource(R.mipmap.disclosure_down);
            gradeInnerClases[i].llClassItem.setVisibility(View.GONE);
        }
    }
    private void initClass(int i, int j) {
        //1
        if(gradeInnerClases[i].classInnerArray[j].bSelected){
            gradeInnerClases[i].classInnerArray[j].textView.setBackgroundResource(R.drawable.bg_banjixuanze_h);
            gradeInnerClases[i].classInnerArray[j].textView.setTextColor(getResources().getColor(R.color.white));
        } else{
            gradeInnerClases[i].classInnerArray[j].textView.setBackgroundResource(R.drawable.bg_banjixuanze_n);
            gradeInnerClases[i].classInnerArray[j].textView.setTextColor(getResources().getColor(R.color.text_color_default));
        }
    }

    @Override
    public void setListener() {
        List<Grade> gradeList = GradeClassInfo.getInstance().getGradeInfo().getData();
        int gradeLen = gradeList.size();
        for(int i=0;i<gradeLen;i++){
            final int id = i;
            gradeInnerClases[i].rlUserGrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gradeInnerClases[id].expend = !gradeInnerClases[id].expend;
                    initGrade(id);
                }
            });
            List<ClassInfoList> classInfoList = gradeList.get(i).getClassInfoList();
            int classLen = classInfoList.size();
            final int superIndex = i;
            for(int j=0;j<classLen;j++){
                final int subIndex = j;
                gradeInnerClases[i].classInnerArray[j].textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gradeInnerClases[superIndex].classInnerArray[subIndex].bSelected =
                                !gradeInnerClases[superIndex].classInnerArray[subIndex].bSelected;
                        initClass(superIndex,subIndex);
                    }
                });
            }
        }
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
    private void saveClassInfoForTeacher() {
        List<List<Integer>> gclInfo = GCLInfo.getInstance().getGclForTeacher().getJsonStr();
        int gLen = gclInfo.size();
        for(int i=gLen-1;i>=0;i--) {
            if(gclInfo.get(i).get(2) == classId){
                gclInfo.remove(i);
            }
        }
        List<Grade> gradeList = GradeClassInfo.getInstance().getGradeInfo().getData();
        int gradeLen = gradeList.size();
        for(int i=0;i<gradeLen;i++){
            List<ClassInfoList> classInfoList = gradeList.get(i).getClassInfoList();
            int classLen = classInfoList.size();
            for(int j=0;j<classLen;j++){
                if(gradeInnerClases[i].classInnerArray[j].bSelected){
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(i+1);
                    temp.add(j+1);
                    temp.add(classId);
                    gclInfo.add(temp);
                }
            }
        }
        List<String> gclStr = new ArrayList<String>();
        for(int i=0;i<gclInfo.size();i++){
            List<Integer> child = gclInfo.get(i);
            String str = String.valueOf(child.get(0))+","+String.valueOf(child.get(1))+","+String.valueOf(child.get(2));
            gclStr.add(str);
        }
        gclStr = removeDuplicateWithOrder(gclStr);
        String gclInfoForTeacher = "";
        for(int i=0;i<gclStr.size();i++) {
            if(gclInfoForTeacher.isEmpty()){
                gclInfoForTeacher += gclStr.get(i);
            } else {
                gclInfoForTeacher += "|"+ gclStr.get(i);
            }
        }

//        {
//            if(gclInfoForTeacher.isEmpty()){
//                gclInfoForTeacher += str2;
//            } else{
//                gclInfoForTeacher += "|"+ str2;
//            }
//        }
        if(!gclInfoForTeacher.isEmpty()){
            onSaveTeacherGCL(gclInfoForTeacher);
        }
    }
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element)){
                newList.add(element);
            }
        }
        return newList;
    }
    public void onSaveTeacherGCL(String info){
        if(info == null || info.isEmpty()){
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.TIMESTAMP, Constants.getTimeStamp());
        map.put(Constants.T_ID, String.valueOf(Teacher.getInstance().getJsonTeacher().getTId()));
        map.put(Constants.JSON_STRING, info);
        netController.onGetTeacherGCLController(NetConstants.UPDATE_GRADE_CLASS_LESSON_FOR_TEACHER, map
            , new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 200) {
                        mHandler.sendEmptyMessage(MSG_GET_GCL);
                    } else {
                        String msg = jsonObject.optString("message");
                        ToastAlone.showShortToast(UserClassActivity.this, msg);
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
                    } else {
                        String msg = jsonObject.optString("message");
                        ToastAlone.showShortToast(UserClassActivity.this, msg);
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
    class GradeInner {
        View view;
        boolean expend = false;
        TextView tvUserGradeItem;
        LinearLayout llClassItem;
        ImageView ivUserGradeItemMore;
        RelativeLayout rlUserGrade;
        private String[] classArray;
        private ClassInner[] classInnerArray;
    }

    class ClassInner{
        View view;
        TextView textView;
        boolean bSelected = false;
        LinearLayout llClass;
    }
}
