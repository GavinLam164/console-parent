package top.mall.pojo;

import java.io.Serializable;

public class ConsoleUserKey implements Serializable {
    private Integer uid;

    private String phone;

    public ConsoleUserKey(Integer uid, String phone) {
        this.uid = uid;
        this.phone = phone;
    }

    public ConsoleUserKey() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}