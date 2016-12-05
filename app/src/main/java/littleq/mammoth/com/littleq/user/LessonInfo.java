package littleq.mammoth.com.littleq.user;

import littleq.mammoth.com.littleq.utils.gson.JsonLesson;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class LessonInfo {
    private static LessonInfo instance = null;
    private JsonLesson jsonLesson;
    private LessonInfo(){

    }

    public static LessonInfo getInstance() {
        if (instance == null) {
            synchronized (LessonInfo.class) {
                if (instance == null) {
                    instance = new LessonInfo();
                }
            }
        }
        return instance;
    }

    public JsonLesson getJsonLesson() {
        return jsonLesson;
    }

    public void setJsonLesson(JsonLesson jsonLesson) {
        this.jsonLesson = jsonLesson;
    }
}
