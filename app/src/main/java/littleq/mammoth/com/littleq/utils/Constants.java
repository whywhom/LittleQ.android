package littleq.mammoth.com.littleq.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuhaoyong on 16/11/5.
 */

public class Constants {
    public static String LITTLEQ_URL_BASE = "http://115.159.146.138/";
    public static String LITTLEQ_URL = "gosun/doService?acid=";
    public static String LITTLEQ_POART = "7145";
    public static String getTimeStamp(){
        long time = 0;
        time = System.currentTimeMillis();//获取系统时间的时间戳
        String strRand="" ;
        for(int i=0;i<3;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        String  str = String.valueOf(time)+strRand;
        return str;
    }
    /**
     * 判断是否是合法手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证输入的邮箱格式是否符合
     * @param email
     * @return 是否合法
     */
    public static boolean isEmail(String email) {
        String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
        boolean result = Pattern.matches(emailPattern, email);
        return result;
    }

    /**
     * 验证输入的用户名LoginName格式是否符合
     * @param name
     * @return 是否合法
     */
    public static boolean isLoginName(String name) {
//        String usernameRegex = "^[a-zA-z][a-zA-Z0-9_]{2,9}$";
//        boolean result = Pattern.matches(usernameRegex, name);
        if(name == null || name.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 验证输入的密码格式是否符合
     * @param pwd
     * @return 是否合法
     */
    public static boolean isLoginPwd(String pwd) {
//        String pwdRegex = "/^\\w{6,18}$/";
//        boolean result = Pattern.matches(pwdRegex, pwd);
        if(pwd == null || pwd.isEmpty()){
            return false;
        } else if (pwd.length()<6){
            return false;
        }
        return true;
    }
}
