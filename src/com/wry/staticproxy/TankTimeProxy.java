package com.wry.staticproxy;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class TankTimeProxy implements Moveable {
    Moveable m;

    public TankTimeProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void moveable() {
        long start = System.currentTimeMillis();
        System.out.println(start);
        m.moveable();
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println("Tank move: " + (end - start));
    }
}
