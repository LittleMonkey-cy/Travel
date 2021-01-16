package cn.love.travel.pojo;

import java.io.Serializable;

/**
* @author 陈钰
* @Description 商家（管理员）实体类
* @Date 8:19 2020/12/20
* @Param
* @return
**/
public class Seller implements Serializable {
    /**
     * 管理员id
     */
    private int sid;
    /**
     * 用户名名
     */
    private String username;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 管理员姓名
     */
    private String name;
    /**
     * 管理员电话
     */
    private String consphone;
    /**
     * 管理员邮箱
     */
    private String email;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 性别
     */
    private String sex;


    /**
     * 无参构造方法
     */
    public Seller(){}

    /**
     * 构造方法
     * @param sid 商家（管理员id）
     * @param username 用户名
     * @param consphone 手机号
     * @param email 邮箱
     * @param password 密码
     *  @param birthday 出生日期
     *   @param sex 性别
     */

    public Seller(int sid, String username, String password, String name, String consphone, String email, String birthday, String sex) {
        this.sid = sid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.consphone = consphone;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
    }

    public int getSid() {
        return sid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
