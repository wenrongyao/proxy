package com.wry.dynamicproxy;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class Tank implements Moveable {
    @Override
    public void moveable() {
        System.out.println("Tank start....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tank stop....");
    }
}
