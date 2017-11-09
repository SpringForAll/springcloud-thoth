package com.prometheus.thoth.common.exception.handler;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.exception.*;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.model.RestResultBuilder;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * SpringMVC全局错误处理.
 * <p>
 * Created by liangliang on 2017/02/16.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    private RestResult runtimeExceptionHandler(Exception ex) {
        logger.error(getStackTrace(ex.getCause()), ex);
        return RestResultBuilder.builder().failure().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    private RestResult illegalParamsExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = Maps.newHashMapWithExpectedSize(bindingResult
                .getFieldErrorCount());
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        logger.warn("---------> invalid request! fields ex:{}", JSON.toJSONString(errors));
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.BAD_REQUEST).data
                (errors).build();
    }

    @ExceptionHandler(BindException.class)
    public RestResult bindExceptionHandler(BindException ex) {
        //ex.printStackTrace();
        Map<String, String> errors = Maps.newHashMapWithExpectedSize(ex.getFieldErrorCount());
        for (FieldError fieldError : ex.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        logger.warn("---------> invalid request! fields ex:{}", JSON.toJSONString(errors));
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.BAD_REQUEST).data
                (errors).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult messageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        logger.warn("---------> json convert failure, exception:{}", ex.getMessage());
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.BAD_REQUEST).build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResult methodArgumentExceptionHandler(MethodArgumentTypeMismatchException ex) {
        logger.error("---------> path variable, exception:{}", ex.getMessage());
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.BAD_REQUEST)
                .message(ex.getMessage()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public RestResult illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        logger.info("IllegalArgumentException exception:{}", ex.getMessage());
        if (ex instanceof ArgumentException) {
            ArgumentException exArg = (ArgumentException) ex;
            return RestResultBuilder.builder().errorCode(exArg.getErrorCode()).build();
        }
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.BAD_REQUEST).build();
    }

    @ExceptionHandler(BusinessException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult businessExceptionHandler(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        if (ex.getErrorCode() == null) {
            logger.warn("---------> business exception code:{}, message:{}",
                    ex.getCode(), ex.getMessage());
            return RestResultBuilder.builder().code(ex.getCode()).message(ex.getMessage()).build();
        }
        logger.warn("---------> business exception errorCode code:{}, message:{}",
                errorCode.getCode(), errorCode.getMessage());
        return RestResultBuilder.builder().errorCode(ex.getErrorCode()).build();
    }

    @ExceptionHandler(ApplicationException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult applicationExceptionHandler(ApplicationException ex) {
        logger.error("---------> application exception message:" + ex.getMessage(), ex);
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestResult noHandlerFoundException(NoHandlerFoundException ex) {
        logger.warn("noHandlerFoundException 404 error requestUrl:{}, method:{}, exception:{}",
                ex.getRequestURL(), ex.getHttpMethod(), ex.getMessage());
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.NOT_FOUND).build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public RestResult httpRequestMethodHandler(HttpServletRequest request,
                                               HttpRequestMethodNotSupportedException ex) {
        //ex.printStackTrace();
        logger.warn("httpRequestMethodHandler 405 error requestUrl:{}, method:{}, exception:{}",
                request.getRequestURI(), ex.getMethod(), ex.getMessage());
        return RestResultBuilder.builder().errorCode(GlobalErrorCode.METHOD_NOT_ALLOWED).build();
    }

    /**
     * get Throwable StackTrace.
     *
     * @param t
     * @return
     */
    protected String getStackTrace(Throwable t) {
        PrintWriter pw = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return "";
    }
}
