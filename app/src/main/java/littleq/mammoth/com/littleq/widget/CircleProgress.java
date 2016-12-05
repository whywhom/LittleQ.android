package littleq.mammoth.com.littleq.widget;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Hunter on 2016/11/30.
 */

public class CircleProgress {
    private static CircleProgress instance = null;
    private Dialog dialog;
    private CircleProgress(){
    }

    public static CircleProgress getInstance() {
        if (instance == null) {
            synchronized (CircleProgress.class) {
                if (instance == null) {
                    instance = new CircleProgress();
                }
            }
        }
        return instance;
    }
    public void showCircleBar(Context ctx, String tip) {
        dialog= ProgressDialog.createDialog(ctx, tip);
        dialog.show();
    }
    public void dismissCircleBar() {
        if(dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
    }
}
