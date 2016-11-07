package littleq.mammoth.com.littleq.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.ListViewAdapter;
import littleq.mammoth.com.littleq.ui.BaseActivity;

/**
 * Created by Hunter on 2016/10/10.
 */
public class MarkActivity extends BaseActivity {
    private static final String TAG = "MarkActivity";
    private static final boolean D = true;
    private ListView checkedListView;
    private List<String> checkedList;
    private ListView nocheckedListView;
    private List<String> nocheckList;
    private ListViewAdapter listViewAdapter;
    private String infoString;
    private String studentName;
    private String lesson;
    private String studentClass;
    private ImageButton buttonNum0;
    private ImageButton buttonNum1;
    private ImageButton buttonNum2;
    private ImageButton buttonNum3;
    private ImageButton buttonNum4;
    private ImageButton buttonNum5;
    private ImageButton buttonNum6;
    private ImageButton buttonNum7;
    private ImageButton buttonNum8;
    private ImageButton buttonNum9;
    private ImageButton buttonNext;
    private EditText etScore;


    @Override
    public void loadXml() {
        setContentView(R.layout.activity_mark);
    }

    @Override
    public void getIntentData(Bundle savedInstanceState) {
        infoString = getIntent().getStringExtra("title");
        studentClass = getIntent().getStringExtra("class");
        studentName = getIntent().getStringExtra("character");
        lesson = getIntent().getStringExtra("lesson");
    }

    @Override
    public void init() {
        TextView tvClass = (TextView) findViewById(R.id.tv_class);
        tvClass.setText(studentClass);
        TextView tvName =  (TextView) findViewById(R.id.tv_name);
        tvName.setText(studentName);
        TextView tvLesson = (TextView) findViewById(R.id.tv_lesson);
        tvLesson.setText(lesson);
        etScore = (EditText) findViewById(R.id.et_score);
        buttonNum0 = (ImageButton) findViewById(R.id.bt_num0);
        buttonNum1 = (ImageButton) findViewById(R.id.bt_num1);
        buttonNum2 = (ImageButton) findViewById(R.id.bt_num2);
        buttonNum3 = (ImageButton) findViewById(R.id.bt_num3);
        buttonNum4 = (ImageButton) findViewById(R.id.bt_num4);
        buttonNum5 = (ImageButton) findViewById(R.id.bt_num5);
        buttonNum6 = (ImageButton) findViewById(R.id.bt_num6);
        buttonNum7 = (ImageButton) findViewById(R.id.bt_num7);
        buttonNum8 = (ImageButton) findViewById(R.id.bt_num8);
        buttonNum9 = (ImageButton) findViewById(R.id.bt_num9);
        buttonNext = (ImageButton) findViewById(R.id.bt_next);
        ImageButton[] ibuttonNum = new ImageButton[10];
        ibuttonNum[0] = buttonNum0;
        ibuttonNum[1] = buttonNum1;
        ibuttonNum[2] = buttonNum2;
        ibuttonNum[3] = buttonNum3;
        ibuttonNum[4] = buttonNum4;
        ibuttonNum[5] = buttonNum5;
        ibuttonNum[6] = buttonNum6;
        ibuttonNum[7] = buttonNum7;
        ibuttonNum[8] = buttonNum8;
        ibuttonNum[9] = buttonNum9;
        for(int i=0;i<ibuttonNum.length;i++){
            final int index = i;
            ibuttonNum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String num = etScore.getText().toString();
                    etScore.setText(num+String.valueOf(index));
                }
            });
        }
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarkActivity.this.finish();
            }
        });
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
