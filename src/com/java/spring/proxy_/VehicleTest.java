package com.java.spring.proxy_;

import org.junit.jupiter.api.Test;

/**
 * @author liushuo
 * @version 1.0
 */
public class VehicleTest {

    @Test
    public void run() {
        Vehicle car = new Car();
        Vehicle ship = new Ship();

        car.run();
        ship.run();
    }

    @Test
    public void ProxyRun() {
        Vehicle ship = new Ship();
        Vehicle car = new Car();
        VehicleProxyProvider vehicleProxyProviderShip = new VehicleProxyProvider(ship);
        VehicleProxyProvider vehicleProxyProviderCar = new VehicleProxyProvider(car);
        //获取代理对象
        Vehicle proxyShip = vehicleProxyProviderShip.getProxy();

        //proxy的编译类型是Vehicle,运行内存是proxy.$Proxy类型
        //所以当执行run方法的时候会执行到代理对象的invoke
        proxyShip.run();
        Vehicle proxyCar = vehicleProxyProviderCar.getProxy();
        proxyCar.run();


    }

}


