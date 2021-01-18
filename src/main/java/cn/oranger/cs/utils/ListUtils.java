package cn.oranger.cs.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 列表操作工具类。
 * 
 * @author Wu.Zheng
 */
public abstract class ListUtils {

    /**
     * 从给定的List中根据索引寻找对应的元素，如果不存在对应索引的元素，则返回null。
     * 
     * @param list 列表
     * @param index 元素索引
     * @return 索引对应的元素，如果不存在对应索引的元素，则返回null。
     */
    public static <T> T get(List<T> list, int index) {
        if (list != null && list.size() > index) {
            return list.get(index);
        } else {
            return null;
        }
    }

    /**
     * 从给定的List中获取第一个元素，如果给定的List为空，则返回null。
     * 
     * @param list 列表
     * @return 第一个元素，如果给定的List为空，则返回null。
     */
    public static <T> T getFirst(List<T> list) {
        return get(list, 0);
    }

    /**
     * 从给定的List中获取最后一个元素，如果给定的List为空，则返回null。
     * 
     * @param list 列表
     * @return 最后一个元素，如果给定的List为空，则返回null。
     */
    public static <T> T getLast(List<T> list) {
        int size = CollectionUtils.size(list);
        if (size > 0) {
            return list.get(size - 1);
        } else {
            return null;
        }
    }

    public static <T> List<T> truncate(List<T> list, int targetSize) {
        int currentSize = CollectionUtils.size(list);
        int excessAmount = currentSize - targetSize;
        if (excessAmount > 0) {
            for (int i = 0; i < excessAmount; ++i) {
                list.remove(currentSize - 1 - i);
            }
        }
        return list;
    }

    public static <T> List<List<T>> split(List<T> list, int splitSize) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        List<List<T>> result = new ArrayList<>();
        List<T> item = null;
        for (int i = 0, size = list.size(); i < size; ++i) {
            if (i % splitSize == 0) {
                item = new ArrayList<>();
                result.add(item);
            }
            item.add(list.get(i));
        }
        return result;
    }

    public static <T> List<T> fromArray(T[] ts) {
        List<T> list = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(ts)) {
            for (T t : ts) {
                list.add(t);
            }
        }
        return list;
    }

    public static <F, T> List<T> mappedList(final List<F> list, final Function<? super F, ? extends T> mapper) {
        List<T> result = null;
        if (CollectionUtils.isNotEmpty(list)) {
            result = list.stream().map(mapper).collect(Collectors.toList());
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    public static <F, T> List<T> transformedList(final List<F> list, final Transformer<? super F, ? extends T> transformer) {
        return mappedList(list, f -> transformer.transform(f));
    }

}
