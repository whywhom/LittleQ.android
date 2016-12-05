
package littleq.mammoth.com.littleq.utils.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonDatum {

    @SerializedName("course_id")
    @Expose
    private int courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;

    /**
     * 
     * @return
     *     The courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * 
     * @param courseId
     *     The course_id
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * 
     * @return
     *     The courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 
     * @param courseName
     *     The course_name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
