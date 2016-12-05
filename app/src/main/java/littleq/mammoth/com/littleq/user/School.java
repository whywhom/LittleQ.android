package littleq.mammoth.com.littleq.user;

import littleq.mammoth.com.littleq.utils.gson.GCLForTeacher;
import littleq.mammoth.com.littleq.utils.gson.SchoolList;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class School {
    private static School instance = null;

    private SchoolList schoolList;
    private School(){

    }

    public static School getInstance() {
        if (instance == null) {
            synchronized (School.class) {
                if (instance == null) {
                    instance = new School();
                }
            }
        }
        return instance;
    }
    public SchoolList getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(SchoolList schoolList) {
        this.schoolList = schoolList;
    }
}
