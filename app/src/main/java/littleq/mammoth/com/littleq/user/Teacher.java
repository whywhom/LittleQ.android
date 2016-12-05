package littleq.mammoth.com.littleq.user;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import littleq.mammoth.com.littleq.utils.gson.JsonTeacher;
import littleq.mammoth.com.littleq.utils.gson.TeacherData;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class Teacher {
    private static Teacher instance = null;
    private TeacherData jsonTeacher;
    private Teacher(){
    }

    public static Teacher getInstance() {
        if (instance == null) {
            synchronized (Teacher.class) {
                if (instance == null) {
                    instance = new Teacher();
                }
            }
        }
        return instance;
    }

    public TeacherData getJsonTeacher() {
        return jsonTeacher;
    }
    public JSONObject getTeacherJSONObject(){
        TeacherData teacherData = jsonTeacher;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("t_id",teacherData.getTId());
            jsonObject.put("t_sc_id",teacherData.getTScId());
            jsonObject.put("t_jobno",teacherData.getTJobno());
            jsonObject.put("t_loginname",teacherData.getTLoginname());
            jsonObject.put("t_loginpwd",teacherData.getTLoginpwd());
            jsonObject.put("t_name",teacherData.getTName());
            jsonObject.put("t_headurl",teacherData.getTHeadurl());
            jsonObject.put("t_sex",teacherData.getTSex());
            jsonObject.put("t_hobby",teacherData.getTHobby());
            jsonObject.put("t_address",teacherData.getTAddress());
            jsonObject.put("t_phone",teacherData.getTPhone());
            jsonObject.put("t_cardno",teacherData.getTCardno());
            jsonObject.put("t_sign",teacherData.getTSign());
            jsonObject.put("t_bind_device_no",teacherData.getTBindDeviceNo());
            jsonObject.put("t_birth",teacherData.getTBirth());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public HashMap<String, Object> getTeacherMap(){
        TeacherData teacherData = jsonTeacher;
        HashMap<String, Object> map = new HashMap<>();
        map.put("t_id",teacherData.getTId());
        map.put("t_sc_id",teacherData.getTScId());
        map.put("t_jobno",teacherData.getTJobno());
        map.put("t_loginname",teacherData.getTLoginname());
        map.put("t_loginpwd",teacherData.getTLoginpwd());
        map.put("t_name",teacherData.getTName());
        map.put("t_headurl",teacherData.getTHeadurl());
        map.put("t_sex",teacherData.getTSex());
        map.put("t_hobby",teacherData.getTHobby());
        map.put("t_address",teacherData.getTAddress());
        map.put("t_phone",teacherData.getTPhone());
        map.put("t_cardno",teacherData.getTCardno());
        map.put("t_sign",teacherData.getTSign());
        map.put("t_bind_device_no",teacherData.getTBindDeviceNo());
        map.put("t_birth",teacherData.getTBirth());

        return map;
    }
    public void setJsonTeacher(TeacherData jsonTeacher) {
        this.jsonTeacher = jsonTeacher;
    }
}
