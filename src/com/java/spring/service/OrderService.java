package com.java.spring.service;

import com.java.spring.dao.OrderDao;

/**
 * @author liushuo
 * @version 1.0
 * Service类
 */
public class OrderService {
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
