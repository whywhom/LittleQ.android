
package littleq.mammoth.com.littleq.utils.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SchoolInfo {

    @SerializedName("sc_address")
    @Expose
    private String scAddress;
    @SerializedName("sc_attr")
    @Expose
    private String scAttr;
    @SerializedName("sc_id")
    @Expose
    private int scId;
    @SerializedName("sc_name")
    @Expose
    private String scName;

    /**
     * 
     * @return
     *     The scAddress
     */
    public String getScAddress() {
        return scAddress;
    }

    /**
     * 
     * @param scAddress
     *     The sc_address
     */
    public void setScAddress(String scAddress) {
        this.scAddress = scAddress;
    }

    /**
     * 
     * @return
     *     The scAttr
     */
    public String getScAttr() {
        return scAttr;
    }

    /**
     * 
     * @param scAttr
     *     The sc_attr
     */
    public void setScAttr(String scAttr) {
        this.scAttr = scAttr;
    }

    /**
     * 
     * @return
     *     The scId
     */
    public int getScId() {
        return scId;
    }

    /**
     * 
     * @param scId
     *     The sc_id
     */
    public void setScId(int scId) {
        this.scId = scId;
    }

    /**
     * 
     * @return
     *     The scName
     */
    public String getScName() {
        return scName;
    }

    /**
     * 
     * @param scName
     *     The sc_name
     */
    public void setScName(String scName) {
        this.scName = scName;
    }

}
