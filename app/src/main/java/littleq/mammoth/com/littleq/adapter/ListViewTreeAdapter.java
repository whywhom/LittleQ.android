package littleq.mammoth.com.littleq.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.utils.GrowTree;

/**
 * Created by Hunter on 2016/10/10.
 */

public class ListViewTreeAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<GrowTree> listDate;
    public ListViewTreeAdapter(Context ctx, ArrayList<GrowTree> list) {
        mContext = ctx;
        listDate = list;
    }

    @Override
    public int getCount() {
        if(listDate == null){
            return 0;
        }
        return listDate.size();
    }

    @Override
    public Object getItem(int position) {
        return listDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.growtree_item, null);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }
        listItemView.horizontalScrollView = (HorizontalScrollView)convertView.findViewById(R.id.hsv_photo);
        listItemView.linear = (LinearLayout) convertView.findViewById(R.id.linear);
        listItemView.title = (TextView)convertView.findViewById(R.id.tv_title);
        listItemView.title.setText(listDate.get(position).getTitle());
        listItemView.className = (TextView)convertView.findViewById(R.id.tv_class);
        listItemView.className.setText(listDate.get(position).getClassName());
        listItemView.calendar = (TextView)convertView.findViewById(R.id.tv_calendar);
        listItemView.calendar.setText(listDate.get(position).getCalendar());
        listItemView.browse = (TextView)convertView.findViewById(R.id.tv_browse);
        listItemView.browse.setText("浏览 "+String.valueOf(listDate.get(position).getBrowse()));
        listItemView.like = (TextView)convertView.findViewById(R.id.tv_like);
        listItemView.like.setText("赞 "+String.valueOf(listDate.get(position).getLike()));
        int[] imageList = listDate.get(position).getImageList();
        listItemView.linear.removeAllViews();
        for (int i = 0; i < imageList.length; i++) {
            LinearLayout.LayoutParams linearLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout myLinear = new LinearLayout(mContext);
            linearLp.setMargins(5, 0, 5, 20);
            myLinear.setOrientation(LinearLayout.VERTICAL);
            myLinear.setTag(i);
            listItemView.linear.addView(myLinear, linearLp);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(android.R.color.transparent);
            imageView.setImageResource(imageList[i]);
            myLinear.addView(imageView, lp);
        }
        return convertView;
    }

    public final class ListItemView{                //自定义控件集合
        public HorizontalScrollView horizontalScrollView;
        public LinearLayout linear;
        public TextView title;
        public TextView className;
        public TextView calendar;
        public TextView browse;
        public TextView like;
    }
}
