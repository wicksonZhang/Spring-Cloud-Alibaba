package cn.wickson.cloud.alibaba.exception;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 前端参数错误异常类
 */
@Getter
@Setter
public class ParameterException extends RuntimeException {

    /**
     * 异常代码
     */
    private ResultCodeEnum code;

    /**
     * 异常代码描述
     */
    private String description;

    /**
     * 构造器：有参数的构造器
     */
    public ParameterException(final ResultCodeEnum code) {
        this.code = code;
        this.description = code.getDescription();
    }

    /**
     * 构造器：有参数的构造器
     */
    public ParameterException(final ResultCodeEnum code, final String message) {
        this.code = code;
        this.description = String.format(code.getDescription() + "{%s}", message);;
    }

    /**
     * 格式化为字符串
     *
     * @return
     */
    @Override
    public String toString() {
        return "ParamException{" +
                "code=" + code.getCode() +
                ", message='" + description + '\'' +
                '}';
    }

    /**
     * 获取类实例
     *
     * @param code
     * @return
     */
    public static ParameterException getInstance(final ResultCodeEnum code) {
        return new ParameterException(code);
    }

    /**
     * 获取类实例
     *
     * @param code
     * @return
     */
    public static ParameterException getInstance(final ResultCodeEnum code, final String message) {
        return new ParameterException(code, message);
    }

}
