package littleq.mammoth.com.littleq.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.model.CourseScoreModel;

/**
 * Created by User on 2016/11/4.
 */
public class CircleView extends RelativeLayout {

    private ViewPager viewPager;
    private ArrayList<View> views;

    private ScoreWidget[] mScoreWidgets = null;
    private CourseScoreModel[] models;
    private ViewGroup mGroup;

    private static final int[] dots = {R.mipmap.banner_dot_default,R.mipmap.banner_dot_selected};
    private ImageAdapter vpAdapter;
    private Context context;
//    private int mImageIndex = 0;

    private ImageView[] mImageViews = null;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void  init(Context context){
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.cycle_view, this);
        mGroup = (ViewGroup) findViewById(R.id.viewGroup);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());

        initData();
        ScoreWidget scoreWidgets = null;
        mScoreWidgets = new ScoreWidget[models.length];
        for(int i = 0; i<models.length; i++) {
            scoreWidgets = new ScoreWidget(context);
            scoreWidgets.setData(models[i]);
            mScoreWidgets[i] = scoreWidgets;
        }

        if(models.length > 1) {
            mImageViews = new ImageView[models.length];
            ImageView mImageView = null;
            for (int i = 0; i < models.length; i++) {
                mImageView = new ImageView(context);
                int imageParams = (int) (context.getResources().getDisplayMetrics().density * 10 + 0.5f);
                int imagePadding = (int) (context.getResources().getDisplayMetrics().density * 4 + 0.5f);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageParams, imageParams);
                params.bottomMargin = imagePadding;
                params.topMargin = imagePadding;
                params.leftMargin = imagePadding;
                params.rightMargin = imagePadding;
                mImageView.setLayoutParams(params);
//			mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
                mImageViews[i] = mImageView;
                if (i == 0) {
                    mImageViews[i].setBackgroundResource(dots[1]);
                } else {
                    mImageViews[i].setBackgroundResource(dots[0]);
                }
                mGroup.addView(mImageViews[i]);
            }
        }
        vpAdapter = new ImageAdapter(context,mScoreWidgets);
        viewPager.setAdapter(vpAdapter);
    }


    private void initData() {
        models = new CourseScoreModel[3];
        CourseScoreModel model = new CourseScoreModel();
        model.setDescription("一年级一班 语文成绩");

        ArrayList<ArrayList<Double>> lists = new ArrayList<ArrayList<Double>>();
        ArrayList yList = new ArrayList<Double>();
        yList.add(21.0);
        yList.add(40.0);
        yList.add(66.0);
        yList.add(30.0);
        yList.add(83.0);
        yList.add(90.0);
        model.setHighScores(yList);

        yList = new ArrayList<Double>();
        yList.add(41.0);
        yList.add(50.0);
        yList.add(86.0);
        yList.add(50.0);
        yList.add(93.0);
        yList.add(60.0);
        model.setLevelScores(yList);
//
        yList = new ArrayList<Double>();
        yList.add(71.0);
        yList.add(90.0);
        yList.add(56.0);
        yList.add(80.0);
        yList.add(73.0);
        yList.add(80.0);
        model.setLowScores(yList);


        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("1月");
        xRawDatas.add("2月");
        xRawDatas.add("3月");
        xRawDatas.add("4月");
        xRawDatas.add("5月");
        xRawDatas.add("6月");
        model.setxRawDatas(xRawDatas);
        model.setMaxValue(100);
        model.setAverageValue(20);

        models[0] = model;
        models[1] = model;
        models[2] = model;
    }


    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE){

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int index) {
//            mImageIndex = index;
            int realSize = mScoreWidgets != null ? mScoreWidgets.length : 0;
            final int realPos = index % realSize;
            mImageViews[realPos].setBackgroundResource(dots[1]);
            for (int i = 0; i < mImageViews.length; i++) {
                if (realPos != i) {
                    mImageViews[i].setBackgroundResource(dots[0]);
                }
            }

        }

    }


    private class ImageAdapter extends PagerAdapter {

        private ArrayList<ScoreWidget> mImageViewCacheList;

        private ScoreWidget[] mAdList;
        private Context mContext;

        public ImageAdapter(Context context, ScoreWidget[] adList) {
            mContext = context;
            mAdList = adList;
            mImageViewCacheList = new ArrayList<ScoreWidget>();
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            int realSize = mAdList != null ? mAdList.length : 0;
            if(realSize <= 0) {
                return null;
            }

            final int realPos = position % realSize;
            ScoreWidget imageView = mAdList[realPos];

//            ScoreWidget imageView = null;
            if (mImageViewCacheList.isEmpty()) {
                imageView = new ScoreWidget(mContext);
                imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                imageView.setData(models[realPos]);
            } else {
                imageView = mImageViewCacheList.remove(0);
            }


            imageView.setTag(realPos);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ScoreWidget view = (ScoreWidget) object;
            container.removeView(view);
            mImageViewCacheList.add(view);
        }

    }

}
