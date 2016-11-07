package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.ListViewAdapter;
import littleq.mammoth.com.littleq.ui.BaseActivity;

public class PigaiDetailActivity extends BaseActivity {
    private static final String TAG = "PigaiDetailActivity";
    private static final boolean D = true;
    private RecyclerView checkedListView;
    private List<String> checkedList;
    private RecyclerView nocheckedListView;
    private List<String> nocheckList;
    private ListViewAdapter listViewAdapter;
    private Context mContext;
    private String infoString;


@Override
    public void loadXml() {
        setContentView(R.layout.activity_pigaidetail);
    }

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void init() {
        initListView();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }
    private void initListView() {
        initListData();
        nocheckedListView = (RecyclerView) findViewById(R.id.lv_weipigai);
        nocheckedListView.setLayoutManager(new LinearLayoutManager(this));
        listViewAdapter = new ListViewAdapter(this, nocheckList);
        nocheckedListView.setAdapter(listViewAdapter);
        //////////////////////////////////////////////////////////////
        checkedListView = (RecyclerView) findViewById(R.id.lv_yipigai);
        checkedListView.setLayoutManager(new LinearLayoutManager(this));
        listViewAdapter = new ListViewAdapter(this, checkedList);
        checkedListView.setAdapter(new ListViewAdapter(this, checkedList));
    }

    private void initListData(){
        nocheckList = new ArrayList<>();

        nocheckList.add("房波" );
        nocheckList.add("蔡庆龙" );
        nocheckList.add("李忠偉" );

        checkedList = new ArrayList<String>();
        checkedList.add("吴三省" );
        checkedList.add("张启灵" );
        checkedList.add("闷油瓶" );
        checkedList.add("王胖子" );

    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.e(TAG, "onActivityResult requestCode: " + requestCode + " resultCode : " + resultCode);
    }
}
