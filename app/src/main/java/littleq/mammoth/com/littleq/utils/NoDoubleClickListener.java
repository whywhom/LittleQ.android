package littleq.mammoth.com.littleq.utils;

import android.view.View;

import java.util.Calendar;


/**
 * Created by why on 2016/9/26.
 */

public abstract  class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(view);
        }
    }
    protected  abstract void onNoDoubleClick(View view);
}
