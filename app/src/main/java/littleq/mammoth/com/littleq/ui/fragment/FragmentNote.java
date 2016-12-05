package littleq.mammoth.com.littleq.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.BaseFragment;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentNote extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private LinearLayout title;
    private TextView tvMessage;
    private TextView tvContact;
    private boolean bMsgClicked = true;
    private boolean bContactClicked = false;
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
        title = (LinearLayout)rootView.findViewById(R.id.title);
        tvMessage = (TextView)rootView.findViewById(R.id.tv_message);
        tvMessage.setBackgroundResource(R.drawable.btn_border_full);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvMessage.setTextColor(getResources().getColor(R.color.default_main_top_title_bg,null));
        } else{
            tvMessage.setTextColor(getResources().getColor(R.color.default_main_top_title_bg));
        }
        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bMsgClicked){
                    bMsgClicked = true;
                    bContactClicked = false;
                    tvMessage.setBackgroundResource(R.drawable.btn_border_full);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tvMessage.setTextColor(getResources().getColor(R.color.default_main_top_title_bg,null));
                    } else{
                        tvMessage.setTextColor(getResources().getColor(R.color.default_main_top_title_bg));
                    }
                    tvContact.setBackgroundResource(R.drawable.btn_border);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tvContact.setTextColor(getResources().getColor(R.color.white,null));
                    } else{
                        tvContact.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });
        tvContact = (TextView)rootView.findViewById(R.id.tv_contact);
        tvContact.setBackgroundResource(R.drawable.btn_border);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvContact.setTextColor(getResources().getColor(R.color.white,null));
        } else{
            tvContact.setTextColor(getResources().getColor(R.color.white));
        }
        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bContactClicked) {
                    bContactClicked = true;
                    bMsgClicked = false;
                    tvMessage.setBackgroundResource(R.drawable.btn_border);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tvMessage.setTextColor(getResources().getColor(R.color.white, null));
                    } else {
                        tvMessage.setTextColor(getResources().getColor(R.color.white));
                    }
                    tvContact.setBackgroundResource(R.drawable.btn_border_full);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tvContact.setTextColor(getResources().getColor(R.color.default_main_top_title_bg, null));
                    } else {
                        tvContact.setTextColor(getResources().getColor(R.color.default_main_top_title_bg));
                    }
                }
            }
        });
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
