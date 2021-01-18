/**
 * 
 */
package cn.oranger.cs.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author wuzheng@tiduyun.com
 *
 */
public class MapUtils {

    public static final Map<String, String> EMPTY_STRING_MAP = Collections.unmodifiableMap(new HashMap<>());

    public static final Map<Integer, Integer> EMPTY_INTEGER_MAP = Collections.unmodifiableMap(new HashMap<>());

    public static final Map<String, Object> EMPTY_STRING_OBJECT_MAP = Collections.unmodifiableMap(new HashMap<>());

    public static final Map<Integer, Object> EMPTY_INTEGER_OBJECT_MAP = Collections.unmodifiableMap(new HashMap<>());

    public static final Map<Integer, String> EMPTY_INTEGER_STRING_MAP = Collections.unmodifiableMap(new HashMap<>());

    public static <R, K> Map<K, R> fromCollection(Collection<R> col, Function<? super R, ? extends K> keyMapper) {
        return fromStream(col.stream(), keyMapper, v -> v);
    }

    public static <R, K, V> Map<K, V> fromCollection(Collection<R> col, Function<? super R, ? extends K> keyMapper,
        Function<? super R, ? extends V> valueMapper) {
        return fromStream(col.stream(), keyMapper, valueMapper);
    }

    public static <R, K> Map<K, R> fromStream(Stream<R> stream, Function<? super R, ? extends K> keyMapper) {
        return fromStream(stream, keyMapper, v -> v);
    }

    public static <R, K, V> Map<K, V> fromStream(Stream<R> stream, Function<? super R, ? extends K> keyMapper,
        Function<? super R, ? extends V> valueMapper) {
        Map<K, V> map = new HashMap<>();
        if (stream == null) {
            return map;
        }
        stream.filter(Objects::nonNull).forEach(e -> map.put(keyMapper.apply(e), valueMapper.apply(e)));
        return map;
    }

    public static <T> Map<T, T> fromKeyValueArray(T[][] entries) {
        Map<T, T> map = new HashMap<>();
        if (ArrayUtils.isNotEmpty(entries)) {
            for (T[] entry : entries) {
                if (ArrayUtils.isEmpty(entry)) {
                    continue;
                }
                T key = entry[0];
                if (key == null) {
                    continue;
                }

                if (entry.length > 1) {
                    map.put(key, entry[1]);
                } else {
                    map.put(key, null);
                }
            }
        }
        return map;
    }

    public static <K, V> Map<K, V> fromEntryArray(Map.Entry<? extends K, ? extends V>[] entryArray) {
        Map<K, V> map = new HashMap<>();
        if (ArrayUtils.isNotEmpty(entryArray)) {
            for (Map.Entry<? extends K, ? extends V> entry : entryArray) {
                if (entry.getKey() == null) {
                    continue;
                }

                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public static <K, V> V orGet(Map<? extends K, ? extends V> map, K key, K orKey) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return map.get(orKey);
        }
    }

    public static Map<String, String> toStringMap(Map<?, ?> sourceMap) {
        if (sourceMap == null) {
            return null;
        }

        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<?, ?> entry : sourceMap.entrySet()) {
            stringMap.put(Objects.toString(entry.getKey()), Objects.toString(entry.getValue()));
        }
        return stringMap;
    }

    public static <V> Map<String, V> toStringKeyMap(Map<?, V> sourceMap) {
        if (sourceMap == null) {
            return null;
        }

        Map<String, V> stringMap = new HashMap<>();
        for (Map.Entry<?, V> entry : sourceMap.entrySet()) {
            stringMap.put(Objects.toString(entry.getKey()), entry.getValue());
        }
        return stringMap;
    }

    public static <K> Map<K, String> toStringValueMap(Map<K, String> sourceMap) {
        if (sourceMap == null) {
            return null;
        }

        Map<K, String> stringMap = new HashMap<>();
        for (Map.Entry<K, String> entry : sourceMap.entrySet()) {
            stringMap.put(entry.getKey(), Objects.toString(entry.getValue()));
        }
        return stringMap;
    }

    public static <K, V> void putIfValueNotNull(Map<K, V> map, K key, V value) {
        if (map != null && value != null) {
            map.put(key, value);
        }
    }

    public static <K, V> void addItemToList(Map<K, List<V>> map, K key, V item) {
        List<V> items = map.get(key);
        if (items == null) {
            items = new ArrayList<>();
            map.put(key, items);
        }
        items.add(item);
    }

    public static <K, V> void addItemToSet(Map<K, Set<V>> map, K key, V item) {
        Set<V> items = map.get(key);
        if (items == null) {
            items = new HashSet<>();
            map.put(key, items);
        }
        items.add(item);
    }

    public static <K, E, V> void putItemToSubMap(Map<K, Map<E, V>> map, K key, E subKey, V item) {
        Map<E, V> subMap = map.get(key);
        if (subMap == null) {
            subMap = new HashMap<>();
            map.put(key, subMap);
        }
        subMap.put(subKey, item);
    }

    public static <V, K> Map<K, List<V>> groupByKey(Collection<V> values, Function<? super V, ? extends K> keyMapper) {
        return groupByKey(values, keyMapper, v -> v);
    }

    public static <T, K, V> Map<K, List<V>> groupByKey(Collection<T> values, Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends V> valueMapper) {
        Map<K, List<V>> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(values)) {
            values.stream().filter(Objects::nonNull).forEach(v -> {
                K key = keyMapper.apply(v);
                V value = valueMapper.apply(v);
                addItemToList(map, key, value);
            });
        }
        return map;
    }

    public static <V, K> Map<K, Set<V>> groupToSetByKey(Collection<V> values,
        Function<? super V, ? extends K> keyMapper) {
        return groupToSetByKey(values, keyMapper, v -> v);
    }

    public static <T, K, V> Map<K, Set<V>> groupToSetByKey(Collection<T> values,
        Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Map<K, Set<V>> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(values)) {
            values.stream().filter(Objects::nonNull).forEach(v -> {
                K key = keyMapper.apply(v);
                V value = valueMapper.apply(v);
                addItemToSet(map, key, value);
            });
        }
        return map;
    }

    public static <K, V, X extends K, Y extends V> boolean contains(Map<K, V> map, Map<X, Y> values) {
        if (map == null || values == null) {
            return false;
        }
        if (map.isEmpty() && values.isEmpty()) {
            return true;
        } else if (map.isEmpty()) {
            return false;
        } else if (values.isEmpty()) {
            return true;
        }

        for (Map.Entry<X, Y> valueEntry : values.entrySet()) {
            X key = valueEntry.getKey();
            Y value = valueEntry.getValue();
            if (!map.containsKey(key)) {
                return false;
            }
            if (!Objects.equals(value, map.get(key))) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> void putAllToFirstMap(Map<K, V> m1, Map<K, V> m2) {
        putAllToFirstMap(new Map[] {m1, m2});
    }

    @SuppressWarnings("unchecked")
    public static <K, V> void putAllToFirstMap(Map<K, V> m1, Map<K, V> m2, Map<K, V> m3) {
        putAllToFirstMap(new Map[] {m1, m2, m3});
    }

    public static <K, V> void putAllToFirstMap(Map<K, V>[] maps) {
        if (ArrayUtils.isEmpty(maps)) {
            return;
        }

        Map<K, V> firstMap = maps[0];
        if (firstMap == null) {
            throw new IllegalArgumentException("first map must not be null");
        }

        Map<K, V> map = null;
        for (int i = 0, count = maps.length; i < count; ++i) {
            map = maps[i];
            if (map != null && !map.isEmpty()) {
                firstMap.putAll(map);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> putAllToNewHashMap(Map<K, V> m1, Map<K, V> m2, Map<K, V> m3) {
        return putAllToNewHashMap(new Map[] {m1, m2, m3});
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> putAllToNewHashMap(Map<K, V> m1, Map<K, V> m2) {
        return putAllToNewHashMap(new Map[] {m1, m2});
    }

    public static <K, V> Map<K, V> putAllToNewHashMap(Map<K, V>[] maps) {
        Map<K, V> map = new HashMap<>();

        if (ArrayUtils.isNotEmpty(maps)) {
            for (Map<K, V> temp : maps) {
                if (temp != null && !temp.isEmpty()) {
                    map.putAll(temp);
                }
            }
        }

        return map;
    }

    public static <K, V> Map.Entry<K, V> getFirstEntry(Map<K, V> map) {
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                return entry;
            }
        }
        return null;
    }
}
