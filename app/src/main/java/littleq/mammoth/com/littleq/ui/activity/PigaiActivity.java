package littleq.mammoth.com.littleq.ui.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.walktech.device.TransactionProcess;
import com.walktech.template.impl.TransactionListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.adapter.ExpandableAdapter;
import littleq.mammoth.com.littleq.application.LittleQApp;
import littleq.mammoth.com.littleq.ui.BaseActivity;
import littleq.mammoth.com.littleq.utils.SPUtils;
import littleq.mammoth.com.littleq.widget.MainTopTitle;

public class PigaiActivity extends BaseActivity {
    private static final String TAG = PigaiActivity.class.getSimpleName();
    private static final boolean D = true;
    private  List<String> groupArray;
    private  List<List<String>> childArray;
    private ExpandableListView expandableListView;
    /////////////////////////////////////////////////////
    public static final int MESSAGE_TOAST = 1;
    public static final int MESSAGE_HOST = 2;
    public static final int MESSAGE_SWIPER = 3;
    public static final int MESSAGE_IN = 4;
    public static final int MESSAGE_OUT = 5;
    public static final int SELECTION = 6;
//    public static final int ENABLE_BUTTON = 7;
    public static final int MESSAGE_START = 8;

    public static final int ENABLE_TRANS_BUTTON = 1;
    public static final int ENABLE_CONNECT_BUTTON = 2;
    public static final int ENABLE_CARD_BUTTON = 3;
    public static final int ENABLE_NFC_BUTTON = 4;

    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private final static byte CONECT_RETRY_COUNT = 1;
    public static byte mConnectAgain = CONECT_RETRY_COUNT;
    private MainTopTitle title;
    private TextView tvTitle;
    private RelativeLayout rlTitle;
    private ImageView ivLeft;
    private BluetoothAdapter mBluetoothAdapter = null;
    private TransactionListener swiper = null;
    private TransactionProcess mmposService = null;
    private String mConfigFile = "conf.prop";
    private Context mContext;

    private String mConnectedDeviceName = null;
    private String mConnectedDeviceMAC = null;
    private String mCurentPath;
    public int cardType;
    private boolean bFindRFID = false;//没扫到设备设置为false


    @Override
    public void loadXml() {
        setContentView(R.layout.activity_homework_pigai);
    }

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void init() {
        mContext = this;
        String infoString = getIntent().getStringExtra("title");
        title = (MainTopTitle)findViewById(R.id.title);
        MainTopTitle.Builder builder = new MainTopTitle.Builder(infoString == null?getString(R.string.title_activity_pigai):infoString);
        builder.left(MainTopTitle.LEFT_IMG).leftImg(R.mipmap.back_arrow);
        builder.leftOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PigaiActivity.this.finish();
            }
        });

        initSensor();
        initExpandableListView();
        initBluetooth();
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



    private void initSensor() {
        final ImageView ivSensor = (ImageView) findViewById(R.id.iv_sensor);
        final AlphaAnimation animationUp = new AlphaAnimation(0.3f, 1.0f);
        final AlphaAnimation animationDown = new AlphaAnimation(1.0f, 0.3f);
        animationUp.setDuration(3000); //设置持续时间
        animationDown.setDuration(1000); //设置持续时间
        animationUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!bFindRFID){
                    ivSensor.startAnimation(animationDown);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!bFindRFID){
                    ivSensor.startAnimation(animationUp);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivSensor.startAnimation(animationUp);
        ivSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LittleQApp.DEBUG){
                    Intent intent = new Intent( PigaiActivity.this , MarkActivity.class);
                    Bundle args = new Bundle();
                    args.putString("title", getString(R.string.title_activity_pigai));
                    args.putString("class","一年级（1）班");
                    args.putString("lesson","语文");
                    args.putString("character", "王涛涛");
                    intent.putExtras(args);
                    startActivity(intent);
                }
            }
        });

    }

    private void initExpandableListView() {
        initListData();
        expandableListView = (ExpandableListView) findViewById(R.id.elv_zuoye);
        /* 隐藏默认箭头显示 */
        expandableListView.setGroupIndicator(null);
        /* 隐藏垂直滚动条 */
        expandableListView.setVerticalScrollBarEnabled(false);
        /*隐藏选择的黄色高亮*/
        ColorDrawable drawable_tranparent = new ColorDrawable(Color.TRANSPARENT);
        expandableListView.setSelector(drawable_tranparent);
        expandableListView.setAdapter(new ExpandableAdapter(PigaiActivity.this, groupArray,childArray));
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent( PigaiActivity.this , PigaiDetailActivity.class);
                Bundle args = new Bundle();
                args.putString("title", childArray.get(groupPosition).get(childPosition));
                intent.putExtras(args);
                startActivity(intent);
                return false;
            }
        });
    }

    private void initBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

    }

    private void initListData(){
        groupArray = new ArrayList<String>();
        childArray = new  ArrayList<List<String>>();

        groupArray.add("9月27日" );
        groupArray.add("9月26日" );
        groupArray.add("9月23日" );
        groupArray.add("9月22日" );

        List<String> tempArray = new  ArrayList<String>();
        tempArray.add("一年级（1）班" );
        tempArray.add("一年级（2）班" );
        tempArray.add("一年级（3）班" );
        tempArray.add("一年级（4）班" );

        for (int  index = 0 ; index <groupArray.size(); ++index)
        {
            childArray.add(tempArray);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");
        enableBluetooth();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        disconnectBT();
        super.onDestroy();
    }

    private void enableBluetooth() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
        else {
            if (mmposService == null) {
                setup();
            }
        }
    }

    private void setup() {
        swiper = new transactListener();
        mmposService = new TransactionProcess(mContext, swiper);
        mConnectAgain = CONECT_RETRY_COUNT;
        connectBT();
    }

    private void scanMpos() {
        Intent serverIntent = null;
        serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }

    private void cardScan() {
        byte[] appendData = new byte[16];
        cardType = mmposService.CARDTYPE_NFC | mmposService.CARDTYPE_IC|mmposService.CARDTYPE_MAG;
        mmposService.checkCard(cardType,
                mmposService.NORMAL_DEVICE,
                appendData, appendData.length,
                30);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.e(TAG, "onActivityResult requestCode: " + requestCode + " resultCode : " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data);
                }
                break;

            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    Log.e(TAG, "BT is enabled");
                    scanMpos();
                } else {
                    Log.d(TAG, "BT is not enabled");
//                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }

    private void connectDevice(Intent data) {
        String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);

        if(mmposService == null)
        {
            Log.e(TAG, " + mBluetoothService NULL + ");
            return;
        }

        mmposService.connectBluetoothDevice(60, address);

        mConnectedDeviceName = device.getName();
        mConnectedDeviceMAC = address;

        Properties prop = new Properties();
        SPUtils.put(PigaiActivity.this, "MAC", mConnectedDeviceMAC);
//        saveConfig(this, mCurentPath+ "/" + mConfigFile, prop);

        SPUtils.put(PigaiActivity.this, "Name", mConnectedDeviceName);
//        saveConfig(this, mCurentPath+ "/" + mConfigFile, prop);

        mHandler.obtainMessage(MESSAGE_HOST, 0, 0,
                "Select BT Device: Name=" + mConnectedDeviceName + " MAC=" + mConnectedDeviceMAC).sendToTarget();
    }

    private void connectBT() {
        String address = (String) SPUtils.get(PigaiActivity.this,"MAC","");
        String name = (String) SPUtils.get(PigaiActivity.this,"Name","");
        if(address == null || address == "")
        {
            mHandler.obtainMessage(MESSAGE_HOST, 0, 0,
                    "Haven't connected to mpos, press ScanMpos to scan and connect mpos").sendToTarget();
            if(D) Log.e(TAG, "-- Config file is null --");
            scanMpos();//扫描设备
            return;
        }


        mmposService.connectBluetoothDevice(30,  address);

        mHandler.obtainMessage(MESSAGE_HOST, 0, 0,
                "Connect BT Device: name=" + name + " MAC=" + address).sendToTarget();
    }

    private void disconnectBT() {
        mConnectAgain = 0;
        mmposService.disconnectBT();
        mHandler.obtainMessage(MESSAGE_HOST, 0, 0,
                "Disonnect BT").sendToTarget();
    }
    private void cancelSwiper() {
        mmposService.abortCheckCard();
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case MESSAGE_HOST:
                    if(msg.obj != null) {
                        Log.d(TAG, (String) msg.obj);
                    }
                    break;

                case MESSAGE_SWIPER:
                    if(msg.obj != null) {
                        Log.d(TAG, (String) msg.obj);
                    }
                    if(msg.arg1 == 0 && msg.arg2 == 0){
                        mHandler.sendEmptyMessage(MESSAGE_START);
                    }
                    break;

                case MESSAGE_START:
                    if(msg.obj != null) {
                        Log.d(TAG, (String) msg.obj);
                    }
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                           //开始识别
//                            cardScan();
//                        }
//                    }).start();
                    //开始识别
                    cardScan();
                    break;

            }
        }
    };

    public String bytes2HexString(byte[] b, int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    class transactListener implements TransactionListener{

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onBluetoothBounding()
         */
        @Override
        public void onBluetoothBounding() {
            // TODO Auto-generated method stub
            // step 1, select device in the device list, then sdk return onBluetoothBounding
            mHandler.obtainMessage(MESSAGE_SWIPER, 1, 0,
                    "Bluetooth Bounding ").sendToTarget();

        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onBluetoothConnected()
         */
        @Override
        public void onBluetoothConnected() {
            // TODO Auto-generated method stub
            mHandler.obtainMessage(MESSAGE_SWIPER, 0, 0,
                    "Bluetooth Connected ").sendToTarget();

//            mHandler.obtainMessage(ENABLE_BUTTON, ENABLE_CONNECT_BUTTON, 0, 0).sendToTarget();
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onBluetoothConnectFail()
         */
        @Override
        public void onBluetoothConnectFail() {
            // TODO Auto-generated method stub
            // banding and connect fail
            mHandler.obtainMessage(MESSAGE_SWIPER, 2, 0,
                    "Bluetooth ConnectFail ").sendToTarget();

//            mHandler.obtainMessage(ENABLE_BUTTON, ENABLE_CONNECT_BUTTON, 0, 0).sendToTarget();
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onWaitingForCardSwipe()
         */
        @Override
        public void onWaitingForCardSwipe() {
            // TODO Auto-generated method stub

            if(cardType == mmposService.CARDTYPE_NFC) {
                mHandler.obtainMessage(MESSAGE_SWIPER, 3, 1,
                        "请放非接卡").sendToTarget();
            } else {
                mHandler.obtainMessage(MESSAGE_SWIPER, 3, 2,
                        "请插卡或刷卡").sendToTarget();
            }

        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onCheckCardCompleted(int, int, java.lang.String, java.lang.String, java.util.HashMap)
         */
        @Override
        public void onCheckCardCompleted(int cardType, int status, String PAN, String track2, HashMap additonal) {
            // TODO Auto-generated method stub
            if(cardType == mmposService.CARDTYPE_IC) {
                mHandler.obtainMessage(MESSAGE_SWIPER, 4, 1,
                        "Detect IC Card Status: " + status).sendToTarget();
            } else if(cardType == mmposService.CARDTYPE_MAG) {
                mHandler.obtainMessage(MESSAGE_SWIPER, 4, 2,
                        "Detect MAG Card Status: " + status).sendToTarget();
            } else if(cardType == mmposService.CARDTYPE_NFC) {
                mHandler.obtainMessage(MESSAGE_SWIPER, 4, 3,
                        "Detect NFC Card Status: " + status).sendToTarget();
            }

            String atr = "";
            String uuid = "";
            String track1 = "";
            String track3 = "";
            String countryCode = "";
            String cardholder = "";
            String expireDate = "";
            boolean isICCard;

            if(status == 0)
            {
                if(additonal != null)
                {
                    track1 = (String) additonal.get("TRACK1");
                    if(track1 != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 1,
                                "TRACK1: " + track1).sendToTarget();
                    }
                    track3 = (String) additonal.get("TRACK3");
                    if(track3 != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 2,
                                "TRACK3: " + track3).sendToTarget();
                    }
                    countryCode = (String) additonal.get("COUNTRYCODE");
                    if(countryCode != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 3,
                                "COUNTRYCODE: " + countryCode).sendToTarget();
                    }
                    cardholder = (String) additonal.get("CARDHOLDER");
                    if(cardholder != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 4,
                                "CARDHOLDER: " + cardholder).sendToTarget();
                    }

                    expireDate = (String) additonal.get("EXPIRE");
                    if(expireDate != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 5,
                                "EXPIRE DATE: " + expireDate).sendToTarget();
                    }

                    Object temp  = additonal.get("isICCARD");
                    if(temp != null)
                    {
                        isICCard = (Boolean) additonal.get("isICCARD");

                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 6,
                                "isICCARD: " + isICCard).sendToTarget();
                    }

                    atr = (String) additonal.get("ATR");
                    if(atr != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 7,
                                "ATR: " + atr).sendToTarget();
                    }

                    uuid = (String) additonal.get("UUID");
                    if(uuid != null)
                    {
                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 8,
                                "UUID: " + uuid).sendToTarget();

                        //for test NFC APDU send receive
                        byte[] sendBuf = { 0x00, (byte) 0xA4, 0x04, 0x00, 0x0E, 0x32, 0x50, 0x41, 0x59,
                                0x2E, 0x53, 0x59, 0x53, 0x2E, 0x44, 0x44, 0x46, 0x30, 0x31, 0x00 };;
                        byte[] recvBuf = new byte[1024];
                        int sendLen = sendBuf.length;
                        int recvLen;

                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 9,
                                "APDU Send: " + bytes2HexString(sendBuf, sendLen)).sendToTarget();

                        recvLen = mmposService.PCDSendRecv(sendBuf, sendLen, recvBuf);

                        mHandler.obtainMessage(MESSAGE_SWIPER, 5, 10,
                                "APDU Recv ret: " + recvLen).sendToTarget();

                        if(recvLen > 0)
                        {
                            if(recvBuf[0] == 0)
                            {
                                byte[] apdu = new byte[recvLen];
                                for(int index=0; index<recvLen-1; index++)
                                {
                                    apdu[index] = recvBuf[1+index];
                                }
                                mHandler.obtainMessage(MESSAGE_SWIPER, 5, 11,
                                        "APDU Recv: " + bytes2HexString(apdu, recvLen-1)).sendToTarget();
                            }
                            else
                            {
                                mHandler.obtainMessage(MESSAGE_SWIPER, 5, 12,
                                        "APDU Recv Error: " + String.format("%02X", recvBuf[0])).sendToTarget();
                            }

                        }
                    }
                }
                else
                {
                    mHandler.obtainMessage(MESSAGE_SWIPER, 5, 13,
                            "No additional data").sendToTarget();
                }

                if(PAN != null)
                {
                    mHandler.obtainMessage(MESSAGE_SWIPER, 5, 14,
                            "PAN: " + PAN).sendToTarget();
                }
                if(track2 != null)
                {
                    mHandler.obtainMessage(MESSAGE_SWIPER, 5, 15,
                            "TRACK2: " + track2).sendToTarget();
                }
            }
            else
            {
                mHandler.obtainMessage(MESSAGE_SWIPER, 5, 16,
                        "check Card error: " + status).sendToTarget();
            }
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onTimeout()
         */
        @Override
        public void onTimeout() {
            // TODO Auto-generated method stub
            Log.d(TAG,"onTimeout");
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onUpdateProcess(int)
         */
        @Override
        public void onUpdateProcess(int percent) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onInputPIN(byte[])
         */
        @Override
        public int onInputPIN(byte[] pin) {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onGetAllAID(byte[])
         */
        @Override
        public int onGetAllAID(byte[] aid) {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onGetRID(byte[], int, int, byte[], byte[])
         */
        @Override
        public int onGetRID(byte[] rid, int ridLen, int pki, byte[] Mod, byte[] Exp) {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onGetTerminalTag(java.lang.String, byte[])
         */
        @Override
        public int onGetTerminalTag(String reserve, byte[] tag) {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onGetOfflineTotalAmount()
         */
        @Override
        public int onGetOfflineTotalAmount() {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onAppSelect(int, java.lang.String[])
         */
        @Override
        public int onAppSelect(int total, String[] appName) {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#HostSendRecv(byte[], int, byte[], java.lang.String)
         */
        @Override
        public int HostSendRecv(byte[] req, int len, byte[] resp, String reserve) {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onTransactResult(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
         */
        @Override
        public void onTransactResult(int res, int status, String TVR, String TSI, String CVM, String[] Script) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onDebug(java.lang.String)
         */
        @Override
        public void onDebug(String info) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see com.walktech.template.impl.TransactionListener#onCheckCRL(java.lang.String, java.lang.String, java.lang.String)
         */
        @Override
        public int onCheckCRL(String RID, String Index, String SN) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

}
