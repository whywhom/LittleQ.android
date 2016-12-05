
package littleq.mammoth.com.littleq.utils.gson;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolList {

    @SerializedName("responsestamp")
    @Expose
    private String responsestamp;
    @SerializedName("data")
    @Expose
    private List<SchoolInfo> data = new ArrayList<SchoolInfo>();
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * 
     * @return
     *     The responsestamp
     */
    public String getResponsestamp() {
        return responsestamp;
    }

    /**
     * 
     * @param responsestamp
     *     The responsestamp
     */
    public void setResponsestamp(String responsestamp) {
        this.responsestamp = responsestamp;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<SchoolInfo> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<SchoolInfo> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The code
     */
    public int getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(int code) {
        this.code = code;
    }

}
