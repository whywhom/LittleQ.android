package littleq.mammoth.com.littleq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.interfaces.ILessonListener;
import littleq.mammoth.com.littleq.utils.gson.LessonDatum;

/**
 * Created by Hunter on 2016/10/10.
 */

public class LessonAdapter extends BaseAdapter {
    private Context mContext;
    private List<LessonDatum> listDate;
    private ILessonListener onItemClick;
    public LessonAdapter(Context ctx, List<LessonDatum> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        //自定义视图
        LessonAdapter.ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new LessonAdapter.ListItemView();
            //获取list_item布局文件的视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lesson_list_item, null);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (LessonAdapter.ListItemView) convertView.getTag();
        }
        listItemView.lessonName = (TextView)convertView.findViewById(R.id.tv_uuser_lesson);
        listItemView.lessonName.setText(listDate.get(position).getCourseName());
        listItemView.arrow = (ImageView) convertView.findViewById(R.id.iv_user_lesson_more);
        listItemView.rl = (RelativeLayout)convertView.findViewById(R.id.rl_lesson);
        listItemView.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClickListener(position, null);
            }
        });
        return convertView;
    }

    public void setListener(ILessonListener listener) {
        onItemClick = listener;
    }

    public final class ListItemView{                //自定义控件集合
        public RelativeLayout rl;
        public TextView lessonName;
        public ImageView arrow;
    }
}