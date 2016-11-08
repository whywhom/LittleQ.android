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
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentNote extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private MainTopTitle title;
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
        title = (MainTopTitle)rootView.findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.toolbar_note));
        title.setBuilder(builder);
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
