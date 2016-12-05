package littleq.mammoth.com.littleq.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseFragment;
import littleq.mammoth.com.littleq.ui.activity.UserInfoActivity;
import littleq.mammoth.com.littleq.ui.activity.UserLessonActivity;
import littleq.mammoth.com.littleq.ui.activity.UserSettingActivity;
import littleq.mammoth.com.littleq.user.Teacher;
import littleq.mammoth.com.littleq.utils.Constants;
import littleq.mammoth.com.littleq.widget.CircleImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentUser extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RelativeLayout[] rlArray = new RelativeLayout[4];
//    private TextView[] tvArray = new TextView[4];
//    private ImageView[] ivArray = new ImageView[4];
//    private ImageView[] ivArrayMore = new ImageView[4];
    private TextView tvUserShareDetail;
    private TextView tvUserShare;
    private ImageView ivMore;
    private CircleImageView userHead;
    private TextView userName;
    private TextView userDescribe;

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
        userHead = (CircleImageView) rootView.findViewById(R.id.user_img);
        getUserHead(userHead, Constants.AVATOR_FULL_PATH);
        ivMore = (ImageView) rootView.findViewById(R.id.iv_more);
        tvUserShareDetail = (TextView) rootView.findViewById(R.id.tv_user_share_detail);
        tvUserShare = (TextView) rootView.findViewById(R.id.tv_user_share);
        userName = (TextView) rootView.findViewById(R.id.user_name);
        userName.setText(Teacher.getInstance().getJsonTeacher().getTName());
        userDescribe = (TextView) rootView.findViewById(R.id.user_class);
        userDescribe.setText(Teacher.getInstance().getJsonTeacher().getTSign());
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
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentUser.this.getActivity() , UserInfoActivity.class);
                startActivity(intent);
            }
        });
        rlArray[0].setOnClickListener(lessonOnClickListener);

        rlArray[1].setOnClickListener(scoreOnClickListener);

        rlArray[2].setOnClickListener(walletOnClickListener);

        rlArray[3].setOnClickListener(settingOnClickListener);

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
            Intent intent = new Intent(FragmentUser.this.getActivity() , UserLessonActivity.class);
            startActivity(intent);
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
            Intent intent = new Intent(FragmentUser.this.getActivity() , UserSettingActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
}
