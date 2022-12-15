package com.autmaple.oauth.common.api;

import lombok.Getter;

/**
 * 自定义响应码
 */
@Getter
public enum ResponseCode {
    RC100(100, "操作成功"),
    RC200(200, "操作成功"),
    RC500(500, "系统异常，请稍后再试"),
    INVALID_TOKEN(2001, "没有访问资源的权限"),
    ACCESS_DENIED(2003, "没有权限访问该资源"),
    AUTHENTICATION_FAILED(1001, "客户端认证失败");

    final int code;
    final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
