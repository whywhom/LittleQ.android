package littleq.mammoth.com.littleq.interfaces;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by wuhaoyong on 16/11/5.
 */

public interface LittleQService {
    @GET("gosun/doService")
    Call<ResponseBody> getSLittleQResponse(@Query("acid") String id, @QueryMap Map<String, String> options);
}
