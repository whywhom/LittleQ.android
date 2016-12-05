package littleq.mammoth.com.littleq.user;

/**
 * Created by wuhaoyong on 16/11/12.
 */

public class Parent extends User {
    private String profession;//职业
    private String company;//公司名称
    private String workAddress;//公司地址

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

}
