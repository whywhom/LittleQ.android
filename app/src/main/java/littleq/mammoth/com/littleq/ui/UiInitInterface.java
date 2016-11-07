package littleq.mammoth.com.littleq.ui;


/**
 * Created by User on 2016/10/28.
 */
public interface UiInitInterface {

    /**
     * 设置xml文件
     */
    void loadXml();


    /**
     * view 初始化
     */
    void init();

    /**
     * 设置view监听器
     */
    void setListener();

    /**
     * 数据设置
     */
    void setData();

    /**
     *
     */
    void setOther();

}
