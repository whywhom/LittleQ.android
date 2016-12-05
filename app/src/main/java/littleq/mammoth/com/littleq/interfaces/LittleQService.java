package littleq.mammoth.com.littleq.interfaces;

import org.json.JSONObject;

import java.util.Map;

import littleq.mammoth.com.littleq.utils.gson.JsonTeacher;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by wuhaoyong on 16/11/5.
 */

public interface LittleQService {
    @POST("gosun/doService")
    Call<JsonTeacher> getLittleQResponse(@Query("acid") String id, @Body RequestBody parmas);
}
