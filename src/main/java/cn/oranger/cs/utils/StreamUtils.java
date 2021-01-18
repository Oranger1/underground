package cn.oranger.cs.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author 洪笠翔
 *
 */
public class StreamUtils {

    public static <T> List<T> filter(Collection<T> col, Predicate<T> p) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().filter(p).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(Collection<T> col, Predicate<T> p, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().filter(p).map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(Collection<T> col, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toListFilterNull(Collection<T> col, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(Collection<T> col, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptySet();
        }
        return col.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, K> Map<K, T> toMap(Collection<T> col, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.toMap(keyMapper, t -> t));
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> col, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T> Integer sumInteger(Collection<T> col, ToIntFunction<T> mapper) {
        return col.stream().collect(Collectors.summingInt(mapper));
    }

    public static <T> Long sumLong(Collection<T> col, ToLongFunction<T> mapper) {
        return col.stream().collect(Collectors.summingLong(mapper));
    }

    public static <T> Long sumLong(Collection<T> col, Predicate<T> p, ToLongFunction<T> mapper) {
        return col.stream().filter(p).collect(Collectors.summingLong(mapper));
    }

    public static <T, K> Map<K, List<T>> group(Collection<T> col, Function<T, K> function) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.groupingBy(function));
    }

    public static <T, K> Map<K, Long> groupCount(Collection<T> col, Function<T, K> function) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.groupingBy(function, Collectors.counting()));
    }

    public static <T, K> Map<K, List<T>> filterGroup(Collection<T> col, Predicate<T> p, Function<T, K> function) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().filter(p).collect(Collectors.groupingBy(function));
    }

    public static <T> T getFirst(T[] col, Predicate<? super T> p) {
        if (col == null || col.length == 0) {
            return null;
        }
        return Stream.of(col).filter(p).findFirst().orElse(null);
    }

    public static <T> T getFirst(Collection<T> col, Predicate<? super T> p) {
        if (CollectionUtils.isEmpty(col)) {
            return null;
        }
        return col.stream().filter(p).findFirst().orElse(null);
    }
}
