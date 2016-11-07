package littleq.mammoth.com.littleq.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.SectionsPagerAdapter;
import littleq.mammoth.com.littleq.presenter.MainPresenter;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.view.IMainView;

public class MainActivity extends BaseActivity implements IMainView {
    private static int currentFregmentIndex = 0;
    private int toolbar_res[][] = null;
    private RelativeLayout toolbar_rl[] = null;
    private ImageView toolbar_iv[] = null;
    private MainPresenter mainPresenter;
    private long mExitTime = 0;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link SectionsPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


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

    private void initViewPager() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

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
    private void initToolbar(){
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
        for(int i=0;i<toolbar_iv.length;i++){
            toolbar_iv[i].setImageResource(toolbar_res[i][1]);
        }
        toolbar_iv[id].setImageResource(toolbar_res[id][0]);
        currentFregmentIndex = id;
    }
    @Override
    public void setCurrentItem(int pos){
        mViewPager.setCurrentItem(pos, true);
    }
    @Override
    public void setCurrentTitle(int pos){
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
}
