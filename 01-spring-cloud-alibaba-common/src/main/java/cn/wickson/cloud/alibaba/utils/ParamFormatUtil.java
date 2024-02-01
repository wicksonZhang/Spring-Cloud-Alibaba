package cn.wickson.cloud.alibaba.utils;

import cn.hutool.core.util.ObjUtil;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.ParameterException;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 格式化处理实体类实例中成员属性值工具类
 */
public class ParamFormatUtil {

    /**
     * 格式化实体类实例中的字符串参数的左右空格排除处理
     *
     * @param entityParam
     */
    public static void formatParam(final Object entityParam) {
        Class<?> clazz = entityParam.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            Arrays.stream(fields).forEach(field -> {
                field.setAccessible(true);
                try {
                    Object obj = field.get(entityParam);
                    if (ObjUtil.isNotNull(obj) && obj instanceof String) {
                        String value = obj.toString().trim();
                        field.set(entityParam, value);
                    }
                } catch (IllegalAccessException e) {
                    throw ParameterException.getInstance(ResultCodeEnum.PARAM_REQUEST_DATA_FORMAT_INVALID);
                }
            });
        }
    }

}
