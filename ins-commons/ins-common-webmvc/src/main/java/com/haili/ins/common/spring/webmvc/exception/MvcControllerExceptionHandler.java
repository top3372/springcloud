package com.haili.ins.common.spring.webmvc.exception;

import com.haili.ins.common.vo.ApiErrorResponse;
import com.haili.ins.common.vo.ResultInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * @author ming 定义全局异常处理
 * @RestControllerAdvice 是@controlleradvice 与@ResponseBody 的组合注解
 */
@RestControllerAdvice
public class MvcControllerExceptionHandler {


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultInfo<ApiErrorResponse> noHandlerFoundException(Exception ex) {
        return new ResultInfo(new ApiErrorResponse(404, 4041, ex.getMessage()), "404", ex.getMessage());
    }

}