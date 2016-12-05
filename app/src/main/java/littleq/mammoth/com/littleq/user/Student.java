package littleq.mammoth.com.littleq.user;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class Student extends User {
    private String studentNo;//学号
    private int cid;//所属班级
    private int fatherId;//父亲ID
    private int motherId;//母亲ID
    private int point;//学生积分
    private String strongPoint;//特长

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public int getMotherId() {
        return motherId;
    }

    public void setMotherId(int motherId) {
        this.motherId = motherId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getStrongPoint() {
        return strongPoint;
    }

    public void setStrongPoint(String strongPoint) {
        this.strongPoint = strongPoint;
    }
}
