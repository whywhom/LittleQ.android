package littleq.mammoth.com.littleq.interfaces;

import littleq.mammoth.com.littleq.utils.gson.GradeInfo;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wuhaoyong on 16/11/5.
 */

public interface LittleQGetGradeInfo {
    @POST("gosun/doService")
    Call<GradeInfo> getLittleQResponse(@Query("acid") String id, @Body RequestBody parmas);
}
