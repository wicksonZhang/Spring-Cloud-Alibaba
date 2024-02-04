package cn.wickson.cloud.alibaba.handle;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.ParameterException;
import cn.wickson.cloud.alibaba.exception.TripartiteInterfaceException;
import cn.wickson.cloud.alibaba.exception.UserOperationException;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局异常统一处理类
 *
 * @author ZhangZiHeng
 * @date 2023-12-27
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResultUtil handleThrowable(Throwable e, HttpServletRequest request) {
        log.error("requestUrl：{}，系统内部异常", request.getRequestURI(), e);
        return ResultUtil.failure(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 路径变量异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingPathVariableException.class)
    public ResultUtil handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        log.error("requestUrl：{}，请求参数异常", request.getRequestURI(), e);
        return ResultUtil.failure(ResultCodeEnum.PARAM_IS_BLANK);
    }

    /**
     * 参数类型错误异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultUtil handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.error("requestUrl：{}，请求参数错误", request.getRequestURI(), e);
        return ResultUtil.failure(ResultCodeEnum.PARAM_TYPE_BIND_ERROR);
    }

    /**
     * 自定义用户操作异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserOperationException.class)
    public ResultUtil handleUserOperationException(UserOperationException e, HttpServletRequest request) {
        log.error("requestUrl：{}，用户操作异常{code={}，message={}}", request.getRequestURI(), e.getCode().getCode(),
                e.getDescription());
        return ResultUtil.failure(e.getCode(), e.getDescription());
    }

    /**
     * 自定义用户操作异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(TripartiteInterfaceException.class)
    public ResultUtil handleTripartiteInterfaceException(TripartiteInterfaceException e, HttpServletRequest request) {
        log.error("requestUrl：{}，用户操作异常{code={}，message={}}", request.getRequestURI(), e.getCode(),
                e.getDescription());
        return ResultUtil.failure(e.getCode(), e.getDescription());
    }

    /**
     * 自定义参数错误异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ParameterException.class)
    public ResultUtil handleParamException(ParameterException e, HttpServletRequest request) {
        log.error("requestUrl：{}，用户操作异常{code={}，message={}}", request.getRequestURI(), e.getCode().getCode(),
                e.getDescription(), e);
        return ResultUtil.failure(e.getCode(), e.getDescription());
    }

    /**
     * 不支持请求方法异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultUtil handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("requestUrl：{}，用户操作异常", request.getRequestURI(), e);
        String errorMessage = String.format("请求方法无效，不支持%s请求", request.getMethod());
        return ResultUtil.failure(ResultCodeEnum.USER_REQUEST_METHOD_INVALID, errorMessage);
    }

    /**
     * 请求参数格式错误异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultUtil handleHttpRequestMethodNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error("requestUrl：{}，用户操作异常", request.getRequestURI(), e);
        return ResultUtil.failure(ResultCodeEnum.PARAM_REQUEST_DATA_FORMAT_INVALID);
    }

    /**
     * 请求参数校验异常处理方式一
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultUtil handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("requestUrl：{}，参数校验失败", request.getRequestURI(), e);
        System.out.println(e.getMessage());
        return ResultUtil.failure(ResultCodeEnum.PARAM_REQUEST_DATA_FORMAT_INVALID);
    }

    /**
     * 请求参数校验异常处理方式二
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultUtil handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("requestUrl：{}，参数校验失败", request.getRequestURI(), e);
        ResultCodeEnum codeEnum = ResultCodeEnum.PARAM_VALIDATED_FAILURE;
        String msg = this.messageFormat(codeEnum.getDescription(), e.getFieldErrors());
        return ResultUtil.failure(codeEnum, msg);
    }

    /**
     * 请求参数校验异常处理方式三
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public ResultUtil handleBindException(BindException e, HttpServletRequest request) {
        log.error("requestUrl：{}，参数校验失败", request.getRequestURI(), e);
        ResultCodeEnum codeEnum = ResultCodeEnum.PARAM_IS_INVALID;
        String msg = this.messageFormat(codeEnum.getDescription(), e.getFieldErrors());
        return ResultUtil.failure(ResultCodeEnum.PARAM_IS_INVALID, msg);
    }

    /**
     * 请求参数校验异常处理方式四
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultUtil handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        log.error("requestUrl：{}，参数校验失败", request.getRequestURI(), e);
        ResultCodeEnum codeEnum = ResultCodeEnum.PARAM_IS_BLANK;
        String msg = codeEnum.getDescription() + "[" + e.getMessage() + "]";
        return ResultUtil.failure(codeEnum, msg);
    }

    /**
     * 将validator数据校验异常信息格式化处理
     */
    private String messageFormat(String topic, List<FieldError> fieldErrorList) {
        StringBuilder msg = new StringBuilder();
        msg.append(topic);
        msg.append("：[");
        fieldErrorList.forEach(fieldError -> {
            msg.append(fieldError.getField());
            msg.append("=");
            msg.append(fieldError.getDefaultMessage());
            msg.append(" ");
        });
        msg.append("]");
        return msg.toString();
    }

}
