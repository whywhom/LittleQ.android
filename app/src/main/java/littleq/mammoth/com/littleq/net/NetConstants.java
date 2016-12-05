package littleq.mammoth.com.littleq.net;

/**
 * Created by User on 2016/11/24.
 */

public class NetConstants {
    public static String LITTLEQ_URL_IP = "http://115.159.146.138/";
    public static String LITTLEQ_URL_BASE = LITTLEQ_URL_IP +"gosun/doService?acid=";

    public static String LOGIN = LITTLEQ_URL_BASE +"1000";//老师登录
    public static String GRADE_CLASS_INFO = LITTLEQ_URL_BASE +"1001";//学校的年级和下边班级查询
    public static String STUDENTLIST_FOLLOWED_BY_TEACHER = LITTLEQ_URL_BASE +"1002";//老师获取关注的学生列表
    public static String FOLLOWED_STUDENT_OR_NOT = LITTLEQ_URL_BASE +"1003";//老师关注和取消关注学生
    public static String STUDENTLIST_NO_FOLLOWED_BY_TEACHER = LITTLEQ_URL_BASE +"1004";//老师获取任课班级未关注学生列表，按照任课班级分类
    public static String QUERY_STUDENT_INFO = LITTLEQ_URL_BASE +"1005";//查询学生个人信息。包括：个人信息，家长信息
    public static String LESSON_FOR_TEACHER = LITTLEQ_URL_BASE +"1006";//获取课程信息
    public static String SAVE_GROWUP_TREE = LITTLEQ_URL_BASE +"1007";//保存成长树
    public static String SAVE_GROWUP_TREE_COMMENT = LITTLEQ_URL_BASE +"1008";//保存成长树评论
    public static String UPDATE_GROWUP_TREE_COMMENT = LITTLEQ_URL_BASE +"1009";//更新浏览数和评论数
    public static String GET_GROWUP_TREE_COMMENT_BY_ID = LITTLEQ_URL_BASE +"1010";//根据成长树ID，获取所有评论内容
    public static String GET_STUDENTLIST_BY_TEACHER = LITTLEQ_URL_BASE +"1011";//老师获取任课班级所有学生列表，按照任课班级分类
    public static String REG_MODIFY_TEACHER_INFO = LITTLEQ_URL_BASE +"1012";//老师注册和修改老师信息
    public static String GET_SCHOOL_INFO = LITTLEQ_URL_BASE +"1013";//获取学校信息列表
    public static String SAVE_MODIFY_HOMEWORK = LITTLEQ_URL_BASE +"1014";//保存和修改作业
    public static String GET_GRADE_CLASS_LESSON_FOR_TEACHER = LITTLEQ_URL_BASE +"1015";//根据老师ID获取班级与课程设置信息
    public static String UPDATE_GRADE_CLASS_LESSON_FOR_TEACHER = LITTLEQ_URL_BASE +"1016";//根据老师ID保存班级与课程设置信息
    public static String GET_HOMEWORK = LITTLEQ_URL_BASE +"1017";//获取首页作业信息，按照是否发布分类

}
