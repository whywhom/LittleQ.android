package littleq.mammoth.com.littleq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.interfaces.OnRecyclerViewListener;

/**
 * Created by Hunter on 2016/10/10.
 */

public class ListViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> listDate;
    private OnRecyclerViewListener onRecyclerViewListener;


    public ListViewAdapter(Context ctx, List<String> list) {
        mContext = ctx;
        listDate = list;
    }
    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    @Override
    public int getItemCount() {
        if(listDate == null){
            return 0;
        }
        return listDate.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ListItemView(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ListItemView holder = (ListItemView) viewHolder;
        holder.position = position;
        holder.title.setText(listDate.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView(convertView);
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

    public class ListItemView extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener{
        //自定义控件集合
        public View rootView;
        public ImageView image;
        public TextView title;
        public int position;
        public ListItemView(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.iv_right_arrow);
            title = (TextView)itemView.findViewById(R.id.tv_title);
            rootView = itemView.findViewById(R.id.rl_list);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
                }
            }

        @Override
        public boolean onLongClick(View v) {
            if(null != onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
