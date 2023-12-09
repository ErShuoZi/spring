package com.java.spring.web;
import com.java.spring.service.OrderService;

/**
 * @author liushuo
 * @version 1.0
 * Servlet就是Controller
 */
public class OrderServlet {
    private OrderService orderService;

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
