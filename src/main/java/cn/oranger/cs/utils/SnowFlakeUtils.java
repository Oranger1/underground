package cn.oranger.cs.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import javax.naming.ldap.PagedResultsControl;

/**
 * @author Oranger
 * @date 2021/3/7
 */
public class SnowFlakeUtils {
    private static Long workerId = (long)0;
    private static Long dataCenterId = (long)1;

    public static Long getSnowFlakeId(){
        Snowflake snowflake = IdUtil.getSnowflake(workerId,dataCenterId);
        return snowflake.nextId();
    }
}
