package littleq.mammoth.com.littleq.net;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.x;
import org.xutils.http.RequestParams;

import java.util.Map;

import littleq.mammoth.com.littleq.utils.Constants;


/**
 * Created by User on 2016/11/24.
 */

public class NetUtils {

    private int connTimeout = 1000 * 60;
    private NetUtilsListener listener;
    private LocalCallback mLocalCallback;
    private boolean isStamp = true;


    public NetUtils(){
        mLocalCallback = new LocalCallback();
    }

    public NetUtils(NetUtilsListener listener) {
        this();
        this.listener = listener;
    }
    public NetUtils(String tag) {
        this();
    }
    public interface NetUtilsListener {

        void success(int code, String msg);

        void fail(int code, String msg);

        void cancel(String msg);
    }


    private RequestParams getRequestParams(String url) {
        RequestParams params=new RequestParams(url);
        params.setAsJsonContent(true);
        params.setConnectTimeout(connTimeout);
        return params;
    }


    /**
     * 发送post请求
     * @param
     */
    public   Callback.Cancelable Post(String url, Map<String,String> map){
        RequestParams params = getRequestParams(url);
        params.setMethod(HttpMethod.POST);
        initBodeParameters(params, map);
        Callback.Cancelable cancelable = x.http().post(params, mLocalCallback);
        return cancelable;
    }

    private void initBodeParameters(RequestParams params, Map<String,String> map) {
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        if(isStamp) {
            params.addBodyParameter(Constants.TIMESTAMP, Constants.getTimeStamp());
        }
    }


    private class  LocalCallback implements Callback.CommonCallback<String> {


        @Override
        public void onSuccess(String result) {
            System.out.println("xiongzhu result = "+result.toString());
            try {
                JSONObject jsonObject = new JSONObject(result);
                int code = jsonObject.optInt("code");
                if(code == 200) {
                    if(listener != null) {
                        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                        if(jsonObject1 == null){
                            JSONArray jsonArray = jsonObject.optJSONArray("data");
                            if(jsonArray == null){
                                listener.fail(code, "no json data");
                                return;
                            }
                            listener.success(code,jsonArray.toString());
                        } else {
                            listener.success(code, jsonObject1.toString());
                        }
                    }
                }else {
                    if (listener != null) {
                        listener.fail(code, jsonObject.optJSONObject("data").toString());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            if(listener != null) {
                listener.fail(-1,ex.getMessage());
            }
        }

        @Override
        public void onCancelled(CancelledException cex) {
            if(listener != null) {
                listener.cancel("");
            }
        }

        @Override
        public void onFinished() {

        }


    }



    /**
     * 发送get请求
     * @param <T>
     */
    public  <T> Callback.Cancelable Get(String url, Map<String,String> map){
        RequestParams params = getRequestParams(url);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().get(params, mLocalCallback);
        return cancelable;
    }



    /**
     * 上传文件
     * @param <T>
     */
    public  <T> Callback.Cancelable UpLoadFile(String url, Map<String,Object> map){
        RequestParams params =getRequestParams(url);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Callback.Cancelable cancelable = x.http().post(params, mLocalCallback);
        return cancelable;
    }

    /**
     * 下载文件
     * @param <T>
     */
    public  <T> Callback.Cancelable DownLoadFile(String url, String filepath){
        RequestParams params =getRequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Callback.Cancelable cancelable = x.http().get(params, mLocalCallback);
        return cancelable;
    }



    /**
     * 发送get请求
     * @param <T>
     */
    public  <T> Callback.Cancelable Get(String url, Map<String,String> map, Callback.CommonCallback<T> callback){
        RequestParams params =getRequestParams(url);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 发送post请求
     * @param <T>
     */
    public  <T> Callback.Cancelable Post(String url, Map<String,Object> map, Callback.CommonCallback<T> callback){
        RequestParams params =getRequestParams(url);
        if(null != map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }


    /**
     * 上传文件
     * @param <T>
     */
    public  <T> Callback.Cancelable UpLoadFile(String url, Map<String,Object> map, Callback.CommonCallback<T> callback){
        RequestParams params =getRequestParams(url);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Callback.Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 下载文件
     * @param <T>
     */
    public  <T> Callback.Cancelable DownLoadFile(String url, String filepath, Callback.CommonCallback<T> callback){
        RequestParams params =getRequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }





}
