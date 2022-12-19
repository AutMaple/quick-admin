package com.autmaple.oauth.components.response;

import com.autmaple.oauth.common.api.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 响应结果统一处理类
 */
@SuppressWarnings({"rawtypes"})
//@RestControllerAdvice
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof LinkedHashMap) { // 处理 BasicErrorController 传递过来的参数
            Map map = (LinkedHashMap) body;
            int code = (int) map.get("status");
            String msg = (String) map.get("error");
            return ResponseResult.failed(code, msg);
        }

        if (body instanceof String) { // 处理返回值为 String 类型的参数
            return this.objectMapper.writeValueAsString(ResponseResult.success(body));
        }

        if (body instanceof ResponseResult) // 异常被 ControllerAdvice 捕获的情况
            return body;
        return ResponseResult.success(body);
    }
}
