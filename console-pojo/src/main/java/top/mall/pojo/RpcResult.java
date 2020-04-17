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

}
