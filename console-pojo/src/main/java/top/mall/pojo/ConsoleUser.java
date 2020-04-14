package top.mall.pojo;

import java.io.Serializable;

public class ConsoleUser extends ConsoleUserKey implements Serializable {
    private String nickName;

    private String password;

    public ConsoleUser(Integer uid, String phone, String nickName, String password) {
        super(uid, phone);
        this.nickName = nickName;
        this.password = password;
    }

    public ConsoleUser() {
        super();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}