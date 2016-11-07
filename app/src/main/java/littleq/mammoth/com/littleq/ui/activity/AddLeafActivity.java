package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.utils.UILImageLoader;
import littleq.mammoth.com.littleq.utils.UILPauseOnScrollListener;


/**
 * Created by Hunter on 2016/10/25.
 */
public class AddLeafActivity extends BaseActivity {
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;

    private TextView tvTitle;
    private RelativeLayout rlTitle;
    private ImageView ivLeft;
    private List<String> mClassListData = new ArrayList<String>();
    private List<PhotoInfo> mAddListData = new ArrayList<PhotoInfo>();
    private LinearLayout linearClass;
    private LinearLayout linearAdd;
    private TextView releaseButton;

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_addleaf);
    }

    @Override
    public void init() {
        rlTitle = (RelativeLayout) findViewById(R.id.littleq_title);
        rlTitle.setBackgroundResource(R.color.colorTitle);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("添加一片树叶");
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivLeft.setImageResource(R.mipmap.back_arrow);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLeafActivity.this.finish();
            }
        });
        releaseButton = (TextView)findViewById(R.id.leaf_released);

        linearClass = (LinearLayout) findViewById(R.id.ll_class);
        linearAdd = (LinearLayout) findViewById(R.id.ll_add);
        initImageLoader(AddLeafActivity.this);
        initFresco();
        createClassLinearLayout();
        createAddLinearLayout(false);
    }

    @Override
    public void setListener() {
        releaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLeafActivity.this.finish();
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GalleryFinal.cleanCacheFile();
    }

    private void createClassLinearLayout() {
        mClassListData.clear();
        mClassListData.add("一年级（1）班");
        mClassListData.add("一年级（2）班");
        mClassListData.add("一年级（3）班");
        mClassListData.add("一年级（4）班");
        mClassListData.add("一年级（5）班");
        for (int i = 0; i < mClassListData.size(); i++) {
            LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout myLinear = new LinearLayout(this);
            linearLp.setMargins(5, 5, 5, 5);
            myLinear.setOrientation(LinearLayout.VERTICAL);
            myLinear.setTag(i);
            linearClass.addView(myLinear, linearLp);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.edittext);
            textView.setPadding(5, 5, 5, 5);
            textView.setTextColor(getResources().getColor(R.color.colorClass));
            textView.setText(mClassListData.get(i));
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            myLinear.addView(textView, lp);

            myLinear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                }
            });
        }
    }
    private void createAddLinearLayout(boolean update) {
        if(!update) {
            mAddListData.clear();
        }
        linearAdd.removeAllViews();
        int i = 0;
        for (i = 0; i < mAddListData.size(); i++) {
            LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout myLinear = new LinearLayout(this);
            linearLp.setMargins(5, 5, 5, 5);
            myLinear.setOrientation(LinearLayout.VERTICAL);
            myLinear.setTag(i);
            linearAdd.addView(myLinear, linearLp);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(160,160);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(getLoacalBitmap(Uri.parse(mAddListData.get(i).getPhotoPath())));
            imageView.setPadding(5, 5, 5, 5);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            myLinear.addView(imageView, lp);
            myLinear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                }
            });
        }
        ////add the last icon////
        LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout myLinear = new LinearLayout(this);
        linearLp.setMargins(5, 5, 5, 5);
        myLinear.setOrientation(LinearLayout.VERTICAL);
        myLinear.setTag(i);
        linearAdd.addView(myLinear, linearLp);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(160,160);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.add_leaf);
        imageView.setPadding(5, 5, 5, 5);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //照片拾取器
                initGalleryFinal();
            }
        });
        myLinear.addView(imageView, lp);

        myLinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });
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
    private void initGalleryFinal(){
        ThemeConfig themeConfig = null;
        //配置功能
        themeConfig = ThemeConfig.DARK;
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0xFF, 0x57, 0x22))
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
        int maxSize = 9;
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
//        ActionSheet.createBuilder(AddLeafActivity.this, getSupportFragmentManager())
//                .setCancelButtonTitle("取消(Cancel)")
//                .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)", "裁剪(Crop)", "编辑(Edit)")
//                .setCancelableOnTouchOutside(true)
//                .setListener(new ActionSheet.ActionSheetListener() {
//                    @Override
//                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
//
//                    }
//
//                    @Override
//                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
//                        String path = "/sdcard/pk1-2.jpg";
//                        switch (index) {
//                            case 0:
//                                if (mutiSelect) {
//                                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
//                                } else {
//                                    GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
//                                }
//                                break;
//                            case 1:
//                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
//                                break;
//                            case 2:
//                                if (new File(path).exists()) {
//                                    GalleryFinal.openCrop(REQUEST_CODE_CROP, functionConfig, path, mOnHanlderResultCallback);
//                                } else {
//                                    Toast.makeText(AddLeafActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
//                                }
//                                break;
//                            case 3:
//                                if (new File(path).exists()) {
//                                    GalleryFinal.openEdit(REQUEST_CODE_EDIT, functionConfig, path, mOnHanlderResultCallback);
//                                } else {
//                                    Toast.makeText(AddLeafActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                })
//                .show();
    }

    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(this, config);
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
//                if(mAddListData.size()>0) {
//                    mAddListData.remove(mAddListData.size() - 1);
//                }
                for(PhotoInfo p:resultList) {
                    mAddListData.add(p);
                }
                createAddLinearLayout(true);
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(AddLeafActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
    public Bitmap getLoacalBitmap(Uri url) {
        try {
            FileInputStream fis = new FileInputStream(url.getPath());
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
