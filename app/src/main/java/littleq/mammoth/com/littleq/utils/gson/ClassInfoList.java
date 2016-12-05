
package littleq.mammoth.com.littleq.utils.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassInfoList {

    @SerializedName("c_id")
    @Expose
    private int cId;
    @SerializedName("c_name")
    @Expose
    private String cName;
    @SerializedName("c_name_class")
    @Expose
    private String cNameClass;

    /**
     * 
     * @return
     *     The cId
     */
    public int getCId() {
        return cId;
    }

    /**
     * 
     * @param cId
     *     The c_id
     */
    public void setCId(int cId) {
        this.cId = cId;
    }

    /**
     * 
     * @return
     *     The cName
     */
    public String getCName() {
        return cName;
    }

    /**
     * 
     * @param cName
     *     The c_name
     */
    public void setCName(String cName) {
        this.cName = cName;
    }

    /**
     * 
     * @return
     *     The cNameClass
     */
    public String getCNameClass() {
        return cNameClass;
    }

    /**
     * 
     * @param cNameClass
     *     The c_name_class
     */
    public void setCNameClass(String cNameClass) {
        this.cNameClass = cNameClass;
    }

}
