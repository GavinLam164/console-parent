package top.mall.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer uid;

    private String nickName;

    private String profile;

    private String phone;

    private String password;

    public User(Integer uid, String nickName, String profile, String phone, String password) {
        this.uid = uid;
        this.nickName = nickName;
        this.profile = profile;
        this.phone = phone;
        this.password = password;
    }

    public User() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}