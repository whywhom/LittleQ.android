package littleq.mammoth.com.littleq.utils;

import android.widget.ImageView;

/**
 * Created by Hunter on 2016/10/24.
 */

public class GrowTree {
    long time = 0;
    String calendar = "";
    String className = "";
    int browse = 0;
    int like = 0;
    String title = "";
    int[] imageList;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getImageList() {
        return imageList;
    }

    public void setImageList(int[] imageList) {
        this.imageList = imageList;
    }
}
