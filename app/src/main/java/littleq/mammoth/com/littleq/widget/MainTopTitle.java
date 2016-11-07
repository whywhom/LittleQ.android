package littleq.mammoth.com.littleq.widget;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.utils.ConversionValueUtils;

/**
 * Created by User on 2016/10/28.
 */
public class MainTopTitle extends RelativeLayout{

    public static final int LEFT_RET = 1<<0;
    public static final int LEFT_IMG = 1<<1;
    public static final int CENTER_CHARACTOR = 1<<2;
    public static final int RIGHT_CHARACTOR = 1<<3;
    public static final int RIGHT_IMG = 1<<4;
    public static final int RIGHT_IMG_CHARACTOR = 1<<5;
    private RelativeLayout top_title,title_left,title_center,title_right;

    private View leftView;
    private View centerView;
    private View rightView;

    private Context context;
    private Builder builder;

    public MainTopTitle(Context context) {
        super(context);
        initView(context);
    }

    public MainTopTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainTopTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.main_top_title,this);
        top_title = (RelativeLayout) view.findViewById(R.id.top_title);
        title_left = (RelativeLayout) view.findViewById(R.id.title_left);
        title_center = (RelativeLayout) view.findViewById(R.id.title_center);
        title_right = (RelativeLayout) view.findViewById(R.id.title_right);
    }

   public void setBuilder(Builder builder) {
       this.builder = builder;
       initViews();
   }

    private void initViews() {
        top_title.setBackgroundColor(context.getResources().getColor(builder.bgColor));
        initLeft();
        initCenter();
        initRight();
    }


    private void initLeft() {
        RelativeLayout.LayoutParams leftLayoutParams = null;
        switch (builder.left) {
            case LEFT_RET:
                leftView = new LeftReView(context);
                break;
            case LEFT_IMG:
                leftView = new ImageView(context);
                ((ImageView)leftView).setImageResource(builder.leftImg);
                ((ImageView)leftView).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                leftLayoutParams = new LayoutParams(ConversionValueUtils.dip2px(context,35),ConversionValueUtils.dip2px(context,35));
                leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;
        }

        if(leftView != null) {
            if(builder.leftOnClickListener != null) {
                leftView.setOnClickListener(builder.leftOnClickListener);
            }
            if(leftLayoutParams != null) {
                title_left.addView(leftView,leftLayoutParams);
            }else {
                title_left.addView(leftView);
            }
        }

    }


    private void initCenter() {
        RelativeLayout.LayoutParams centerLayoutParams = null;
        switch (builder.center) {
            case CENTER_CHARACTOR:
                centerView = new TextView(context);
                ((TextView)centerView).setText(builder.centerChar);
                ((TextView)centerView).setTextColor(context.getResources().getColor(builder.titleColor));
                ((TextView)centerView).setTextSize(TypedValue.COMPLEX_UNIT_SP ,18);
                centerLayoutParams = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                centerLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;
        }


        if(centerView != null) {
            if(builder.centerOnClickListener != null) {
                centerView.setOnClickListener(builder.centerOnClickListener);
            }
            if(centerLayoutParams != null) {
                title_center.addView(centerView,centerLayoutParams);
            }else {
                title_center.addView(centerView);
            }
        }
    }

   private  void initRight(){
       RelativeLayout.LayoutParams rightLayoutParams = null;
       switch (builder.right) {
           case RIGHT_IMG:
               rightView = new ImageView(context);
               ((ImageView)rightView).setImageResource(builder.rightImg);
               ((ImageView)rightView).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
               rightLayoutParams = new LayoutParams(ConversionValueUtils.dip2px(context,45),ConversionValueUtils.dip2px(context,45));
               rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
               break;
           case RIGHT_CHARACTOR:
               rightView = new TextView(context);
               ((TextView)rightView).setText(builder.rightChar);
               ((TextView)rightView).setTextColor(context.getResources().getColor(R.color.white));
               ((TextView)rightView).setTextSize(TypedValue.COMPLEX_UNIT_SP ,14);
               ((TextView)rightView).setGravity(Gravity.CENTER);
               rightLayoutParams = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
               rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
               break;
           case RIGHT_IMG_CHARACTOR:

               break;
       }


       if(rightView != null) {
           if(builder.rightOnClickListener != null) {
               rightView.setOnClickListener(builder.rightOnClickListener);
           }

           if(rightLayoutParams != null) {
               title_right.addView(rightView,rightLayoutParams);
           }else {
               title_right.addView(rightView);
           }
       }
   }


   public static class Builder{
       private int left;
       private  int center = CENTER_CHARACTOR;
       private int right;

       private int leftImg;
       private String leftChar;
       private String centerChar;
       private int centerImg;
       private int rightImg;
       private String rightChar;

       private int bgColor = R.color.default_main_top_title_bg;
       private int titleColor = android.R.color.white;


       private OnClickListener leftOnClickListener;
       private OnClickListener centerOnClickListener;
       private OnClickListener rightOnClickListener;

       public Builder(String centerChar) {
           this.centerChar = centerChar;
       }


       public Builder center(int val) {
           this.center = val;
           return  this;
       }

       public Builder centerImg(int val) {
           this.centerImg = val;
           return  this;
       }


       public Builder left(int val) {
           left = val;
           return  this;
       }

       public Builder right(int val) {
           right = val;
           return this;
       }


       public Builder leftImg(int val) {
           leftImg = val;
           return this;
       }

       public Builder leftChar(String val) {
           leftChar = val;
           return  this;
       }

       public Builder rightImg(int val) {
           rightImg = val;
           return this;
       }


       public Builder rightChar(String val) {
           rightChar = val;
           return  this;
       }


       public Builder bg(int val) {
           bgColor = val;
           return this;
       }

       public Builder titleColor(int val) {
           titleColor = val;
           return this;
       }


       public Builder leftOnClickListener(OnClickListener val) {
           leftOnClickListener = val;
           return this;
       }

       public Builder centerOnClickListener(OnClickListener val) {
           centerOnClickListener = val;
           return this;
       }


       public Builder rightOnClickListener(OnClickListener val) {
           rightOnClickListener = val;
           return this;
       }

   }


}
