package com.java.spring.tx.service;

import com.java.spring.tx.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    /**
     * 购买商品[没有使用事务] * @param user_id
     *
     * @param goods_id
     * @param num
     */
    public void buyGoods(int user_id, int goods_id, int num) {
        //查询到商品价格
        Float goods_price = goodsDao.queryPriceById(goods_id);
        //购买商品，减去余额
        goodsDao.updateBalance(user_id, goods_price * num);
        //模拟一个异常, 会发生数据库数据不一致现象
        int i = 10 / 0;
        //更新库存
        goodsDao.updateAmount(goods_id, num);
    }


    @Transactional
    // @Transactional 可以进行事务声明
    //即将标示的方法中，对数据库的操作作为一个事务管理
    public void buyGoodsByTx(int user_id, int goods_id, int num) {
        //查询到商品价格
        Float goods_price = goodsDao.queryPriceById(goods_id);
        //购买商品，减去余额
        goodsDao.updateBalance(user_id, goods_price * num);
        //模拟一个异常, 会发生数据库数据不一致现象
        int i = 10 / 0;
        //更新库存
        goodsDao.updateAmount(goods_id, num);
    }
}
