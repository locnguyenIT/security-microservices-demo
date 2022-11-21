package com.ntloc.orders.exception;

import feign.FeignException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

//@RestControllerAdvice
public class FeignExceptionHandler {

//    @ExceptionHandler(value = FeignException.class)
//    public String handleFeignClientException(FeignException feign, HttpServletResponse response) throws JSONException {
//        System.out.println(feign.getMessage());
//        JSONObject jsonObject = new JSONObject(feign.contentUTF8());
//        if(Objects.nonNull(jsonObject.get("message"))) {
//            return jsonObject.get("message").toString();
//
//
//        }
//        return null;
//    }
}
