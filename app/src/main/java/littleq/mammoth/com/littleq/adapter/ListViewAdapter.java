package littleq.mammoth.com.littleq.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import littleq.mammoth.com.littleq.R;

/**
 * Created by Hunter on 2016/10/10.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> listDate;


    public ListViewAdapter(Context ctx, List<String> list) {
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
        if(listDate == null){
            return null;
        }
        return listDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListViewAdapter.ListItemView();
            //获取list_item布局文件的视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }
        listItemView.image = (ImageView)convertView.findViewById(R.id.iv_right_arrow);
        listItemView.title = (TextView)convertView.findViewById(R.id.tv_title);
        listItemView.title.setText(listDate.get(position));

        return convertView;
    }
    public final class ListItemView{                //自定义控件集合
        public ImageView image;
        public TextView title;
    }
//    public class ListItemView extends RecyclerView.ViewHolder implements View.OnClickListener,
//            View.OnLongClickListener{
//        //自定义控件集合
//        public View rootView;
//        public ImageView image;
//        public TextView title;
//        public int position;
//        public ListItemView(View itemView) {
//            super(itemView);
//            image = (ImageView)itemView.findViewById(R.id.iv_right_arrow);
//            title = (TextView)itemView.findViewById(R.id.tv_title);
//            rootView = itemView.findViewById(R.id.rl_list);
//            rootView.setOnClickListener(this);
//            rootView.setOnLongClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (null != onRecyclerViewListener) {
//                onRecyclerViewListener.onItemClick(position);
//                }
//            }
//
//        @Override
//        public boolean onLongClick(View v) {
//            if(null != onRecyclerViewListener){
//                return onRecyclerViewListener.onItemLongClick(position);
//            }
//            return false;
//        }
//    }
}
