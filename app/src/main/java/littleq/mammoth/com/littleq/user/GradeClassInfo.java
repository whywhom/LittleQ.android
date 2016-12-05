package littleq.mammoth.com.littleq.user;

import littleq.mammoth.com.littleq.utils.gson.GradeInfo;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class GradeClassInfo {
    private static GradeClassInfo instance = null;
    private GradeInfo gradeInfo;
    private GradeClassInfo(){
    }

    public static GradeClassInfo getInstance() {
        if (instance == null) {
            synchronized (GradeClassInfo.class) {
                if (instance == null) {
                    instance = new GradeClassInfo();
                }
            }
        }
        return instance;
    }

    public GradeInfo getGradeInfo() {
        return gradeInfo;
    }

    public void setGradeInfo(GradeInfo gradeInfo) {
        this.gradeInfo = gradeInfo;
    }
}
