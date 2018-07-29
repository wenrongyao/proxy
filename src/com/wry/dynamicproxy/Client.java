package com.wry.dynamicproxy;


/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class Client {
    public static void main(String args[]) throws Exception {
        Tank t = new Tank();
        InvocationHandler ih = new LogInvocationHandler(t);
        Moveable m = (Moveable) Proxy.newInstance(Moveable.class, ih);
//        Moveable m = new Proxy(ih);
        m.moveable();

    }
}
