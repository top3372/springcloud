package com.haili.ins.common.spring.web.exception;

import com.haili.ins.common.vo.ApiErrorResponse;
import com.haili.ins.common.vo.ResultInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author ming 定义全局异常处理
 * @RestControllerAdvice 是@controlleradvice 与@ResponseBody 的组合注解
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo<ApiErrorResponse> constraintViolationException(ConstraintViolationException ex) {
        return new ResultInfo(new ApiErrorResponse(500, 5001, ex.getMessage()), "500", ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo<ApiErrorResponse> IllegalArgumentException(IllegalArgumentException ex) {
        return new ResultInfo(new ApiErrorResponse(501, 5002, ex.getMessage()), "501", ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo<ApiErrorResponse> unknownException(Exception ex) {

        return new ResultInfo(new ApiErrorResponse(500, 5002, ex.getMessage()), "500", ex.getMessage());
    }
}