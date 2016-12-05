package littleq.mammoth.com.littleq.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.ListViewTreeAdapter;
import littleq.mammoth.com.littleq.ui.BaseFragment;
import littleq.mammoth.com.littleq.ui.activity.AddLeafActivity;
import littleq.mammoth.com.littleq.utils.GrowTree;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentGrowup extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private MainTopTitle title;
    private TextView tvTitle;
    private RelativeLayout rlTitle;
    private ImageView ivRight;
    private TextView tvRight;
    private ImageView fab;
    private ListView listviewTree;
    private ListViewTreeAdapter listViewTreeAdapter;
    private ArrayList<GrowTree> growTreeList = new ArrayList<GrowTree>();
    public static FragmentGrowup newInstance(int sectionNumber) {
        FragmentGrowup fragment = new FragmentGrowup();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void loadXml() {
        setLayoutId(R.layout.fragment_growup);
    }

    @Override
    public void init() {
        title = (MainTopTitle)rootView.findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(getString(R.string.toolbar_growup));
        builder.right(MainTopTitle.RIGHT_CHARACTOR).rightChar(getString(R.string.growup_allclass));
        title.setBuilder(builder);
        listviewTree = (ListView) rootView.findViewById(R.id.lv_tree);
        fab = (ImageView) rootView.findViewById(R.id.fab);

    }

    @Override
    public void setListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentGrowup.this.getActivity() , AddLeafActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
    public FragmentGrowup() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGrowTree();
    }

    private void initGrowTree() {
        int[] imgList1 = {
                R.mipmap.index_gallery_01,
                R.mipmap.index_gallery_02,
                R.mipmap.index_gallery_03,
                R.mipmap.index_gallery_04
        };
        int[] imgList2 = {
                R.mipmap.index_gallery_05,
                R.mipmap.index_gallery_06,
                R.mipmap.index_gallery_07,
        };
        int[] imgList3 = {
                R.mipmap.index_gallery_08,
                R.mipmap.index_gallery_09,
        };
        int[] imgList4 = {
                R.mipmap.index_gallery_10,
                R.mipmap.index_gallery_11,
                R.mipmap.index_gallery_12,
                R.mipmap.index_gallery_13,
                R.mipmap.index_gallery_14,
        };
        //1
        GrowTree growTree = new GrowTree();
        growTree.setCalendar(getTime(0));
        growTree.setTitle("秋游剪影");
        growTree.setClassName("一年级(1)班");
        growTree.setBrowse(100);
        growTree.setLike(56);
        growTree.setImageList(imgList1);
        growTreeList.add(growTree);
        //2
        growTree = new GrowTree();
        growTree.setCalendar(getTime(1));
        growTree.setTitle("蔡总视察");
        growTree.setClassName("一年级(2)班");
        growTree.setBrowse(1000);
        growTree.setLike(4);
        growTree.setImageList(imgList2);
        growTreeList.add(growTree);
        //3
        growTree = new GrowTree();
        growTree.setCalendar(getTime(2));
        growTree.setTitle("李总视察");
        growTree.setClassName("一年级(3)班");
        growTree.setBrowse(1000);
        growTree.setLike(4);
        growTree.setImageList(imgList3);
        growTreeList.add(growTree);
        //4
        growTree = new GrowTree();
        growTree.setCalendar(getTime(3));
        growTree.setTitle("房总视察");
        growTree.setClassName("一年级(4)班");
        growTree.setBrowse(1000);
        growTree.setLike(4);
        growTree.setImageList(imgList4);
        growTreeList.add(growTree);

        listViewTreeAdapter = new ListViewTreeAdapter(this.getActivity(),growTreeList);
        listviewTree.setAdapter(listViewTreeAdapter);
    }

    private String getTime(int gap){
        long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        Date d1=new Date(time);
        d1.setMonth(d1.getMonth()-gap);
        String t1=format.format(d1);
        Log.e("msg", t1);
        return t1;
    }
}
