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
import littleq.mammoth.com.littleq.utils.gson.SchoolInfo;

/**
 * Created by Hunter on 2016/10/10.
 */

public class SchoolAdapter extends BaseAdapter {
    private Context mContext;
    private List<SchoolInfo> listDate;
    private ILessonListener onItemClick;
    public SchoolAdapter(Context ctx, List<SchoolInfo> list) {
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
        SchoolAdapter.ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new SchoolAdapter.ListItemView();
            //获取list_item布局文件的视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.school_list_item, null);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (SchoolAdapter.ListItemView) convertView.getTag();
        }
        listItemView.schoolName = (TextView)convertView.findViewById(R.id.tv_uuser_school);
        listItemView.schoolName.setText(listDate.get(position).getScName());
        listItemView.arrow = (ImageView) convertView.findViewById(R.id.iv_user_school_more);
        listItemView.rl = (RelativeLayout)convertView.findViewById(R.id.rl_school);
        listItemView.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClickListener(position, listDate.get(position));
            }
        });
        return convertView;
    }

    public void setListener(ILessonListener listener) {
        onItemClick = listener;
    }

    public void updateData(List<SchoolInfo> listSchool) {
        if(listSchool != null) {
            listDate = listSchool;
        }
    }

    public final class ListItemView{                //自定义控件集合
        public RelativeLayout rl;
        public TextView schoolName;
        public ImageView arrow;
    }
}
