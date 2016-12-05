package littleq.mammoth.com.littleq.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.interfaces.LittleQPutTeacherInfo;
import littleq.mammoth.com.littleq.net.NetConstants;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.ui.controller.NetController;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.utils.FTP;
import littleq.mammoth.com.littleq.utils.ToastAlone;
import littleq.mammoth.com.littleq.utils.UILImageLoader;
import littleq.mammoth.com.littleq.utils.UILPauseOnScrollListener;
import littleq.mammoth.com.littleq.widget.CircleImageView;
import littleq.mammoth.com.littleq.widget.MainTopTitle;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static littleq.mammoth.com.littleq.net.NetConstants.REG_MODIFY_TEACHER_INFO;

/**
 * Created by wuhaoyong on 16/11/8.
 */
public class UserInfoActivity extends BaseActivity{
    private static final int MSG_SAVE = 0x00;
    private static final int MSG_EXIT = 0x01;
    private static final int MSG_FTP_UPLOAD_FILE_SUCCESS = 0x02;
    private static String TAG = UserInfoActivity.class.getSimpleName();
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    public static final int REQUEST_NAME = 1;
    public static final int REQUEST_MOBILE = 2;
    public static final int REQUEST_SCHOOL = 3;
    private int teacherSex = 0;//保存修改的性别信息
    private String teacherName = "";//保存修改的姓名信息
    private String teacherMobile = "";//保存修改的号码信息
    private String teacherBirth = "";//保存修改的生日信息
    private String teacherScName = "";//保存修改的学校信息
    private boolean avatorChanged = false;
    private NetController netController;

    private MainTopTitle title;
    private Context mContext;
    private CircleImageView avatorHead;
    private ImageView ivArrow;
    private RelativeLayout rlName;
    private TextView tvUserNameDetail;
    private RelativeLayout rlSex;
    private TextView tvUserSexDetail;
    private String strSex = "";
    private RelativeLayout rlBirth;
    private TextView tvUserBirthDetail;
    private String strData = "";
    private RelativeLayout rlMobile;
    private TextView tvUserMobileDetail;
    private RelativeLayout rlMySchool;
    private TextView tvUserSchoolDetail;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch(msg.what){
                case MSG_SAVE:
                    saveTeacherInfo();
                    break;
                case MSG_EXIT:
                    UserInfoActivity.this.finish();
                    break;
                case MSG_FTP_UPLOAD_FILE_SUCCESS:
                    updateTeacherInfo();
                    break;
            }
        }
    };

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void init() {
        mContext = this;
        netController = new NetController(this);
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.title_activity_userinfo));
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserInfoActivity.this.finish();
            }
        });
        builder.right(MainTopTitle.RIGHT_CHARACTOR).rightChar(getString(R.string.common_save));
        builder.rightOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(MSG_SAVE);
            }
        });
        title.setBuilder(builder);
        ivArrow = (ImageView)findViewById(R.id.iv_head_select);
        avatorHead = (CircleImageView)findViewById(R.id.iv_head);
        getUserHead(avatorHead, Constants.AVATOR_FULL_PATH);
        rlName = (RelativeLayout)findViewById(R.id.ll_name);
        tvUserNameDetail = (TextView)findViewById(R.id.tv_user_name_detail);
        tvUserNameDetail.setText(Teacher.getInstance().getJsonTeacher().getTName());
        rlSex = (RelativeLayout)findViewById(R.id.rl_sex);
        tvUserSexDetail = (TextView)findViewById(R.id.tv_user_sex_detail);
        String strSex = Teacher.getInstance().getJsonTeacher().getTSex();
        if(strSex.equals("1")) {
            tvUserSexDetail.setText(getString(R.string.userinfo_sex_male));
        } else if(strSex.equals("2")){
            tvUserSexDetail.setText(getString(R.string.userinfo_sex_female));
        } else {
            tvUserSexDetail.setText(getString(R.string.userinfo_sex_unknown));
        }
        rlBirth = (RelativeLayout)findViewById(R.id.rl_birth);
        tvUserBirthDetail = (TextView)findViewById(R.id.tv_user_birth_detail);
        tvUserBirthDetail.setText(Teacher.getInstance().getJsonTeacher().getTBirth());
        rlMobile = (RelativeLayout)findViewById(R.id.rl_mobile);
        tvUserMobileDetail = (TextView)findViewById(R.id.tv_user_mobile_detail);
        tvUserMobileDetail.setText(Teacher.getInstance().getJsonTeacher().getTPhone());
        rlMySchool = (RelativeLayout)findViewById(R.id.rl_myschool);
        tvUserSchoolDetail = (TextView)findViewById(R.id.tv_user_myschool_detail);
        tvUserSchoolDetail.setText(Teacher.getInstance().getJsonTeacher().getTScName());
    }
    private void getUserHead(CircleImageView userHead, String avatorPath) {
        File f = new File(avatorPath);
        if(f.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(avatorPath);
            if (bitmap != null) {
                userHead.setImageBitmap(bitmap);
            }
        }
    }
    @Override
    public void setListener() {
        avatorHead.setOnClickListener(onChangeAvator);
        ivArrow.setOnClickListener(onChangeAvator);
        rlName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( UserInfoActivity.this , EditUserInfoActivity.class);
                intent.putExtra("title",getString(R.string.modify_name));
                intent.putExtra("data",tvUserNameDetail.getText().toString());
                startActivityForResult(intent,REQUEST_NAME);
            }
        });
        rlSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSex = tvUserSexDetail.getText().toString();
                showSexSelDialog();
            }
        });
        rlBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = string2calendar(tvUserBirthDetail.getText().toString());
                showDatePickDlg(calendar);
            }
        });

        rlMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( UserInfoActivity.this , EditUserInfoActivity.class);
                intent.putExtra("title",getString(R.string.modify_mobile));
                intent.putExtra("data",tvUserMobileDetail.getText().toString());
                startActivityForResult(intent,REQUEST_MOBILE);
            }
        });
        rlMySchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( UserInfoActivity.this , SchoolSearchActivity.class);
                intent.putExtra("title",getString(R.string.search_school));
                intent.putExtra("data",tvUserSchoolDetail.getText().toString());
                startActivityForResult(intent,REQUEST_SCHOOL);
            }
        });
    }
    private Calendar string2calendar(String data){
        String pattern="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        Calendar calendar = null;
        try {
            Date date=dateFormat.parse(data);
            calendar=Calendar.getInstance();
            calendar.setTime(date);
//            System.out.println(calendar.get(Calendar.YEAR));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return calendar;
    }
    private void showSexSelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择性别");
        final String[] sex = {"男", "女"};
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合
         * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */
        int id = 0;
        if(strSex.equals(sex[1])){
            id = 1;
        }
        builder.setSingleChoiceItems(sex, id, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(UserInfoActivity.this, "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
                strSex = sex[which];
                teacherSex = which+1;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvUserSexDetail.setText(strSex);
                Teacher.getInstance().getJsonTeacher().setTSex(String.valueOf(teacherSex));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    private void showDatePickDlg(Calendar calendar) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(UserInfoActivity.this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                UserInfoActivity.this.mEditText.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                int showMonth = monthOfYear+1;
                strData = year + "-" + showMonth + "-" + dayOfMonth;
                teacherBirth = strData;
                tvUserBirthDetail.setText(strData);
                Teacher.getInstance().getJsonTeacher().setTBirth(strData);
            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        String modifyResult = data.getStringExtra("modify");
        switch(requestCode){
            case REQUEST_NAME:
                if(modifyResult.isEmpty()){
                    return;
                }
                teacherName = modifyResult;
                Teacher.getInstance().getJsonTeacher().setTName(teacherName);
                tvUserNameDetail.setText(modifyResult);
                break;
            case REQUEST_MOBILE:
                if(modifyResult.isEmpty()){
                    return;
                }
                teacherMobile = modifyResult;
                Teacher.getInstance().getJsonTeacher().setTPhone(teacherMobile);
                tvUserMobileDetail.setText(modifyResult);
                break;
            case REQUEST_SCHOOL:
                teacherScName = modifyResult;
                Teacher.getInstance().getJsonTeacher().setTScName(teacherScName);
                tvUserMobileDetail.setText(teacherScName);
                break;
        }
    }

    View.OnClickListener onChangeAvator = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            initImageLoader(UserInfoActivity.this);
            initGalleryFinal();
        }
    };
    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    private void initGalleryFinal(){
        ThemeConfig themeConfig = null;
        //配置功能
        themeConfig = ThemeConfig.GREEN;
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0xF8, 0xF8, 0xF8))
                .setTitleBarTextColor(Color.BLACK)
                .setTitleBarIconColor(Color.BLACK)
                .setFabNornalColor(Color.RED)
                .setFabPressedColor(Color.BLUE)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
                .setIconBack(R.mipmap.ic_action_previous_item)
                .setIconRotate(R.mipmap.ic_action_repeat)
                .setIconCrop(R.mipmap.ic_action_crop)
                .setIconCamera(R.mipmap.ic_action_camera)
                .build();
        themeConfig = theme;

        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        UILImageLoader imageLoader;
        PauseOnScrollListener pauseOnScrollListener = null;
        imageLoader = new UILImageLoader();
        pauseOnScrollListener = new UILPauseOnScrollListener(false, true);
        boolean muti = true;
        int maxSize = 1;
        functionConfigBuilder.setMutiSelectMaxSize(maxSize);
        final boolean mutiSelect = muti;
        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);
//        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        final FunctionConfig functionConfig = functionConfigBuilder.build();


        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .setNoAnimcation(true)
                .build();
        GalleryFinal.init(coreConfig);
        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
    }
    private void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                for(PhotoInfo p:resultList) {
                    String path = p.getPhotoPath();
                    Log.d(TAG, path);
                    Bitmap photo = BitmapFactory.decodeFile(path);
                    Constants.setAvatorBitmap(UserInfoActivity.this,photo);
                    setHeadImage();
                }
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(UserInfoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
    private void setHeadImage(){
        avatorChanged = true;
        Bitmap bitmap = Constants.getAvatorBitmap();
        if(bitmap != null) {
            avatorHead.setImageBitmap(bitmap);
        }
    }

    private void saveTeacherInfo() {
        if(avatorChanged) {
            onUpdateAvatorToFTP();
        } else{
            updateTeacherInfo();
        }
    }

    private void onUpdateAvatorToFTP() {
        //startMainActivity();
        final String url = Teacher.getInstance().getJsonTeacher().getTHeadurl();
        String pattern="yyyy/MM/dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        String pattern2="yyyyMMddHHmmss";
        SimpleDateFormat dateFormat2=new SimpleDateFormat(pattern2);
        Date currentDate = new Date();
        String currentTime = dateFormat.format(currentDate);
        String currentTime2 = dateFormat2.format(currentDate);
        final String currentTime3 = currentTime2+Constants.get3Random()+".png";
        Constants.copyFile(Constants.AVATOR_FULL_PATH, Constants.TEMP_PATH+"/"+currentTime3);
        final String updateFilePath = "Image/"+currentTime+"/";
//        if(url != null && !url.isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 上传
                    File file = new File(Constants.TEMP_PATH+"/"+currentTime3);
                    try {

                        //单文件上传
                        new FTP().uploadSingleFile(file, updateFilePath ,new FTP.UploadProgressListener(){

                            @Override
                            public void onUploadProgress(String currentStep,long uploadSize,File file) {
                                // TODO Auto-generated method stub
                                Log.d(TAG, currentStep);
                                if(currentStep.equals(Constants.FTP_UPLOAD_SUCCESS)){
                                    Log.d(TAG, "-----update--successful");
                                    Teacher.getInstance().getJsonTeacher().setTHeadurl(updateFilePath
                                            +currentTime3);
                                    avatorChanged = false;
                                    handler.sendEmptyMessage(MSG_FTP_UPLOAD_FILE_SUCCESS);
                                } else if(currentStep.equals(Constants.FTP_UPLOAD_LOADING)){
                                    long fize = file.length();
                                    float num = (float)uploadSize / (float)fize;
                                    int result = (int)(num * 100);
                                    Log.d(TAG, "-----update---"+result + "%");
                                }
                            }
                        });
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }).start();
    }
    private void updateTeacherInfo() {
        HashMap<String, Object> map = Teacher.getInstance().getTeacherMap();
        map.put(Constants.TIMESTAMP, Constants.getTimeStamp());
        map.put(Constants.IS_SAVE,1);
        netController.onGetTeacherGCLController(NetConstants.REG_MODIFY_TEACHER_INFO, map
                , new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 200) {
                        handler.sendEmptyMessage(MSG_EXIT);
                    } else {
                        String msg = jsonObject.optString("message");
                        ToastAlone.showShortToast(UserInfoActivity.this, msg);
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
}
