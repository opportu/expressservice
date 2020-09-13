package com.service;

import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisCacheService {

    /**
     * 初始化 redis 序列化器
     * @param clazz 传入类变量
     * @return 返回序列化对象
     */
    RedisSerializer getSerializer(Class clazz);

    /**
     * 通过键值获取对应的值
     * @param key 传入的键值
     * @return 返回该键对应的值
     */
    String getValue(String key);

    /**
     * 保存具有过期时间的键值对
     * @param key 键值
     * @param value 键值对应的值
     * @param expireTime 过期时间
     */
    void saveValueWithExpireTime(String key, String value, long expireTime);

    /**
     * 保存键值对
     * @param key 键值
     * @param value 键值对应的值
     */
    void saveValue(String key, Object value);

    <T> void saveObjectWithExpireTime(final String key, final T obj, final long expireTime, TimeUnit timeUnit);

    <T> void saveObject(String key, T obj);

    <T> T getObject(String key, Class<T> valueClass);

    <T> List<T> getListData(final String key, final Class<T> clazz);

    <T> void saveListData(String key, List<T> list, Class<T> clazz);

    <T> void setListDataWithExpireTime(final String key, final List<T> list, final long expireTime,
                            final Class<T> clazz);

    <T> void hashSaveWithExpireTime(final String key, final String field, final byte[] bytesValue, final long expireTime);

    <T> void hashSave(final String key, final String field, final byte[] bytesValue);

    <T> void hashSaveWithExpireTime(final String key, String field, T value, long expireTime, final Class<T> clazz);

    <T> void hashSave(final String key, String field, T value, final Class<T> clazz);

    <T> T hashGet(final String key, final String field, final Class<T> clazz);

    <K, V> Map<K, V> hashGetAll(final String key, final Class<K> fieldClazz, final Class<V> valueClazz);

    <K, V> void hashMultiSave(final String key, Map<K, V> map, final Class<K> fieldClazz, final Class<V> valueClazz);

    void delete(String key);

    void hashDelete(String key, String field);

    boolean hasKey(String key);

    void saveInteger(final String key, final int value);

    Object getInteger(final String key);

    Integer incrementAndGet(final String key);



}
