package littleq.mammoth.com.littleq.user;

/**
 * Created by Hunter on 2016/11/11.
 */

public class User {
    public static int USER_DEFAULT = 0;
    public static int USER_TEACHER = 1;
    public static int USER_STUDENT = 2;
    public static int USER_PARENT = 3;
    /**
     * 单例对象实例
     */
    private static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new User();
                }
            }
         }
        return instance;
    }

}
