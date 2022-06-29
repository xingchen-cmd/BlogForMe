package com.forme.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis 保存属性
     * @param key
     * @param value
     * @param time 有过期时间
     */
    public void set(String key,Object value,long time){
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 保存属性无过期时间
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 获取属性
     * @param key
     * @return
     */
    public Object get(String key){
       return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除属性
     * @param key
     * @return
     */
    public Boolean del(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除属性
     * @param keys
     * @return
     */
    public Long del(List<String> keys){
        return redisTemplate.delete(keys);
    }

    /**
     * 设置过期时间
     * @param key
     * @param time
     * @return
     */
    public Boolean expire(String key,long time){
        return redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    /**
     * 获取key的过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断是否有该属性
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     *  按delta递增
     * @param key
     * @param delta
     * @return
     */
    public Long incr(String key,long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     *  按delta递减
     * @param key
     * @param delta
     * @return
     */
    public Long decr(String key,long delta){
        return redisTemplate.opsForValue().decrement(key,delta);
    }

    /**
     * 获取hash结构中的属性
     * @param key
     * @param hashKey
     * @return
     */
    public Object hGet(String key,String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    /**
     * 向hash结构中放入一个属性
     * @param key
     * @param hashKey
     * @param value
     */
    public void hSet(String key,String hashKey,Object value){
          redisTemplate.opsForHash().put(key,hashKey,value);
    }

    /**
     * 向hash结构中放入一个属性,并设置过期时间
     * @param key
     * @param hashKey
     * @param value
     * @param time
     * @return
     */
    public Boolean hSet(String key,String hashKey,Object value,long time){
        redisTemplate.opsForHash().put(key,hashKey,value);
        return expire(key,time);
    }

    /**
     * 获得整个hash结构
     * @param key
     * @return
     */
    public Map<Object,Object> hGetAll(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 保存一个hash结构，并设置过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public Boolean hSetAll(String key,Map<String,Object> map,long time){
        redisTemplate.opsForHash().putAll(key,map);
        return expire(key,time);
    }

    /**
     * 保存一个hash结构
     * @param key
     * @param map
     */
    public void hSetAll(String key,Map<String,?> map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    /**
     * 删除hash结构中的属性
     * @param key
     * @param hashKey
     */
    public void hDel(String key,Object... hashKey){
        redisTemplate.opsForHash().delete(key,hashKey);
    }

    /**
     * 判断hash结构中是否有该属性
     * @param key
     * @param hashKey
     * @return
     */
    public Boolean hHasKey(String key,String hashKey){
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    /**
     * hash结构中属性递增
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    public Long hIncr(String key,String hashKey,Long delta){
        return redisTemplate.opsForHash().increment(key,hashKey,delta);
    }

    /**
     * hash结构中属性递减
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    public Long hDecr(String key,String hashKey,Long delta){
        return redisTemplate.opsForHash().increment(key,hashKey,-delta);
    }

    /**
     * 获取set结构成员
     * @param key
     * @return
     */
    public Set<Object> sMembers(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 像set中添加成员
     * @param key
     * @param values
     */
    public void sAdd(String key,Object... values){
        redisTemplate.opsForSet().add(key,values);
    }

    /**
     * 是否为Set中属性
     * @param key
     * @param value
     * @return
     */
    public Boolean sIsMember(String key,Object value){
       return redisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * 获取Set结构长度
     * @param key
     * @return
     */
    public Long sSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 删除Set结构中的属性
     * @param key
     * @param values
     * @return
     */
    public Long sRemove(String key,Object... values){
        return redisTemplate.opsForSet().remove(key,values);
    }

    /**
     * 获取list结构中的属性
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> lRange(String key,long start,long end){
        return redisTemplate.opsForList().range(key,start,end);
    }

    /**
     * 获得list结构的长度
     * @param key
     * @return
     */
    public Long lSize(String key){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 获得list结构中的属性
     * @param key
     * @param index
     * @return
     */
    public Object lIndex(String key,long index){
        return redisTemplate.opsForList().index(key,index);
    }

    /**
     * 向list结构中添加属性
     * @param key
     * @param value
     * @return
     */
    public Long lPush(String key,Object value){
        return redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     * 向list中添加属性，并设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public Long lPush(String key,Object value,long time){
        Long index=redisTemplate.opsForList().rightPush(key,value);
        expire(key,time);
        return index;
    }

    /**
     * 向list中批量添加属性
     * @param key
     * @param values
     * @return
     */
    public Long lPushAll(String key,Object... values){
        return redisTemplate.opsForList().rightPushAll(key,values);
    }

    /**
     * 向list结构中批量添加属性并且添加过期时间
     * @param key
     * @param time
     * @param values
     * @return
     */
    public Long lPushAll(String key, long time,Object... values){
        Long index=redisTemplate.opsForList().rightPushAll(key,values);
        expire(key,time);
        return index;
    }

    /**
     * 从list结构中删除属性
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long lRemove(String key,long count ,Object value){
        return redisTemplate.opsForList().remove(key,count,value);
    }





}
