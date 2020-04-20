package top.mall.pojo;

import java.io.Serializable;

public class RpcResult<T> implements Serializable {
    int code;
    String msg;
    T data;

    public RpcResult() {
    }

    public RpcResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RpcResult<T> success(T data) {
        return new RpcResult(200, "", data);
    }

    public static RpcResult error(int code, String msg) {
        return new RpcResult(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
