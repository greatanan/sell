package com.imooc.service.impl;

import com.imooc.exception.SellException;
import com.imooc.service.RedisLock;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SeckillServiceImpl {

    private static final int TIMEOUT = 10*1000;//超时时间

    @Autowired
    RedisLock redisLock;

    /**
     * 国庆活动，皮蛋粥特价。限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static{
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }

    private String queryMap(String productId){
        return "国庆活动，皮蛋粥特价，限量份"+ products.get(productId)
                +" 还剩： " +stock.get(productId) +"份"
                + " 该商品成功下单用户数： "+orders.get(productId) +"人";
    }

    public String querySecKillProductInfo(String productId){
        return this.queryMap(productId);
    }

    public void orderProductMockDiffUser(String productId ){

        //当前时间加上超时时间
        long time = System.currentTimeMillis()+TIMEOUT;

        if(!redisLock.lock(productId,String.valueOf(time))){
            //如果加锁失败
            throw new SellException(101,"当前抢购人员过多！");
        }

        //查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if(stockNum == 0){
            throw new SellException(100,"活动结束");
        }else{
            //下单（模拟不同用户openId不同）
            orders.put(KeyUtil.genUniqueKey(),productId);
            //减库存
            stockNum = stockNum-1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }

        //解锁
        redisLock.unlock(productId,String.valueOf(time));

    }





} 