package com.autmaple.oauth.common.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一客户端响应格式
 */
@Getter
@Setter
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;

    public ResponseResult() {
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setCode(ResponseCode.RC200.getCode());
        res.setMessage(ResponseCode.RC200.getMessage());
        res.setData(data);
        return res;
    }

    public static <T> ResponseResult<T> failed(int code, String message) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setCode(code);
        res.setMessage(message);
        return res;
    }

}
