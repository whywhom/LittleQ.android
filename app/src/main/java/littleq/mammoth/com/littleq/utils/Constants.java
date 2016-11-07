package littleq.mammoth.com.littleq.utils;

/**
 * Created by wuhaoyong on 16/11/5.
 */

public class Constants {
    public static String LITTLEQ_URL_BASE = "http://115.159.146.138/";
    public static String LITTLEQ_URL = "gosun/doService?acid=";
    public static String LITTLEQ_POART = "7145";
    public static String getTimeStamp(){
        long time = 0;
        time = System.currentTimeMillis();//获取系统时间的时间戳
        String strRand="" ;
        for(int i=0;i<3;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        String  str = String.valueOf(time)+strRand;
        return str;
    }

}
