package littleq.mammoth.com.littleq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import littleq.mammoth.com.littleq.R;

/**
 * Created by User on 2016/10/31.
 */
public class LeftReView extends LinearLayout{


    public LeftReView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(context);
    }

    public LeftReView(Context context) {
        super(context);
        initViews(context);
    }

    public LeftReView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.main_top_title_left_ret,this);
    }

}
