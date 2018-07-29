package com.wry.staticproxy;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class TankLogProxy implements Moveable {
    Moveable m;

    public TankLogProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void moveable() {
        System.out.println("日志记录开始");
        m.moveable();
        System.out.println("日志记录结束");
    }
}
