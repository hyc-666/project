package stu.pojo;

/**
 * @author hyc
 * @date 2020/10/31
 */
public class Student {
    private Integer s_id;//学生编号,这个编号对学生不可见,用于区分用户类型
    private String s_number;//学号
    private String s_name;//姓名
    private String s_password;//登录密码
    private String s_college;//学院信息

    public Student(String s_number, String s_password) {
        this.s_number = s_number;
        this.s_password = s_password;
    }

    private String s_class;//班级信息

    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + s_id +
                ", s_number='" + s_number + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_password='" + s_password + '\'' +
                ", s_college='" + s_college + '\'' +
                ", s_class='" + s_class + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(Integer s_id, String s_number, String s_name, String s_password, String s_college, String s_class) {
        this.s_id = s_id;
        this.s_number = s_number;
        this.s_name = s_name;
        this.s_password = s_password;
        this.s_college = s_college;
        this.s_class = s_class;
    }

    public String getS_college() {
        return s_college;
    }

    public void setS_college(String s_college) {
        this.s_college = s_college;
    }

    public String getS_class() {
        return s_class;
    }

    public void setS_class(String s_class) {
        this.s_class = s_class;
    }

    public Student( String s_number, String s_name, String s_password) {
        this.s_number = s_number;
        this.s_name = s_name;
        this.s_password = s_password;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getS_number() {
        return s_number;
    }

    public void setS_number(String s_number) {
        this.s_number = s_number;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }
}
