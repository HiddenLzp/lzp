package com.lzp.demo.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: RedisUtils
 */
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    static {
        redisTemplate = (RedisTemplate) SpringBeanUtil.getBean("redisTemplate");
    }

    /**
     * 俩天的秒数
     */
    public static final Long TWO_DAYS = 172800L;

    /**
     * 一周的秒数
     */
    public static final Long A_WEEK = 604800L;

    /**
     * 一个月的秒数 + 0至604800的随机秒数
     */
    public static final Long A_MONTH = 2592000L + getRandomSeconds();

    private static long getRandomSeconds(){
        double seconds = Math.random() * 604800;
        return (long)seconds;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public static void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public static void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public static void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public static boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public static Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存,带过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * List集合写入缓存
     *
     */
    public static boolean setList(final String key, Object object) {
        boolean result = false;
        try {
           redisTemplate.opsForList().rightPush(key,object);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * List集合写入缓存,带过期时间
     */
    public static boolean setList(final String key, Object object, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPush(key,object);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *List集合整个写入缓存
     */
    public static boolean setListAll(final String key, List list){
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key,list);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *List集合整个写入缓存,带过期时间
     */
    public static boolean setListAll(final String key,List list,Long expireTime){
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key,list);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取List集合
     */
    public static List getList(final String key,long start,long end) {
        List result = null;
        result = redisTemplate.opsForList().range(key,start,end);
        return result;
    }

    /**
     * Set集合写入缓存
     */
    public static boolean setSet(final String key, Set set) {
        boolean result = false;
        try {
            SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
            for (Object o : set) {
                setOperations.add(key, o);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean setSet(final String key, Object object) {
        boolean result = false;
        try {
            SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
            setOperations.add(key,object);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Set集合写入缓存,带过期时间
     */
    public static boolean setSet(final String key,Object object, Long expireTime) {
        boolean result = false;
        try {
            SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
            setOperations.add(key, object);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static boolean setSet(final String key, Set set, Long expireTime) {
        boolean result = false;
        try {
            SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
            for (Object o : set) {
                setOperations.add(key, o);
            }
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取Set集合
     */
    public static Set getSet(final String key) {
        Set result = null;
        result = redisTemplate.opsForSet().members(key);
        return result;
    }

    /**
     * Map集合写入缓存
     */
    public static boolean setMap(final String key, Map map) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Map集合写入缓存,带过期时间
     */
    public static boolean setMap(final String key, Map map, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 取出Map集合
     */
    public static Map getMap(final String key) {
        Map result = null;
        result = redisTemplate.opsForHash().entries(key);
        return result;
    }

    /**
     * 获取自增ID
     */
    public static Long incr(String key){
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long  increment  = redisAtomicLong.getAndIncrement();
//        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
//            redisAtomicLong.expire(liveTime, TimeUnit.SECONDS);
//        }
        return increment;
    }

}
