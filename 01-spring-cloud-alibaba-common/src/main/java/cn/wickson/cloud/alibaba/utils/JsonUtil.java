package cn.wickson.cloud.alibaba.utils;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * bean转换JSON工具类
 *
 * @auhor CaoJing
 * @create 2023-03-13 21:40
 */
public class JsonUtil {

    /**
     * 获取Json格式的操作内容
     *
     * @return
     */
    public static String toJsonStr(final Object oldRecord, final Object newRecord) {
        Map<Object,Object> operationContentMap = new HashMap<Object,Object>();
        operationContentMap.put("beforeUpdate", oldRecord);
        operationContentMap.put("afterUpdating", newRecord);

        return toJsonStr(operationContentMap);
    }

    /**
     * 获取Json格式的操作内容
     *
     * @return
     */
    public static String toJsonStr(final Object newRecord) {
        return JSONUtil.toJsonStr(newRecord);
    }

}
