package cn.wickson.cloud.alibaba.exception;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 三方接口调用异常类
 */
@Getter
@Setter
public class TripartiteInterfaceException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;

    /**
     * 异常代码描述
     */
    private String description;

    /**
     * 异常 resultCode
     */
    private ResultCodeEnum resultCode;

    /**
     * 构造器：有参数的构造器
     */
    public TripartiteInterfaceException(final Integer code, final String message) {
        this.code = code;
        this.description = message;
    }

    /**
     * 格式化为字符串
     *
     * @return
     */
    @Override
    public String toString() {
        return "ParamException{" +
                "code=" + code +
                ", message='" + description + '\'' +
                '}';
    }

    /**
     * 获取类实例
     *
     * @param code
     * @return
     */
    public static TripartiteInterfaceException getInstance(final Integer code, final String message) {
        return new TripartiteInterfaceException(code, message);
    }

    /**
     * 获取类实例
     *
     * @param resultCode
     * @return
     */
    public static TripartiteInterfaceException getInstance(final ResultCodeEnum resultCode) {
        return new TripartiteInterfaceException(resultCode.getCode(), resultCode.getDescription());
    }

}
