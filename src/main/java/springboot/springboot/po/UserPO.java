package springboot.springboot.po;


import java.io.Serializable;

/**
 * @author 我命倾尘
 */
public class UserPO implements Serializable {
    /** 用户ID */
    private int id;
    /** 用户名 */
    private String username;
    /** 用户密码 */
    private String password;
    /** 年龄 */
    private int age;
    /** 性别 */
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
