package littleq.mammoth.com.littleq.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import littleq.mammoth.com.littleq.R;

/**
 * Created by wuhaoyong on 16/11/5.
 * 获取系统时间的时间戳
 * 判断网络连接是否可用
 * 判断是否是合法手机号
 * 验证输入的邮箱格式是否符合
 * 验证输入的用户名LoginName格式是否符合
 * 验证输入的密码格式是否符合
 * 获取SD卡根目录
 * 检查SD卡是否可用
 * 创建一个文件夹
 * 复制单个文件
 * 保存文件到指定路径
 * 获取文件夹大小
 * 规范文件大小数据格式
 * 删除文件夹
 * 删除文件夹中所有文件
 */

public class Constants {
    private static String TAG = "Constants";
    public static String LITTLEQ_URL_BASE = "http://115.159.146.138/";
    public static String LITTLEQ_FTP_HOST = "ftp://115.159.146.138/";
    public static int LITTLEQ_FTP_PORT = 21;
    public static String LITTLEQ_FTP_NAME = "gosun";
    public static String LITTLEQ_FTP_PASSWORD = "ftp2016";
    public static String LITTLEQ_URL = "gosun/doService?acid=";
    public static String LITTLEQ_POART = "7145";

    public static final String AVATOR_NAME = "avater.png";// 保存的头像图片名
    public static String AVATOR_PATH = "";// 保存的头像图片名的路径
    public static String AVATOR_FULL_PATH = "";// 保存的头像图片名的完整路径
    public static String TEMP_PATH = "";
    public static String TIMESTAMP = "requeststamp";
    public static String LOGIN_NAME = "t_loginname";
    public static String LOGIN_PWD = "t_loginpwd";
    public static String T_ID = "t_id";//教师ID
    public static String IS_SAVE= "is_save";
    public static String JSON_STRING = "json_str";//json 字符串
    //ftp
    public static final String FTP_CONNECT_SUCCESSS = "ftp连接成功";
    public static final String FTP_CONNECT_FAIL = "ftp连接失败";
    public static final String FTP_DISCONNECT_SUCCESS = "ftp断开连接";
    public static final String FTP_FILE_NOTEXISTS = "ftp上文件不存在";

    public static final String FTP_UPLOAD_SUCCESS = "ftp文件上传成功";
    public static final String FTP_UPLOAD_FAIL = "ftp文件上传失败";
    public static final String FTP_UPLOAD_LOADING = "ftp文件正在上传";

    public static final String FTP_DOWN_LOADING = "ftp文件正在下载";
    public static final String FTP_DOWN_SUCCESS = "ftp文件下载成功";
    public static final String FTP_DOWN_FAIL = "ftp文件下载失败";

    public static final String FTP_DELETEFILE_SUCCESS = "ftp文件删除成功";
    public static final String FTP_DELETEFILE_FAIL = "ftp文件删除失败";


    public static String BR_BOOT = "ittleq.mammoth.com.littleq.BOOT";
    // 项目文件根目录
    public static final String FILEDIR = "littleQ";
    // 照相机照片目录
    public static final String FILEPHOTO = "photos";
    // 应用程序图片存放
    public static final String FILEIMAGE = "images";
    // 应用程序缓存
    public static final String FILECACHE = "cache";
    // 用户信息目录
    public static final String FILEUSER = "user";
    // 用户暂存信息目录
    public static final String FILETEMP = "temp";
    /**
     * 系统参数设置
     */

    public final static String STP_LOGIN_TYPE = "STP_LOGIN_TYPE";
    public final static String STP_USER_NAME = "STP_USER_NAME";
    public final static String STP_PASSWORD = "STP_PASSWORD";
    public final static String STP_BINDING_WEIXIN = "STP_BINDING_WEIXIN";
    public final static String STP_BINDING_QQ = "STP_USER_QQ";
    public final static String STP_BINDING_WEIBO = "STP_BINDING_WEIBO";
    public final static String STP_MSG_SWITCH = "STP_MSG_SWITCH";
    public final static String STP_IS_DIRECTOR = "STP_IS_DIRECTOR";

    /**
     * 获取系统时间的时间戳
     * @return
     */
    public static String getTimeStamp(){
//        long time = 0;
//        time = System.currentTimeMillis();//获取系统时间的时间戳
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String strRand=get3Random() ;
        String  str = timeStamp+strRand;
        return str;
    }
    public static String get3Random(){
        String strRand="" ;
        for(int i=0;i<3;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }
    /**
     * 判断网络连接是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        // 判断网络连接是否可用
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity ==null) {
            return false;
        }
        // 获取网络连接管理的对象
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info ==null || !info.isConnected()) {
            return false;
        }
        // 判断当前网络是否已经连接
        return (info.getState() == NetworkInfo.State.CONNECTED);
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

    /**
     * 获取SD卡根目录
     * @return
     */
    public static String getSDRootPath() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory()+"/";
        } else {
            return null;
        }
    }
    /*
        * 检查sd卡是否可用
        * getExternalStorageState 获取状态
        * Environment.MEDIA_MOUNTED 直译  环境媒体登上  表示，当前sd可用
        */
    public static boolean checkSdCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            //sd卡可用
            return true;
        }else {
            //当前sd卡不可用
            return false;
        }
    }
    /*
    * 创建一个文件夹
    */
    public static  void  createFileDir(String fileDir){
        String path=getSDRootPath()+fileDir;
        File path1=new File(path);
        if(!path1.exists()) {
            path1.mkdirs();
            Log.d(TAG, "我被创建了");
        }
    }
    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                return true;
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            return false;
        }
        return false;
    }
    /**
     * @description 保存文件到指定路径
     * @param in
     * @param path
     * @param fileName
     * @return boolean
     */
    public static boolean saveToFile(InputStream in, String path,
                                     String fileName) {
        FileOutputStream out = null;
        byte buffer[] = new byte[4 * 1024];

        File file = new File(path, fileName);
        try {
            file.createNewFile();
            out = new FileOutputStream(file);
            while ((in.read(buffer)) != -1) {
                out.write(buffer);
            }
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        } finally {
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * 获取文件夹大小
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file);
            }
        }
        return dirSize;
    }

    /**
     * 规范文件大小数据格式
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS)
    {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024)
        {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576)
        {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824)
        {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else
        {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 删除文件夹
     *
     * @param dirName
     * @return
     */
    public static boolean deleteDirectory(String dirName)
    {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!dirName.equals("")) {
            File newPath = new File(dirName);
            checker.checkDelete(newPath.toString());
            if (newPath.isDirectory()) {
                String[] listfile = newPath.list();
                try {
                    for (int i = 0; i < listfile.length; i++) {
                        File deletedFile = new File(newPath.toString() + File.separator
                                + listfile[i].toString());
                        deletedFile.delete();
                    }
                    newPath.delete();
                    status = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                }

            } else {
                status = false;
            }
        } else {
            status = false;
        }
        return status;
    }
    /**
     * 删除文件夹中所有文件
     *
     * @param dirName
     * @return
     */
    public static boolean deleteDirFile(String dirName)
    {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!dirName.equals("")) {
            File newPath = new File(dirName);
            checker.checkDelete(newPath.toString());
            if (newPath.isDirectory()) {
                String[] listfile = newPath.list();
                try {
                    for (int i = 0; i < listfile.length; i++) {
                        File deletedFile = new File(newPath.toString() + File.separator
                                + listfile[i].toString());
                        deletedFile.delete();
                    }
                    status = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                }

            } else {
                status = false;
            }
        } else {
            status = false;
        }
        return status;
    }
    /**
     * @description 新建文件
     * @param name
     * @return
     */
    public static File newAbsoluteFile(String name)
    {
        File file = new File(name);
        file.getParentFile().mkdirs();
        try
        {
            file.createNewFile();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return file;
    }
    /**
     * 将头像保存在SDcard
     */
    private static String saveAvatorBitmap(Bitmap bitmap) {
        String fileName = AVATOR_NAME;
        String dir = getSDRootPath()+Constants.FILEDIR+"/"+Constants.FILEUSER+"/";

        File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File f = new File(folder.getAbsolutePath(), fileName);
        try {
            f.createNewFile();
            FileOutputStream fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }
    /**
     * 读取保存在SDcard的头像
     */
    public static  Bitmap getAvatorBitmap( ) {
        String fileName = AVATOR_NAME;
        String dir = getSDRootPath()+Constants.FILEDIR+"/"+Constants.FILEUSER+"/";
        File folder = new File(dir);
        if (!folder.exists()) {
            return null;
        }
        File f = new File(folder.getAbsolutePath(), fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        return bitmap;
    }

    public static void setAvatorBitmap(Context ctx, Bitmap bitmap){
        if(bitmap == null) {
            Bitmap photo = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_launcher);
            saveAvatorBitmap(photo);
        } else{
            saveAvatorBitmap(bitmap);
        }
    }
}
