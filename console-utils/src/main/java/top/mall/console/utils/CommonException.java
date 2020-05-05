package top.mall.console.utils;

import org.springframework.http.HttpStatus;

public class CommonException extends Exception{
    int errorCode;
    String errorMsg;

    public CommonException(int errorCode) {
        this.errorCode = errorCode;
    }

    public CommonException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CommonException(HttpStatus status) {
        this(status.value(), status.getReasonPhrase());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
