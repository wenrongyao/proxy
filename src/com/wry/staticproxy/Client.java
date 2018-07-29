package com.wry.staticproxy;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class Client {
    public static void main(String args[]) {
        Tank t = new Tank();
        TankTimeProxy ttp = new TankTimeProxy(t);
        TankLogProxy tlp = new TankLogProxy(ttp);
        Moveable m = tlp;
        m.moveable();
    }
}
