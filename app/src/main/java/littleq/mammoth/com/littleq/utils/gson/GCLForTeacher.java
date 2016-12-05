
package littleq.mammoth.com.littleq.utils.gson;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GCLForTeacher {

    @SerializedName("responsestamp")
    @Expose
    private String responsestamp;
    @SerializedName("json_str")
    @Expose
    private List<List<Integer>> jsonStr = new ArrayList<List<Integer>>();
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
     *     The jsonStr
     */
    public List<List<Integer>> getJsonStr() {
        return jsonStr;
    }

    /**
     * 
     * @param jsonStr
     *     The json_str
     */
    public void setJsonStr(List<List<Integer>> jsonStr) {
        this.jsonStr = jsonStr;
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
