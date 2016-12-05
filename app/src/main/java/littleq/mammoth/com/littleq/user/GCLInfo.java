package littleq.mammoth.com.littleq.user;

import littleq.mammoth.com.littleq.utils.gson.GCLForTeacher;
import littleq.mammoth.com.littleq.utils.gson.JsonLesson;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class GCLInfo {
    private static GCLInfo instance = null;

    private GCLForTeacher gclForTeacher;
    private GCLInfo(){

    }

    public static GCLInfo getInstance() {
        if (instance == null) {
            synchronized (GCLInfo.class) {
                if (instance == null) {
                    instance = new GCLInfo();
                }
            }
        }
        return instance;
    }
    public GCLForTeacher getGclForTeacher() {
        return gclForTeacher;
    }

    public void setGclForTeacher(GCLForTeacher gclForTeacher) {
        this.gclForTeacher = gclForTeacher;
    }
}
