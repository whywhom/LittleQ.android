package littleq.mammoth.com.littleq.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.presenter.FragmentHPresenter;
import littleq.mammoth.com.littleq.ui.BaseFragment;
import littleq.mammoth.com.littleq.ui.activity.BuzhiActivity;
import littleq.mammoth.com.littleq.ui.activity.PigaiActivity;
import littleq.mammoth.com.littleq.utils.NoDoubleClickListener;
import littleq.mammoth.com.littleq.view.IHomeworkView;
import littleq.mammoth.com.littleq.widget.CircleView;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentHomework extends BaseFragment implements IHomeworkView{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentHPresenter fragmentHPresenter;
    private MainTopTitle title;
    private CircleView mCircleView;

    public static FragmentHomework newInstance(int sectionNumber) {
        FragmentHomework fragment = new FragmentHomework();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentHomework() {
        fragmentHPresenter = new FragmentHPresenter(this);
    }

    @Override
    public void loadXml() {
        setLayoutId(R.layout.fragment_homework);
    }

    @Override
    public void init() {
        title = (MainTopTitle)rootView.findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.toolbar_homework));
        builder.right(MainTopTitle.RIGHT_IMG).rightImg(R.mipmap.index_alarm_n);
        title.setBuilder(builder);
        ImageButton btnBuzhizuoye = (ImageButton)rootView.findViewById(R.id.ib_buzhizuoye);
        btnBuzhizuoye.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                Intent intent = new Intent(FragmentHomework.this.getActivity() , BuzhiActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btnPigaizuoye = (ImageButton)rootView.findViewById(R.id.ib_pigaizuoye);
        btnPigaizuoye.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                Intent intent = new Intent(FragmentHomework.this.getActivity() , PigaiActivity.class);
                startActivity(intent);
            }
        });

        mCircleView = (CircleView) rootView.findViewById(R.id.score);

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
}
