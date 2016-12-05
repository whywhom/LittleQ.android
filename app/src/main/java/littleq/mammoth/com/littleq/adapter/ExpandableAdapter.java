package littleq.mammoth.com.littleq.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import littleq.mammoth.com.littleq.R;

/**
 * Created by 吴浩勇 on 2016/9/27.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private  List<String> groupArray;
    private  List<java.util.List<String>> childArray;

    public  ExpandableAdapter(Activity a,List<String> mainArray, List<java.util.List<String>> subArray) {
        activity = a;
        groupArray = mainArray;
        childArray = subArray;
    }
    public  Object getChild(int  groupPosition, int  childPosition) {
        return  childArray.get(groupPosition).get(childPosition);
    }
    public  long  getChildId(int  groupPosition, int  childPosition) {
        return  childPosition;
    }
    public  int  getChildrenCount(int  groupPosition) {
        return  childArray.get(groupPosition).size();
    }
    public  View getChildView(int  groupPosition, int  childPosition,
                              boolean  isLastChild, View convertView, ViewGroup parent) {
        String string = childArray.get(groupPosition).get(childPosition);
//        return  getGenericView(string);
        TextView txt_child_class;
        TextView txt_child_lesson;
        TextView txt_child_count;
        ImageView image_child;
        if(null == convertView){
            convertView = LayoutInflater.from(activity).inflate(R.layout.view_child_item, null);
        }
        /*判断是否group张开，来分别设置背景图*/
        image_child = (ImageView) convertView.findViewById(R.id.iv_right_arrow);

        txt_child_class = (TextView)convertView.findViewById(R.id.tv_childclass);
        txt_child_class.setText(string);
        txt_child_lesson = (TextView)convertView.findViewById(R.id.tv_childlesson);
        txt_child_lesson.setText("语文");
        txt_child_count = (TextView)convertView.findViewById(R.id.tv_childcount);
        txt_child_count.setText("25 / 18");
        return convertView;
    }
    // group method stub
    public  Object getGroup(int  groupPosition) {
        return  groupArray.get(groupPosition);
    }
    public  int  getGroupCount() {
        return  groupArray.size();
    }
    public  long  getGroupId(int  groupPosition) {
        return  groupPosition;
    }
    public  View getGroupView(int  groupPosition, boolean  isExpanded,
                              View convertView, ViewGroup parent) {
        String string = groupArray.get(groupPosition);
//        return  getGenericView(string);
        TextView txt_group;
        ImageView image_group;
        if(null == convertView){
            convertView = LayoutInflater.from(activity).inflate(R.layout.group_item, null);
        }
        /*判断是否group张开，来分别设置背景图*/
        image_group = (ImageView) convertView.findViewById(R.id.iv_right_arrow);
        if(isExpanded){
            image_group.setVisibility(View.GONE);
        }else{
            image_group.setVisibility(View.VISIBLE);
        }

        txt_group = (TextView)convertView.findViewById(R.id.tv_groupname);
        txt_group.setText(string);
        return convertView;
    }
    // View stub to create Group/Children 's View
    public TextView getGenericView(String string) {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64 );
        TextView text = new  TextView(activity);
        text.setLayoutParams(layoutParams);
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(36 , 0 , 0 , 0 );
        text.setText(string);
        return  text;
    }
    public  boolean  hasStableIds() {
        return  false ;
    }
    public  boolean  isChildSelectable(int  groupPosition, int  childPosition) {
        return  true ;
    }
}
