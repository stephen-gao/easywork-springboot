package com.gao.cache;

/**
 * @Author gs
 * @Date created time 2019/5/5 11:29
 * @Description
 */
public interface ICache<HK, V> {

    /**
     * 60秒
     */
    public static final long ONE_MIN_SECONDS = 60;

    /**
     * 1秒
     */
    public static final long ONE_SECOND = 1;

    /**
     * 3秒
     */
    public static final long THREE_SECONDS = 3*ONE_SECOND;

    /**
     * 10秒
     */
    public static final long TEN_SECONDS = 10*ONE_SECOND;

    /**
     * 3分钟
     */
    public static final long THREE_MIN_SECONDS = 3 * ONE_MIN_SECONDS;

    /**
     * 5分钟
     */
    public static final long FIVE_MIN_SECONDS = 5 * ONE_MIN_SECONDS;

    /**
     * 10分钟
     */
    public static final long TEN_MIN_SECONDS = 10 * ONE_MIN_SECONDS;

    /**
     * 半小时
     */
    public static final long HALF_HOUR_SECONDS = 30 * ONE_MIN_SECONDS;

    /**
     * 1小时
     */
    public static final long ONE_HOUR_SECONDS = 60 * ONE_MIN_SECONDS;

    /**
     * 12小时
     */
    public static final long TWELVE_HOUR_SECONDS = 12 * 60 * ONE_MIN_SECONDS;

    /**
     * 24小时
     */
    public static final long ONE_DAY_SECONDS = 24 * 60 * ONE_MIN_SECONDS;

    /**
     * 7天
     */
    public static final long ONE_WEEK_SECONDS = 7 * 24 * 60 * ONE_MIN_SECONDS;

    /**
     * 一月，最大时间
     */
    public static final long ONE_MONTH_SECONDS = 30 * 24 * 60 * ONE_MIN_SECONDS;


    /**
     * 保存自定义时长
     */
    void set(final String key, V value, Long expireTime);


    /**
     * 保存5分钟
     * @param key
     * @param value
     */
    void setFiveMin(String key, V value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    void setTenMin(String key, V value);

    /**
     * 保存半小时
     * @param key
     * @param value
     */
    void setHalfHour(String key, V value);

    /**
     * 保存一小时
     * @param key
     * @param value
     */
    void setOneHour(String key, V value);


    /**
     * @param key
     * @return
     */
    V get(final String key);

    /**
     * @param key
     * @return
     */
    public void remove(final String key);

    /**
     * 重置过期时间半小时
     * @param key
     */
    void expireHalfHour(String key);
}
