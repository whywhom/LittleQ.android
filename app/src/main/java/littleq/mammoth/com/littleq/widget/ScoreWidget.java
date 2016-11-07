package littleq.mammoth.com.littleq.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.model.CourseScoreModel;

/**
 * Created by User on 2016/11/2.
 */
public class ScoreWidget extends View{

    /**
     * 公共部分
     */
    private static final int CIRCLE_SIZE = 20;

    private static enum Linestyle
    {
        Line, Curve
    }

    private Context mContext;
    private Paint mPaint;
    private Resources res;
    private DisplayMetrics dm;

    /**
     * data
     */
    private Linestyle mStyle = Linestyle.Line;

    private int canvasHeight;
    private int canvasWidth;
    private int bheight = 0;
    private int blwidh;
    private boolean isMeasure = true;
    /**
     * Y轴最大值
     */
    private int maxValue;
    /**
     * Y轴间距值
     */
    private int averageValue;
    private int marginTop = 20;
    private int marginBottom = 40;

    /**
     * 曲线上总点数
     */
    private Point[] mPoints;

    private List<Point[]> listPoints;
    /**
     * 纵坐标值
     */
    private ArrayList<Double> yRawData;

    private ArrayList<ArrayList<Double>> listsRawData = new ArrayList<ArrayList<Double>>();
    /**
     * 横坐标值
     */
    private ArrayList<String> xRawDatas;
    private ArrayList<Integer> xList = new ArrayList<Integer>();// 记录每个x的值
    private int spacingHeight;

    private int[] levelColors = {android.R.color.holo_red_light,android.R.color.holo_green_light,android.R.color.holo_blue_light};

    public ScoreWidget(Context context)
    {
        this(context, null);
    }

    public ScoreWidget(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView()
    {
        this.res = mContext.getResources();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        marginTop = dip2px(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        if (isMeasure)
        {
            this.canvasHeight = getHeight();
            this.canvasWidth = getWidth();
            if (bheight == 0)
                bheight = (int) (canvasHeight - dip2px(45) );
            blwidh = dip2px(35);
            isMeasure = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint.setColor(res.getColor(R.color.line));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dip2px(1f));
        PathEffect effects = new DashPathEffect(new float[] { 1, 2, 4, 8}, 1);
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(effects);
        drawAllXLine(canvas);
        // 画直线（纵向）
        drawAllYLine(canvas);
        // 点的操作设置
        listPoints = getPoints();

        mPaint.setColor(res.getColor(R.color.line));
        mPaint.setStrokeWidth(dip2px(1.5f));

        if (mStyle == Linestyle.Curve)
        {
            mPaint.setStyle(Paint.Style.STROKE);
            drawScrollLine(canvas);
        }
        else
        {
            for(int i = 0;i< listPoints.size() ;i++) {
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(res.getColor(levelColors[i]));
                drawLine(canvas, listPoints.get(i));
                mPaint.setStyle(Paint.Style.FILL);
                for(int j = 0;j<listPoints.get(i).length;j++) {
                    canvas.drawCircle(listPoints.get(i)[j].x, listPoints.get(i)[j].y, CIRCLE_SIZE / 2, mPaint);
                }

            }

            }

        }





    /**
     *  画所有横向表格，包括X轴
     */
    private void drawAllXLine(Canvas canvas)
    {
        for (int i = 0; i < spacingHeight +1 ; i++)
        {
            canvas.drawLine(blwidh, bheight - (bheight / spacingHeight) * i + marginTop, (canvasWidth - blwidh),
                    bheight - (bheight / spacingHeight) * i + marginTop, mPaint);// Y坐标
            drawText(String.valueOf(averageValue * i), blwidh / 2 -20, bheight - (bheight / spacingHeight) * i + marginTop,
                    canvas);
        }
    }

    /**
     * 画所有纵向表格，包括Y轴
     */
    private void drawAllYLine(Canvas canvas)
    {
        for (int i = 0; i < yRawData.size(); i++)
        {
            xList.add(blwidh + (canvasWidth - blwidh) / yRawData.size() * i);
            canvas.drawLine(blwidh + (canvasWidth - blwidh) / yRawData.size() * i, 0, blwidh
                    + (canvasWidth - blwidh) / yRawData.size() * i, bheight + marginTop, mPaint);
            drawText(xRawDatas.get(i), blwidh + (canvasWidth - blwidh) / yRawData.size() * i, bheight +dip2px(40),
                    canvas);// X坐标
        }
    }

    private void drawScrollLine(Canvas canvas)
    {
        Point startp = new Point();
        Point endp = new Point();
        for (int i = 0; i < mPoints.length - 1; i++)
        {
            startp = mPoints[i];
            endp = mPoints[i + 1];
            int wt = (startp.x + endp.x) / 2;
            Point p3 = new Point();
            Point p4 = new Point();
            p3.y = startp.y;
            p3.x = wt;
            p4.y = endp.y;
            p4.x = wt;

            Path path = new Path();
            path.moveTo(startp.x, startp.y);
            path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawLine(Canvas canvas,Point[] points)
    {
        Point startp = new Point();
        Point endp = new Point();
        for (int i = 0; i < points.length - 1; i++)
        {
            startp = points[i];
            endp = points[i + 1];
            canvas.drawLine(startp.x, startp.y, endp.x, endp.y, mPaint);
        }
    }

    private void drawText(String text, int x, int y, Canvas canvas)
    {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(dip2px(12));
        p.setColor(res.getColor(R.color.text));
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(text, x, y, p);
    }

    private List<Point[]> getPoints()
    {
        List<Point[]> tempListPoints = new ArrayList<Point[]>();
        Point[] points = null;
        for(int j = 0; j < listsRawData.size() ;j ++ ) {
            points = new Point[yRawData.size()];
            for (int i = 0; i < listsRawData.get(j).size(); i++)
            {
                int ph = bheight - (int) (bheight * (listsRawData.get(j).get(i) / maxValue));
                points[i] = new Point(xList.get(i), ph + marginTop);
            }
            tempListPoints.add(points);
        }

        return tempListPoints;
    }

    public void setData(CourseScoreModel model)
    {
        this.listsRawData.add(model.getHighScores());
        this.listsRawData.add(model.getLevelScores());
        this.listsRawData.add(model.getLowScores());
        this.maxValue = model.getMaxValue();
        this.averageValue = model.getAverageValue();
        this.xRawDatas = model.getxRawDatas();
        this.yRawData = model.getHighScores();
        this.mPoints = new Point[yRawData.size()];
        this.spacingHeight = maxValue / averageValue;
    }

    public void setTotalvalue(int maxValue)
    {
        this.maxValue = maxValue;
    }

    public void setPjvalue(int averageValue)
    {
        this.averageValue = averageValue;
    }

    public void setMargint(int marginTop)
    {
        this.marginTop = marginTop;
    }

    public void setMarginb(int marginBottom)
    {
        this.marginBottom = marginBottom;
    }

    public void setMstyle(Linestyle mStyle)
    {
        this.mStyle = mStyle;
    }

    public void setBheight(int bheight)
    {
        this.bheight = bheight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue)
    {
        return (int) (dpValue * dm.density + 0.5f);
    }

    public int stringToInt(String string){
        int j = 0;
        String str = string.substring(0, string.indexOf(".")) + string.substring(string.indexOf(".") + 1);
        int intgeo = Integer.parseInt(str);
        return intgeo;
    }

}
