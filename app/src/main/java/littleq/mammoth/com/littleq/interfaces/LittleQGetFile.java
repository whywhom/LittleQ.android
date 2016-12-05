package littleq.mammoth.com.littleq.interfaces;

import littleq.mammoth.com.littleq.utils.gson.JsonTeacher;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by wuhaoyong on 16/11/5.
 */

public interface LittleQGetFile {
    @GET
    public Call<ResponseBody> downloadFile(@Url String url);
}
