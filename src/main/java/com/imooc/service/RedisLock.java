package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {

        //setIfAbsent就是redis的setnx命令
        //在新的springboot版本中 引入的redis start将setIfAbsent设置了过期时间，所以如果升级版本分布式锁就不用这么麻烦了
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //如果能成功设置返回true,这里意思也就是可以锁住了
            return true;
        }
        // A = currrntValue 从redis取出的是A. 这两个线程的value的值都是B.
        String currrntValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currrntValue)
                && Long.parseLong(currrntValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            //getAndSet方法就是redis命令中的GETSET命令，就是先get后set
           /* 这里我们解释一下下面代码的意义：我们假设有两个线程同时进来，这两个线程的value值都是一样的，我们假设是B
                A = currrntValue
              这两个线程执行下面代码的时候总是要有一个先后顺序的，我们假设是线程1先执行的。那么，这个时候的oldValue就是A 也就是等于currrntValue
              这样线程1就拿到锁了。同时把值设置成了B
              然后线程2也执行下面的方法，但是这个时候的oldValue 已经是B了（被线程1更改了），所以线程2不能进入下面的if方法
              也就不能拿到锁了
           */
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currrntValue)) {
                //获取到锁
                return true;
            }
        }
        //不能得到锁
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currrntValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currrntValue)
                    && currrntValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
//            log.error("redis分布式锁解锁异常，{}",e);
        }

    }


} 