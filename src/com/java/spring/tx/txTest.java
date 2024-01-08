package com.java.spring.tx;

import com.java.spring.tx.dao.GoodsDao;
import com.java.spring.tx.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class txTest {
    /**
     * 查询商品的价格
     */
    @Test
    public void queryPriceByIdTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        System.out.println("查询价格");
        GoodsDao bean = ioc.getBean(GoodsDao.class);
        Float price = bean.queryPriceById(1);
        System.out.println(" 1 号商品的价格= " + price);
    }


    /**
     * 修改用户余额(购买商品后)
     */
    @Test
    public void updateBalanceTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao bean = ioc.getBean(GoodsDao.class);
        bean.updateBalance(1, 30f);
        System.out.println("====修改余额成功====");
    }


    /**
     * 测试修改商品的库存量
     */
    @Test
    public void updateAmountTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao bean = ioc.getBean(GoodsDao.class);
        bean.updateAmount(1, 2);
        System.out.println("====修改商品库存量成功====");
    }


    @Test
    public void buyGoodsTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsService bean = ioc.getBean(GoodsService.class);
        bean.buyGoods(1, 2, 1);
        System.out.println("====购买商品成功====");
    }

    @Test
    public void buyGoodsByTxTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml"); GoodsService bean = ioc.getBean(GoodsService.class);
        //使用 buyGoodsByTx()
        bean.buyGoodsByTx(1, 2, 1);
        System.out.println("====购买商品成功===="); }
}
