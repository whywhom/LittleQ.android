package littleq.mammoth.com.littleq.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseFragment;
import littleq.mammoth.com.littleq.ui.activity.UserInfoActivity;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentUser extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RelativeLayout[] rlArray = new RelativeLayout[4];
    private TextView[] tvArray = new TextView[4];
    private ImageView[] ivArray = new ImageView[4];
    private ImageView[] ivArrayMore = new ImageView[4];
    private TextView tvUserShareDetail;
    private TextView tvUserShare;
    private ImageView ivMore;

    public static FragmentUser newInstance(int sectionNumber) {
        FragmentUser fragment = new FragmentUser();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUser() {
    }

    @Override
    public void loadXml() {
        setLayoutId(R.layout.fragment_user);
    }

    @Override
    public void init() {
        rlArray[0] = (RelativeLayout) rootView.findViewById(R.id.ll_lesson_class);
        rlArray[1] = (RelativeLayout) rootView.findViewById(R.id.ll_score);
        rlArray[2] = (RelativeLayout) rootView.findViewById(R.id.ll_wallet);
        rlArray[3] = (RelativeLayout) rootView.findViewById(R.id.ll_setting);

        tvArray[0] = (TextView) rootView.findViewById(R.id.tv_lesson_class);
        tvArray[1] = (TextView) rootView.findViewById(R.id.tv_score);
        tvArray[2] = (TextView) rootView.findViewById(R.id.tv_wallet);
        tvArray[3] = (TextView) rootView.findViewById(R.id.tv_setting);

        ivArray[0] = (ImageView) rootView.findViewById(R.id.iv_lesson_class);
        ivArray[1] = (ImageView) rootView.findViewById(R.id.iv_score);
        ivArray[2] = (ImageView) rootView.findViewById(R.id.iv_wallet);
        ivArray[3] = (ImageView) rootView.findViewById(R.id.iv_setting);

        ivArrayMore[0] = (ImageView) rootView.findViewById(R.id.iv_lesson_class_more);
        ivArrayMore[1] = (ImageView) rootView.findViewById(R.id.iv_score_more);
        ivArrayMore[2] = (ImageView) rootView.findViewById(R.id.iv_wallet_more);
        ivArrayMore[3] = (ImageView) rootView.findViewById(R.id.iv_setting_more);

        ivMore = (ImageView) rootView.findViewById(R.id.iv_more);
        tvUserShareDetail = (TextView) rootView.findViewById(R.id.tv_user_share_detail);
        tvUserShare = (TextView) rootView.findViewById(R.id.tv_user_share);
    }

    @Override
    public void setListener() {
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentUser.this.getActivity() , UserInfoActivity.class);
                startActivity(intent);
            }
        });
        rlArray[0].setOnClickListener(lessonOnClickListener);
        tvArray[0].setOnClickListener(lessonOnClickListener);
        ivArray[0].setOnClickListener(lessonOnClickListener);
        ivArrayMore[0].setOnClickListener(lessonOnClickListener);

        rlArray[1].setOnClickListener(scoreOnClickListener);
        tvArray[1].setOnClickListener(scoreOnClickListener);
        ivArray[1].setOnClickListener(scoreOnClickListener);
        ivArrayMore[1].setOnClickListener(scoreOnClickListener);

        rlArray[2].setOnClickListener(walletOnClickListener);
        tvArray[2].setOnClickListener(walletOnClickListener);
        ivArray[2].setOnClickListener(walletOnClickListener);
        ivArrayMore[2].setOnClickListener(walletOnClickListener);

        rlArray[3].setOnClickListener(settingOnClickListener);
        tvArray[3].setOnClickListener(settingOnClickListener);
        ivArray[3].setOnClickListener(settingOnClickListener);
        ivArrayMore[3].setOnClickListener(settingOnClickListener);

        tvUserShareDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvUserShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    View.OnClickListener lessonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener scoreOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener walletOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener settingOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
}
