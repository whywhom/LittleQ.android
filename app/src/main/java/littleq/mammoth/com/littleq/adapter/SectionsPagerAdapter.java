package littleq.mammoth.com.littleq.adapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.fragment.FragmentGrowup;
import littleq.mammoth.com.littleq.ui.fragment.FragmentHomework;
import littleq.mammoth.com.littleq.ui.fragment.FragmentNote;
import littleq.mammoth.com.littleq.ui.fragment.FragmentUser;

/**
 * Created by Hunter on 2016/9/18.
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter  extends FragmentPagerAdapter {
    private static final int FREGMENT_COUNT = 4;
    private Context context;
    public SectionsPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        context = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment f;
        switch(position){
            case 0:
                f = FragmentHomework.newInstance(position + 1);
                break;
            case 1:
                f = FragmentNote.newInstance(position + 1);
                break;
            case 2:
                f = FragmentGrowup.newInstance(position + 1);
                break;
            case 3:
                f = FragmentUser.newInstance(position + 1);
                break;
            default:
                f = FragmentHomework.newInstance(position + 1);
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return FREGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.toolbar_homework);
            case 1:
                return context.getString(R.string.toolbar_note);
            case 2:
                return context.getString(R.string.toolbar_growup);
            case 3:
                return context.getString(R.string.toolbar_user);
        }
        return null;
    }
}
