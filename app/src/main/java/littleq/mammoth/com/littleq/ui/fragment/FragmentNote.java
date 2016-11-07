package littleq.mammoth.com.littleq.ui.fragment;

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

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentNote extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView tvTitle;
    private RelativeLayout rlTitle;
    private ImageView ivRight;
    public static FragmentNote newInstance(int sectionNumber) {
        FragmentNote fragment = new FragmentNote();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentNote() {
    }

    @Override
    public void loadXml() {
        setLayoutId(R.layout.fragment_note);
    }

    @Override
    public void init() {
        rlTitle = (RelativeLayout) rootView.findViewById(R.id.littleq_title);
        rlTitle.setBackgroundResource(R.color.colorTitle);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvTitle.setText(R.string.toolbar_note);
        ivRight = (ImageView) rootView.findViewById(R.id.iv_right);
        ivRight.setVisibility(View.GONE);
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
