
package littleq.mammoth.com.littleq.utils.gson;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Grade {

    @SerializedName("classInfoList")
    @Expose
    private List<ClassInfoList> classInfoList = new ArrayList<ClassInfoList>();
    @SerializedName("g_attribute")
    @Expose
    private String gAttribute;
    @SerializedName("g_describe")
    @Expose
    private String gDescribe;
    @SerializedName("g_id")
    @Expose
    private int gId;
    @SerializedName("g_name")
    @Expose
    private String gName;
    @SerializedName("g_t_id")
    @Expose
    private int gTId;

    /**
     * 
     * @return
     *     The classInfoList
     */
    public List<ClassInfoList> getClassInfoList() {
        return classInfoList;
    }

    /**
     * 
     * @param classInfoList
     *     The classInfoList
     */
    public void setClassInfoList(List<ClassInfoList> classInfoList) {
        this.classInfoList = classInfoList;
    }

    /**
     * 
     * @return
     *     The gAttribute
     */
    public String getGAttribute() {
        return gAttribute;
    }

    /**
     * 
     * @param gAttribute
     *     The g_attribute
     */
    public void setGAttribute(String gAttribute) {
        this.gAttribute = gAttribute;
    }

    /**
     * 
     * @return
     *     The gDescribe
     */
    public String getGDescribe() {
        return gDescribe;
    }

    /**
     * 
     * @param gDescribe
     *     The g_describe
     */
    public void setGDescribe(String gDescribe) {
        this.gDescribe = gDescribe;
    }

    /**
     * 
     * @return
     *     The gId
     */
    public int getGId() {
        return gId;
    }

    /**
     * 
     * @param gId
     *     The g_id
     */
    public void setGId(int gId) {
        this.gId = gId;
    }

    /**
     * 
     * @return
     *     The gName
     */
    public String getGName() {
        return gName;
    }

    /**
     * 
     * @param gName
     *     The g_name
     */
    public void setGName(String gName) {
        this.gName = gName;
    }

    /**
     * 
     * @return
     *     The gTId
     */
    public int getGTId() {
        return gTId;
    }

    /**
     * 
     * @param gTId
     *     The g_t_id
     */
    public void setGTId(int gTId) {
        this.gTId = gTId;
    }

}
