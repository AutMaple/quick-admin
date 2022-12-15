package com.autmaple.oauth.components;

import com.autmaple.oauth.common.api.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        return ResponseEntity.accepted().body(body);
        if (body instanceof String) {
            return this.objectMapper.writeValueAsString(ResponseResult.success(body));
        }
        if (body instanceof ResponseResult)
            return body;
        return ResponseResult.success(body);
    }
}
