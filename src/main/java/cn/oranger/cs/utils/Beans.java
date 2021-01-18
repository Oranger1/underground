package cn.oranger.cs.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Bean 辅助类
 * @author Oranger
 * @date 2021/1/3
 */
public class Beans {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static boolean isNull(Object o) {
        return Objects.isNull(o);
    }

    public static boolean isNotNull(Object o) {
        return Objects.nonNull(o);
    }

    public static boolean strEmpty(String s) {
        return (null == s || "".equals(s));
    }

    public static boolean strNotEmpty(String s) {
        return !Beans.strEmpty(s);
    }

    /**
     *  实例化一个对象
     * @author Daizhiqiang
     * @date 2019/5/31 14:10
     * @param clazz: 实例化对象类型
     * @return 实例化对象
     */
    public static <T> T instance(Class<T> clazz){
        return BeanUtils.instantiateClass(clazz);
    }
    public static <T> T to(Object obj, Class<T> cls) {
        return Beans.beans(Beans.json(obj), cls);
    }
    /***
     *  拷贝一个对象
     *  @author Daizhiqiang
     * @date 2019/5/31 14:11
     * @param src: 源对象, targetClazz: 目标对象类型
     * @return 被复制的对象
     */
    public static <T> T copy(Object src, Class<T> targetClazz){
        T target = Beans.instance(targetClazz);
        BeanUtils.copyProperties(src, target);
        return target;
    }

    /***
     *  对象转成json
     * @author Daizhiqiang
     * @date 2019/5/31 14:11
     * @param o: 源对象, targetClazz: 目标对象类型
     * @return json 字符串
     */
    public static String json(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    /**
     * 功能: 从json转成对象
     * @author Daizhiqiang
     * @date 2019/5/31 14:14
     * @param json: json字符串, targetClazz: 目标对象类型
     * @return obj 对象
     */
    public static <T> T  beans(String json, Class<T> clazz){
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Beans.instance(clazz);
    }



    /**
     * 功能: 从json字符串转成map
     * @author Daizhiqiang
     * @date 2019/5/31 14:14
     * @param jsonStr: json 字符串
     * @return map
     */
    public static Map<String, Object> str2map(String jsonStr) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr,
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    /**
     * 功能: 从对象转成map
     * @author Daizhiqiang
     * @date 2019/5/31 14:14
     * @param o: 对象
     * @return map
     */
    public static Map<String, Object> maps(Object o) {
        try {
            return OBJECT_MAPPER.readValue(Beans.json(o),
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }


    /**
     * 功能: 从对象转成map,key值从驼峰转为下划线
     * @author Daizhiqiang
     * @date 2019/5/31 14:14
     * @param o: 对象
     * @return map
     */
    public static Map<String, Object> dbMaps(Object o) {
        Map<String, Object> dbMaps = new HashMap<>();
        Beans.maps(o).forEach((k,v)->{
            if (v != null) {
                dbMaps.put(Beans.camelToUnderline(k), v);
            }

        });
        return dbMaps;
    }

    /**
     * 字符串驼峰转下划线格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    private static String camelToUnderline(String param) {
        if (Beans.strEmpty(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 功能: 从map转成对象
     * @author Daizhiqiang
     * @date 2019/5/31 14:14
     * @param o: 要转换的对象, targetClazz: 目标对象类型
     * @return obj 对象
     */
    public static <T> T  obj(Map<String, Object> o, Class<T> clazz) {
        return Beans.beans(Beans.json(o), clazz);
    }



}
